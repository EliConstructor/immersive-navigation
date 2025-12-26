package me.eliq.immersivenavigation;

import me.eliq.immersivenavigation.gui.TrinketInfoOverlay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ImmersiveNavigationClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HudRenderCallback.EVENT.register(new TrinketInfoOverlay());
	}
}