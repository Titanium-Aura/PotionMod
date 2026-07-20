package net.titaniumaura.potionmod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.titaniumaura.potionmod.PotionMod;
import net.titaniumaura.potionmod.effect.ModEffects;
import net.titaniumaura.potionmod.effect.SplashAndLingeringEffect;


@EventBusSubscriber(modid = PotionMod.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void onSplashHitEffect(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        HitResult hitResult = event.getRayTraceResult();

        if (projectile instanceof ThrownPotion potion) {
            PotionContents potioncontents = potion.getItem().getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);

            for (MobEffectInstance instance : potioncontents.getAllEffects()) {
                if (instance.getEffect().value() instanceof SplashAndLingeringEffect effect) {
                    if (!potion.getItem().is(Items.LINGERING_POTION)) {
                        if (!projectile.level().isClientSide()) {
                            effect.onSplashHit((ServerLevel) projectile.level(), new BlockPos((int) hitResult.getLocation().x,(int) hitResult.getLocation().y,(int) hitResult.getLocation().z));
                        }
                    }
                }
            }
        }
    }
}
