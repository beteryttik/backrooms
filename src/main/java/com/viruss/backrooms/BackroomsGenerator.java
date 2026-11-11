package com.viruss.backrooms;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.FlatLevelGeneratorSettings;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.StructureManager;
import net.minecraft.world.level.levelgen.blender.Blender;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Level 0: endless yellow rooms.
 * Floor y0 (base) + y1 (carpet), walls y2..y4 on a 12-block grid with doorways,
 * ceiling y5 with fluorescent lamps every 8 blocks.
 */
public class BackroomsGenerator extends FlatLevelSource {
    public static final MapCodec<BackroomsGenerator> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    FlatLevelGeneratorSettings.CODEC.fieldOf("settings").forGetter(g -> g.settings)
            ).apply(instance, BackroomsGenerator::new));

    private final FlatLevelGeneratorSettings settings;

    public BackroomsGenerator(FlatLevelGeneratorSettings settings) {
        super(settings);
        this.settings = settings;
    }

    @Override
    protected MapCodec<? extends net.minecraft.world.level.levelgen.ChunkGenerator> codec() {
        return CODEC;
    }

    private static BlockState st(Block block) {
        return block.defaultBlockState();
    }

    private static int mod(int v, int m) {
        return Mth.positiveModulo(v, m);
    }

    private static void set(ChunkAccess chunk, int x, int y, int z, BlockState state) {
        chunk.setBlockState(new BlockPos(x, y, z), state, false);
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, RandomState random,
                                                        StructureManager structureManager, ChunkAccess chunk) {
        BlockState base = st(Blocks.SMOOTH_STONE);
        BlockState carpet = st(ModBlocks.CARPET.get());
        BlockState wall = st(ModBlocks.WALLPAPER.get());
        BlockState ceiling = st(ModBlocks.CEILING.get());
        BlockState lamp = st(ModBlocks.LAMP.get());
        int minY = chunk.getMinBuildHeight();

        for (int lz = 0; lz < 16; lz++) {
            for (int lx = 0; lx < 16; lx++) {
                int x = chunk.getPos().getMinBlockX() + lx;
                int z = chunk.getPos().getMinBlockZ() + lz;

                set(chunk, x, minY, z, base);
                set(chunk, x, minY + 1, z, carpet);
                set(chunk, x, minY + 5, z, ceiling);

                if (mod(x, 8) == 4 && mod(z, 8) == 4) {
                    set(chunk, x, minY + 5, z, lamp);
                }

                boolean wallX = mod(x, 12) == 0;
                boolean wallZ = mod(z, 12) == 0;
                if (wallX != wallZ) {
                    int along = wallX ? z : x;
                    int d = mod(along, 12);
                    if (d < 5 || d > 7) {
                        for (int y = 2; y <= 4; y++) {
                            set(chunk, x, minY + y, z, wall);
                        }
                    }
                }
            }
        }
        return CompletableFuture.completedFuture(chunk);
    }
}
