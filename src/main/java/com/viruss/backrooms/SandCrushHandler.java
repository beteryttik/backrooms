package com.viruss.backrooms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Entry: get buried by sand.
 * 1) Your eyes end up inside a sand block -> noclip.
 * 2) Falling sand overlaps you for a few ticks (it presses through you) -> noclip.
 */
@Mod.EventBusSubscriber(modid = BackroomsMod.MODID)
public class SandCrushHandler {
    private static final Map<UUID, Integer> CRUSH_PROGRESS = new HashMap<>();
    private static final Map<UUID, Long> COOLDOWN = new HashMap<>();
    private static final int THRESHOLD_TICKS = 3;
    private static final int COOLDOWN_TICKS = 100;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide) return;

        ServerPlayer player = (ServerPlayer) event.player;
        ServerLevel level = (ServerLevel) player.level();

        long now = level.getGameTime();
        Long last = COOLDOWN.get(player.getUUID());
        if (last != null && now - last < COOLDOWN_TICKS) return;

        if (level.dimension() == ModGen.BACKROOMS_LEVEL) return;

        boolean buried = false;

        BlockState eyeState = level.getBlockState(BlockPos.containing(player.getEyePosition().x,
                player.getEyePosition().y, player.getEyePosition().z));
        buried = eyeState.is(Blocks.SAND) || eyeState.is(Blocks.RED_SAND);

        if (!buried) {
            AABB box = player.getBoundingBox().inflate(0.1);
            List<FallingBlockEntity> sand = level.getEntitiesOfClass(FallingBlockEntity.class, box,
                    e -> e.getBlockState().is(Blocks.SAND) || e.getBlockState().is(Blocks.RED_SAND));
            if (!sand.isEmpty()) {
                int p = CRUSH_PROGRESS.getOrDefault(player.getUUID(), 0) + 1;
                CRUSH_PROGRESS.put(player.getUUID(), p);
                if (p >= THRESHOLD_TICKS) buried = true;
            } else {
                CRUSH_PROGRESS.remove(player.getUUID());
            }
        }

        if (buried) {
            COOLDOWN.put(player.getUUID(), now);
            CRUSH_PROGRESS.remove(player.getUUID());
            ServerLevel backrooms = level.getServer().getLevel(ModGen.BACKROOMS_LEVEL);
            if (backrooms == null) return;
            player.teleportTo(backrooms, 8.5, 2.0, 8.5, player.getYRot(), player.getXRot());
            player.sendSystemMessage(Component.translatable("message.backrooms.noclip"));
        }
    }
}
