package net.titaniumaura.potionmod.item;


import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.titaniumaura.potionmod.PotionMod;
import net.titaniumaura.potionmod.item.custom.FunkySplashPotionItem;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PotionMod.MODID);

    public static final DeferredItem<Item> FUNKY_POTION = ITEMS.register("funky_potion",
            () -> new FunkySplashPotionItem(new Item.Properties().stacksTo(1).component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
