package net.titaniumaura.potionmod.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;


public class LightningEffect extends InstantenousSpecialEffect {
    public LightningEffect(MobEffectCategory category, int color) {
        super(category, color);
    }


   /* @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.level().isClientSide()) {
           spawnLightning((ServerLevel) livingEntity.level(), livingEntity.getOnPos(), amplifier);
        }
        return true;
   }*/


    /*@Override
    public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity livingEntity, int amplifier, double health) {
       spawnLightning((ServerLevel) livingEntity.level(), livingEntity.getOnPos(), amplifier);
    }*/


    @Override
    public void onDrink(LivingEntity entity, ItemStack potion, int amplifier) {
        if (!entity.level().isClientSide()) {
            spawnLightning((ServerLevel) entity.level(), entity.getOnPos(), amplifier);
        }
    }


    @Override
    public void onSplashHit(ServerLevel level, BlockPos pos, int amplifier) {
        spawnLightning(level, pos, amplifier);
    }


    @Override
    public void onLingeringHit(ServerLevel level, BlockPos pos, int amplifier) {
        level.setWeatherParameters(0, 15600, true, true);
    }


    @Override
    public void applyLingerRepeat(ServerLevel level, BlockPos pos, int amplifier) {
        spawnLightning(level, pos, amplifier);
    }


    private void spawnLightning(ServerLevel level, BlockPos pos, int amplifier) {
        for (int i = -1; i < amplifier; i++) {
            EntityType.LIGHTNING_BOLT.spawn(level, pos, MobSpawnType.TRIGGERED);
        }
    }
}
