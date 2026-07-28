package net.titaniumaura.potionmod;

import net.titaniumaura.potionmod.block.ModBlocks;
import net.titaniumaura.potionmod.component.ModDataComponents;
import net.titaniumaura.potionmod.effect.ModEffects;
import net.titaniumaura.potionmod.enchantment.ModEnchantmentEffects;
import net.titaniumaura.potionmod.entity.ModEntities;
import net.titaniumaura.potionmod.item.ModCreativeModeTabs;
import net.titaniumaura.potionmod.item.ModItems;
import net.titaniumaura.potionmod.loot.ModLootModifers;
import net.titaniumaura.potionmod.particle.ModParticles;
import net.titaniumaura.potionmod.potion.ModPotions;
import net.titaniumaura.potionmod.sound.ModSounds;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;


@Mod(PotionMod.MODID)
public class PotionMod {
    public static final String MODID = "potionmod";
    public static final Logger LOGGER = LogUtils.getLogger();


    public PotionMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModParticles.register(modEventBus);
        ModEffects.register(modEventBus);
        ModPotions.register(modEventBus);
        ModEnchantmentEffects.register(modEventBus);
        ModEntities.register(modEventBus);
        ModSounds.register(modEventBus);
        ModDataComponents.register(modEventBus);
        ModLootModifers.register(modEventBus);


        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
