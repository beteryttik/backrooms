package com.viruss.backrooms;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.StructureManager;


public class BackroomsGenerator extends ChunkGenerator {

    public static final MapCodec<BackroomsGenerator> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    BiomeSource.CODEC.fieldOf("biome_source").forGetter(g -> g.biomeSource)
            ).apply(instance, BackroomsGenerator::new)
    );

    private static final int FLOOR_Y = 64;
    private static final int CEIL_Y = 68;

    private static final BlockState FLOOR   = Blocks.YELLOW_WOOL.defaultBlockState();
    private static final BlockState WALL    = Blocks.YELLOW_TERRACOTTA.defaultBlockState();
    private static final BlockState CEILING = Blocks.LIGHT_GRAY_CONCRETE.defaultBlockState();
    private static final BlockState LAMP    = Blocks.SEA_LANTERN.defaultBlockState();
    private static final BlockState AIR     = Blocks.AIR.defaultBlockState();

    public BackroomsGenerator(BiomeSource biomeSource) {
        super(biomeSource);
    }

    @Override
    protected MapCodec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, RandomState random,
                                                        StructureManager structureManager, ChunkAccess chunk) {
        ChunkPos cp = chunk.getPos();
        int baseX = cp.getMinBlockX();
        int baseZ = cp.getMinBlockZ();

        // сид на координаты чанка: у каждой "ячейки" своя судьба
        RandomSource rng = RandomSource.create(baseX * 341873128712L + baseZ * 132897987541L + 911L);

        boolean skipWest = rng.nextFloat() < 0.25F;
        boolean skipNorth = rng.nextFloat() < 0.25F;
        int door = 4 + rng.nextInt(8);

        for (int lx = 0; lx < 16; lx++) {
            for (int lz = 0; lz < 16; lz++) {
                int x = baseX + lx;
                int z = baseZ + lz;

                set(chunk, x, FLOOR_Y, z, FLOOR);
                for (int y = FLOOR_Y + 1; y < CEIL_Y; y++) {
                    set(chunk, x, y, z, AIR);
                }
                set(chunk, x, CEIL_Y, z, (lx == 8 && lz == 8) ? LAMP : CEILING);
            }
        }

        // западная стена ячейки (с проёмом-дверью)
        if (!skipWest) {
            for (int lz = 0; lz < 16; lz++) {
                if (lz >= door && lz < door + 2) continue;
                for (int y = FLOOR_Y + 1; y < CEIL_Y; y++) {
                    set(chunk, baseX, y, baseZ + lz, WALL);
                }
            }
        }
        // северная стена ячейки
        if (!skipNorth) {
            for (int lx = 0; lx < 16; lx++) {
                if (lx >= door && lx < door + 2) continue;
                for (int y = FLOOR_Y + 1; y < CEIL_Y; y++) {
                    set(chunk, baseX + lx, y, baseZ, WALL);
                }
            }
        }

        return CompletableFuture.completedFuture(chunk);
    }

    private static void set(ChunkAccess chunk, int x, int y, int z, BlockState state) {
        chunk.setBlockState(new BlockPos(x, y, z), state, false);
    }

    @Override
    public void buildSurface(WorldGenRegion region, StructureManager structureManager, RandomState random, ChunkAccess chunk) {
    }

    @Override
    public void applyCarvers(WorldGenRegion region, long seed, RandomState random, BiomeManager biomeManager,
                             StructureManager structureManager, ChunkAccess chunk, GenerationStep.Carving step) {
    }

    @Override
    public void spawnOriginalMobs(WorldGenRegion region) {
    }

    @Override
    public int getGenDepth() {
        return 128;
    }

    @Override
    public int getMinY() {
        return 0;
    }

    @Override
    public int getSeaLevel() {
        return 0;
    }

    @Override
    public int getBaseHeight(int x, int z, Heightmap.Types type, LevelHeightAccessor level, RandomState random) {
        return FLOOR_Y + 1;
    }

    @Override
    public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor level, RandomState random) {
        return new NoiseColumn(FLOOR_Y, new BlockState[]{FLOOR});
    }

    @Override
    public void addDebugScreenInfo(List<String> info, RandomState random, BlockPos pos) {
    }
}
