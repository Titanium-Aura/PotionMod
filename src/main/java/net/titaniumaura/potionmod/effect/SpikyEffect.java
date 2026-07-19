package net.titaniumaura.potionmod.effect;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

public class SpikyEffect extends MobEffect {
    public SpikyEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onMobHurt(LivingEntity livingEntity, int amplifier, DamageSource damageSource, float amount) {
        int spawnCount = 8 + 4 * amplifier;
        for (int i = 0; i < spawnCount; i++) {
            this.spawnArrow(livingEntity.level(), livingEntity, livingEntity.getX(), livingEntity.getY() + (double)livingEntity.getBbHeight() / 2.0, livingEntity.getZ());
        }
    }

    private void spawnArrow(Level level, LivingEntity entity, double x, double y, double z) {
        Arrow arrow = EntityType.ARROW.create(level);
        if (arrow != null) {
            arrow.moveTo(x, y, z);
            Vector3f shootDir = new Vector3f(1, 0.5f, 0).rotateY(level.getRandom().nextFloat() * 360.0F);
            arrow.shoot(shootDir.x, shootDir.y, shootDir.z, 1, 0);
            level.addFreshEntity(arrow);
            arrow.playSound(SoundEvents.DISPENSER_LAUNCH);
        }
    }
}
