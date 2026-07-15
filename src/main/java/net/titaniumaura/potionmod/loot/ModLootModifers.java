package net.titaniumaura.potionmod.loot;

import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.titaniumaura.potionmod.PotionMod;

import java.util.function.Supplier;

public class ModLootModifers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFER_SERIALIZERS =
        DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, PotionMod.MODID);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM =
            LOOT_MODIFER_SERIALIZERS.register("add_item", () -> AddItemModifier.CODEC);


    public static void register(IEventBus eventBus) {
        LOOT_MODIFER_SERIALIZERS.register(eventBus);
    }
}
