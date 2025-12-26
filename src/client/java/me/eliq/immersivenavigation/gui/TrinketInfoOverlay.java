package me.eliq.immersivenavigation.gui;

import me.eliq.immersivenavigation.ImmersiveNavigation;
import me.eliq.immersivenavigation.registry.ModItems;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionTypes;

public class TrinketInfoOverlay implements HudRenderCallback {
    private static final Identifier BAROMETER_ICON = Identifier.of(ImmersiveNavigation.MOD_ID, "barometer");
    private static final Identifier SEXTANT_ICON = Identifier.of(ImmersiveNavigation.MOD_ID, "sextant");

    private static final int ROW_HEIGHT = 20;
    int rowNum = 0;

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null)
            return;
        if (client.player == null)
            return;
        if (client.getDebugHud().shouldShowDebugHud())
            return;

        rowNum = 0;

        if (client.player.getInventory().contains(new ItemStack(ModItems.SEXTANT)))
            drawSextantInfo(drawContext, client, client.player);
        if (client.player.getInventory().contains(new ItemStack(ModItems.BAROMETER)))
            drawBarometerInfo(drawContext, client, client.player);
    }

    private void drawSextantInfo(DrawContext drawContext, MinecraftClient client, PlayerEntity player){
        if (!ImmersiveNavigation.config.sextantOverlay)
            return;
        if (player.getWorld().getRegistryKey() != World.OVERWORLD)
            return;
        if (!player.getWorld().isSkyVisible(player.getBlockPos()))
            return;

        float latitude = (float) player.getBlockZ() / ImmersiveNavigation.config.sextantBlocksPerDegree;
        float longitude = (float) player.getBlockX() / ImmersiveNavigation.config.sextantBlocksPerDegree;

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

        drawContext.drawGuiTexture(SEXTANT_ICON, 2, 2 + ROW_HEIGHT * rowNum, 16, 16);
        drawContext.drawText(client.textRenderer,
                Text.translatable("gui.immersivenavigation.sextant.coordinates", Math.abs(latitude), latLetter, Math.abs(longitude), lonLetter),
                20, 5 + ROW_HEIGHT * rowNum, Colors.WHITE, true);
        rowNum++;
    }

    private void drawBarometerInfo(DrawContext drawContext, MinecraftClient client, PlayerEntity player){
        if (!ImmersiveNavigation.config.barometerOverlay)
            return;

        if (!(player.getWorld().getRegistryKey() == World.OVERWORLD || player.getWorld().getRegistryKey() == World.NETHER))
            return;

        int difference = player.getBlockY() - player.getWorld().getSeaLevel();
        Text message = Text.of("");

        if (player.getWorld().getRegistryKey() == World.NETHER){
            difference -= ImmersiveNavigation.config.netherDepth;
        }

        if (difference == 0){
            message = Text.translatable("gui.immersivenavigation.barometer.at_sea_level");
        } else if (difference > 0){
            message = Text.translatable("gui.immersivenavigation.barometer.above_sea_level", difference);
        } else if (difference < 0){
            message = Text.translatable("gui.immersivenavigation.barometer.below_sea_level", -difference);
        }

        drawContext.drawGuiTexture(BAROMETER_ICON, 2, 2 + ROW_HEIGHT * rowNum, 16, 16);
        drawContext.drawText(client.textRenderer, message, 20, 5 + ROW_HEIGHT * rowNum, Colors.WHITE, true);
        rowNum++;
    }
}
