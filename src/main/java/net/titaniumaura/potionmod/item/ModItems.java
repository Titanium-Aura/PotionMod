package net.titaniumaura.potionmod.item;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.titaniumaura.potionmod.PotionMod;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PotionMod.MODID);



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
