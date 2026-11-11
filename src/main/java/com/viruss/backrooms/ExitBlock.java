package com.viruss.backrooms;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ExitBlock extends Block {
    public ExitBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide && player instanceof ServerPlayer sp) {
            ServerLevel overworld = sp.getServer().overworld();
            if (overworld != null && sp.level() != overworld) {
                BlockPos spawn = overworld.getSharedSpawnPos();
                sp.teleportTo(overworld, spawn.getX() + 0.5, spawn.getY() + 1.0, spawn.getZ() + 0.5,
                        sp.getYRot(), sp.getXRot());
                sp.sendSystemMessage(Component.translatable("message.backrooms.escaped"));
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
