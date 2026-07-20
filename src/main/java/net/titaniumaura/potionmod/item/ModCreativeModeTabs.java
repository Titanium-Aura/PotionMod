package net.titaniumaura.potionmod.item;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.titaniumaura.potionmod.PotionMod;

import java.util.function.Supplier;


public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PotionMod.MODID);


    public static final Supplier<CreativeModeTab> POTIONS = CREATIVE_MODE_TAB.register("custom_potions_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(Items.BREWING_STAND))
                    .title(Component.translatable("creativetab.potionmod.custompotions"))
                    .displayItems((itemDisplayParameters, output) -> {
                        itemDisplayParameters.holders()
                                .lookup(Registries.POTION)
                                .ifPresent(
                                        potionRegistryLookup -> {
                                            generatePotionEffectTypes(
                                                    output,
                                                    potionRegistryLookup,
                                                    ModItems.FUNKY_POTION.get(),
                                                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS,
                                                    itemDisplayParameters.enabledFeatures()
                                            );
                                        });
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }


    private static void generatePotionEffectTypes(
            CreativeModeTab.Output output,
            HolderLookup<Potion> potions,
            Item item,
            CreativeModeTab.TabVisibility tabVisibility,
            FeatureFlagSet requiredFeatures) {
        potions.listElements()
                .filter(reference -> reference.value().isEnabled(requiredFeatures))
                .map(reference -> PotionContents.createItemStack(item, reference))
                .forEach(itemStack -> output.accept(itemStack, tabVisibility));
    }
}