package me.eliq.immersivenavigation.registry;

import me.eliq.immersivenavigation.ImmersiveNavigation;
import me.eliq.immersivenavigation.item.BarometerItem;
import me.eliq.immersivenavigation.item.SextantItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
	public static final Item BAROMETER = item("barometer", BarometerItem::new);
	public static final Item SEXTANT = item("sextant", SextantItem::new);

	private static Item item(String id, Function<Item.Settings, Item> factory) {
		Identifier identifier = Identifier.of(ImmersiveNavigation.MOD_ID, id);
		RegistryKey<Item> key = RegistryKey.of(Registries.ITEM.getKey(), identifier);

		return Registry.register(Registries.ITEM, identifier, factory.apply(new Item.Settings().registryKey(key)));
	}

	public static void registerModItems() {
		ImmersiveNavigation.LOGGER.info("Registering items...");
	}
}