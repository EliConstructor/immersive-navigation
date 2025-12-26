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

public class SextantItem extends Item {
    public SextantItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isSkyVisible(user.getBlockPos())){
            user.sendMessage(Text.translatable("item.immersivenavigation.sextant.no_sky"), true);
        } else if (world.getRegistryKey() != World.OVERWORLD) {
            user.sendMessage(Text.translatable("item.immersivenavigation.sextant.unfamiliar_sky"), true);
        }
        else {
            float latitude = (float) user.getBlockZ() / ImmersiveNavigation.config.sextantBlocksPerDegree;
            float longitude = (float) user.getBlockX() / ImmersiveNavigation.config.sextantBlocksPerDegree;

            // Rounding
            int tens = (int) Math.pow(10, ImmersiveNavigation.config.sextantDecimalPlaces);
            latitude = (float) (Math.round(latitude * tens))/tens;
            longitude = (float) (Math.round(longitude * tens))/tens;

            String latLetter = "N";
            if (latitude > 0)
                latLetter = "S";
            String lonLetter = "W";
            if (longitude > 0)
                lonLetter = "E";

            user.sendMessage(Text.translatable("item.immersivenavigation.sextant.coordinates", Math.abs(latitude), latLetter, Math.abs(longitude), lonLetter), true);
        }
        return super.use(world, user, hand);
    }
}
