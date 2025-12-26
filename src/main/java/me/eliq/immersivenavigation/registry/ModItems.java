package me.eliq.immersivenavigation.registry;

import me.eliq.immersivenavigation.ImmersiveNavigation;
import me.eliq.immersivenavigation.item.BarometerItem;
import me.eliq.immersivenavigation.item.SextantItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item BAROMETER = item("barometer", new BarometerItem(new Item.Settings()));
    public static final Item SEXTANT = item("sextant", new SextantItem(new Item.Settings()));

    private static Item item(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ImmersiveNavigation.MOD_ID, id), item);
    }

    public static void registerModItems(){
        ImmersiveNavigation.LOGGER.info("Registering items...");
    }
}
