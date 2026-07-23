package net.titaniumaura.potionmod.potion;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.titaniumaura.potionmod.PotionMod;
import net.titaniumaura.potionmod.effect.ModEffects;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, PotionMod.MODID);

    public static final Holder<Potion> LIGHTNING = POTIONS.register("lightning_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.LIGHTNING)));
    public static final Holder<Potion> FRAIL = POTIONS.register("frail_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.FRAIL)));


    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
