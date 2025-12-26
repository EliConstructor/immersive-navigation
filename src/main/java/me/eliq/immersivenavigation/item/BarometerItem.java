package me.eliq.immersivenavigation.item;

import me.eliq.immersivenavigation.ImmersiveNavigation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionTypes;

public class BarometerItem extends Item {
    public BarometerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Text message = Text.of("");

        int difference = user.getBlockY() - world.getSeaLevel();

        if (world.getRegistryKey() == World.NETHER){
            difference -= ImmersiveNavigation.config.netherDepth;
        }

        if (difference == 0){
            message = Text.translatable("item.immersivenavigation.barometer.at_sea_level");
        } else if (difference > 0){
            message = Text.translatable("item.immersivenavigation.barometer.above_sea_level", difference);
        } else if (difference < 0){
            message = Text.translatable("item.immersivenavigation.barometer.below_sea_level", -difference);
        }

        if (!(world.getRegistryKey() == World.OVERWORLD || world.getRegistryKey() == World.NETHER)) {
            message = Text.translatable("item.immersivenavigation.barometer.not_working");
        }

        user.sendMessage(Text.of(message), true);
        return super.use(world, user, hand);
    }
}
