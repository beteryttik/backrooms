package com.viruss.backrooms.client;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;

public class BackroomsFog extends DimensionSpecialEffects {
    public BackroomsFog() {
        super(Float.NaN, false, DimensionSpecialEffects.SkyType.NONE, false, false);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 fogColor, float brightness) {
        return new Vec3(0.50, 0.45, 0.20).scale(brightness * 0.95 + 0.05);
    }

    @Override
    public boolean isFoggyAt(int x, int z) {
        return true;
    }
}
