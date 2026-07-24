package net.titaniumaura.potionmod.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class DetonationEffect extends InstantenousSplashAndLingeringEffect{
    public DetonationEffect(MobEffectCategory category, int color) {
        super(category, color);
    }


    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        Level level = livingEntity.level();
        if (!level.isClientSide()) {
            level.explode(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 3f, Level.ExplosionInteraction.MOB);
        }
        return true;
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity livingEntity, int amplifier, double health) {
        Level level = livingEntity.level();
        if (!level.isClientSide()) {
            level.explode(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 3f, Level.ExplosionInteraction.MOB);
        }
    }


    @Override
    public void onSplashHit(ServerLevel level, BlockPos pos) {
        if (!level.isClientSide()) {
            level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 3f, Level.ExplosionInteraction.MOB);
        }
    }


    @Override
    public void onLingeringHit(ServerLevel level, BlockPos pos) {
        if (!level.isClientSide()) {
            level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 3f, Level.ExplosionInteraction.MOB);
        }
    }
}
