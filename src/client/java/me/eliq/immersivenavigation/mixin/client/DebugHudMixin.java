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

	@Inject(method = "drawText", at = @At("HEAD"))
	private void drawLeftText(DrawContext context, List<String> text, boolean left, CallbackInfo ci) {
		if (!left) return;
		if (!this.client.getDebugHud().shouldShowDebugHud()) return;

		if (client.hasReducedDebugInfo() && ImmersiveNavigation.config.noSurvivalDebug) {
			text.clear();
			text.add("Minecraft " + SharedConstants.getGameVersion().name() + " (" + this.client.getGameVersion() + "/" + ClientBrandRetriever.getClientModName() + ")");
			text.add("Immersive Navigation's no survival debug is enabled!");
		}
	}
}