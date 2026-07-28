package net.titaniumaura.potionmod.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class SpecialEffect extends MobEffect {
    public SpecialEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    public void onSplashHit(ServerLevel level, BlockPos pos, int amplifier) {}
    public void onArrowHit(ServerLevel level, BlockPos pos, int amplifier) {}
    public void onLingeringHit(ServerLevel level, BlockPos pos, int amplifier) {}
    public void onDrink(LivingEntity entity, ItemStack potion, int amplifier) {}

    public void applyLingerRepeat(ServerLevel level, BlockPos pos, int amplifier) {}
}
