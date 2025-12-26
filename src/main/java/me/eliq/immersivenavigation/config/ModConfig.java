package me.eliq.immersivenavigation.config;

import me.eliq.immersivenavigation.ImmersiveNavigation;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = ImmersiveNavigation.MOD_ID)
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public int netherDepth = 2000;
    @ConfigEntry.Gui.Tooltip
    public boolean barometerOverlay = true;
    @ConfigEntry.Gui.Tooltip
    public boolean sextantOverlay = true;
    @ConfigEntry.Gui.Tooltip
    public int sextantBlocksPerDegree = 1670;
    @ConfigEntry.Gui.Tooltip
    public int sextantDecimalPlaces = 1;
    @ConfigEntry.Gui.Tooltip
    public boolean compassPointsNorth = true;
    @ConfigEntry.Gui.Tooltip
    public boolean showCompassHintTooltip = true;
    @ConfigEntry.Gui.Tooltip
    public boolean noSurvivalDebug = true;
}
