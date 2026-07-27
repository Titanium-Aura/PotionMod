package net.titaniumaura.potionmod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.titaniumaura.potionmod.PotionMod;
import net.titaniumaura.potionmod.effect.SpecialEffect;


@EventBusSubscriber(modid = PotionMod.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onPotionDrinkEffect(LivingEntityUseItemEvent.Finish event){
        ItemStack potion = event.getItem();
        if (potion.is(Items.POTION)) {
            PotionContents potioncontents = potion.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);

            for (MobEffectInstance instance : potioncontents.getAllEffects()) {
                if (instance.getEffect().value() instanceof SpecialEffect effect) {
                    effect.onDrink(event.getEntity(), potion, instance.getAmplifier());
                }
            }
        }
    }



    @SubscribeEvent
    public static void onSplashHitEffect(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        Vec3 location = event.getRayTraceResult().getLocation();

        if (projectile instanceof ThrownPotion potion) {
            PotionContents potioncontents = potion.getItem().getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);

            for (MobEffectInstance instance : potioncontents.getAllEffects()) {
                if (instance.getEffect().value() instanceof SpecialEffect effect) {
                    if (!potion.getItem().is(Items.LINGERING_POTION)) {
                        if (!projectile.level().isClientSide()) {
                            effect.onSplashHit((ServerLevel) projectile.level(), new BlockPos((int) location.x,(int) location.y,(int) location.z));
                        }
                    } else {
                        if (!projectile.level().isClientSide()) {
                            effect.onLingeringHit((ServerLevel) projectile.level(), new BlockPos((int) location.x,(int) location.y,(int) location.z));
                        }
                    }
                }
            }
        }
    }
}
