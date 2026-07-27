package net.titaniumaura.potionmod.mixin;


import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.titaniumaura.potionmod.effect.SpecialEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(AreaEffectCloud.class)
public abstract class AreaCloudMixin extends Entity {
    public AreaCloudMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    private PotionContents potionContents = PotionContents.EMPTY;

    @Inject(method = "tick", at = @At("TAIL"), cancellable = true)
    public void repeat(CallbackInfo ci) {
        if (!this.level().isClientSide) {
            if (this.tickCount % 20 == 0) {
                if (this.potionContents.potion().isPresent()) {
                    for (MobEffectInstance mobeffectinstance1 : this.potionContents.potion().get().value().getEffects()) {
                        if (mobeffectinstance1.getEffect().value() instanceof SpecialEffect effect) {
                            effect.applyLingerRepeat((ServerLevel) this.level(), getOnPos(), mobeffectinstance1.getAmplifier());
                        }
                    }
                }
            }
        }
    }
}
