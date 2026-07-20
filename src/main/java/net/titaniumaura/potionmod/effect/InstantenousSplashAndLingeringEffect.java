package net.titaniumaura.potionmod.effect;

import net.minecraft.world.effect.MobEffectCategory;

public class InstantenousSplashAndLingeringEffect extends SplashAndLingeringEffect{
    public InstantenousSplashAndLingeringEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration >= 1;
    }
}
