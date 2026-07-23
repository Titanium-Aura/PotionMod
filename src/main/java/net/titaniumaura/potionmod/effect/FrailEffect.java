package net.titaniumaura.potionmod.effect;



import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;


public class FrailEffect extends MobEffect {
    public FrailEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onMobHurt(LivingEntity livingEntity, int amplifier, DamageSource damageSource, float amount) {
        livingEntity.hurt(damageSource, amount * (amplifier + 2f));
    }
}
