package net.titaniumaura.potionmod.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;

import javax.annotation.Nullable;

public class LightningEffect extends InstantenousMobEffect {

    public LightningEffect(MobEffectCategory category, int color) {
        super(category, color);
    }



    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.level().isClientSide()) {
            EntityType.LIGHTNING_BOLT.spawn((ServerLevel) livingEntity.level(), livingEntity.getOnPos(), MobSpawnType.TRIGGERED);
        }
        return true;
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity livingEntity, int amplifier, double health) {

        EntityType.LIGHTNING_BOLT.spawn((ServerLevel) livingEntity.level(), livingEntity.getOnPos(), MobSpawnType.TRIGGERED);

    }

}
