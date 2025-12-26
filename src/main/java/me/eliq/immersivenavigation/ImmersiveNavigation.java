package me.eliq.immersivenavigation;

import me.eliq.immersivenavigation.config.ModConfig;
import me.eliq.immersivenavigation.registry.ModItems;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImmersiveNavigation implements ModInitializer {
	public static final String MOD_ID = "immersivenavigation";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ModConfig config;

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Immersive Navigation.");

		// Registries
		ModItems.registerModItems();

		// Item group
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
			content.add(ModItems.BAROMETER);
			content.add(ModItems.SEXTANT);
		});

		// Config
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
	}
}