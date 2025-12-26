package me.eliq.immersivenavigation.mixin.client;

import com.google.common.collect.Lists;
import me.eliq.immersivenavigation.ImmersiveNavigation;
import net.minecraft.SharedConstants;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(DebugHud.class)
public class DebugHudMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    private void drawText(DrawContext context, List<String> text, boolean left) {/*dummy body*/}

    @Inject(method = "drawLeftText", at = @At("HEAD"), cancellable = true)
    protected void drawLeftText(DrawContext context, CallbackInfo ci){
        if (client.hasReducedDebugInfo() && ImmersiveNavigation.config.noSurvivalDebug){
            List<String> list = Lists.newArrayList("Minecraft " + SharedConstants.getGameVersion().getName() + " (" + this.client.getGameVersion() + "/" + ClientBrandRetriever.getClientModName() + ")", this.client.fpsDebugString);
            list.add("Immersive Navigation's no survival debug is enabled!");
            drawText(context, list, true);
            ci.cancel();
        }
    }
}