package net.titaniumaura.potionmod.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SplashAndLingeringEffect extends MobEffect {
    public SplashAndLingeringEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    public void onSplashHit(ServerLevel level, BlockPos pos) {}
    public void onLingeringHit(ServerLevel level, BlockPos pos) {}
}
