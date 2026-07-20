package net.titaniumaura.potionmod.event;

import net.minecraft.core.component.DataComponents;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.titaniumaura.potionmod.PotionMod;
import net.titaniumaura.potionmod.item.ModItems;

@EventBusSubscriber(modid = PotionMod.MODID)
public class ModClientEvents {
    @SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> {
                    return tintIndex > 0 ? -1 : FastColor.ARGB32.opaque(stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).getColor());
        },
                ModItems.FUNKY_POTION.value());
    }

}
