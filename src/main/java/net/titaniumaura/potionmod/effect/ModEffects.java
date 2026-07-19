package net.titaniumaura.potionmod.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.titaniumaura.potionmod.PotionMod;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, PotionMod.MODID);

    public static final Holder<MobEffect> SPIKY = MOB_EFFECTS.register("spiky",
            () -> new SpikyEffect(MobEffectCategory.BENEFICIAL, 0x6E003B));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
