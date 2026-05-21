package com.peak.sibyl.core;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
public class SibylConfig extends MidnightConfig {
    private static final String hud = "hud";
    private static final String window = "window";
    private static final String player = "player";

    @Entry(category = hud)
    public static boolean renderHotbar = true;

    @Entry(category = hud)
    public static boolean renderScoreboard = true;

    @Entry(category = hud)
    public static boolean renderHearts = true;

    @Entry(category = hud)
    public static boolean renderBossbar = true;

    @Entry(category = hud)
    public static boolean renderCrosshair = true;

    @Entry(category = hud)
    public static boolean renderSwingHand = true;

    @Entry(category = hud)
    public static boolean renderPortalOverlay = true;

    @Entry(category = hud)
    public static boolean renderPowderSnowOverlay = true;

    @Entry(category = hud)
    public static boolean renderPumpkinBlur = true;

    @Entry(category = window)
    public static String windowTitle = "";

    @Entry(category = window, isSlider = true, min = -15, max = 15)
    public static int panoramaSpeed = 0;

    @Entry(category = window)
    public static boolean modInfoOnTitleScreen = true;

    @Entry(category = window)
    public static String customSplashMessage = "";

    @Entry(category = window)
    public static WindowIconSet windowIcon = WindowIconSet.RELEASE;

    @Entry(category = window)
    public static boolean fullDeafen = false;

    @Entry(category = player)
    public static String customDeathMessage = "";

    @Entry(category = player)
    public static boolean hudCoordinateReadout = false;

    @Entry(category = player)
    public static boolean shouldRenderArmor = true;

    @Entry(category = player)
    public static boolean shouldShowTutorialToasts = true;

    @Entry(category = player)
    public static boolean shouldShowAdvancementToasts = true;

    @Entry(category = player)
    public static boolean shouldShowSystemToasts = true;

    @Entry(category = player)
    public static boolean shouldShowRecipeToasts = true;

    public enum WindowIconSet {
        RELEASE,
        SNAPSHOT
    }
}