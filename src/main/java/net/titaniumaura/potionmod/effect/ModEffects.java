package net.titaniumaura.potionmod.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.titaniumaura.potionmod.PotionMod;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, PotionMod.MODID);

    public static final Holder<MobEffect> SPIKY = MOB_EFFECTS.register("spiky",
            () -> new SpikyEffect(MobEffectCategory.BENEFICIAL, 0x6E003B));

    public static final Holder<MobEffect> LIGHTNING = MOB_EFFECTS.register("lightning",
            () -> new LightningEffect(MobEffectCategory.HARMFUL, 0x306EF0));

    public static final Holder<MobEffect> FRAIL = MOB_EFFECTS.register("frail",
            () -> new FrailEffect(MobEffectCategory.HARMFUL, 0xF9F6EE));

    public static final Holder<MobEffect> VULNERABLE = MOB_EFFECTS.register("vulnerable",
            () -> new VulnerableEffect(MobEffectCategory.HARMFUL, 0xF9F6EE)
                    .addAttributeModifier(
                            Attributes.MAX_HEALTH,
                            ResourceLocation.fromNamespaceAndPath(PotionMod.MODID, "vulnerable"),
                            -6.0,
                            AttributeModifier.Operation.ADD_VALUE));

    public static final Holder<MobEffect> ELEVATION = MOB_EFFECTS.register("elevation",
            () -> new ElevationEffect(MobEffectCategory.NEUTRAL, 0xFFFFFF));

    public static final Holder<MobEffect> DESCENT = MOB_EFFECTS.register("descent",
            () -> new DescentEffect(MobEffectCategory.NEUTRAL, 0xFFFFC5));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
