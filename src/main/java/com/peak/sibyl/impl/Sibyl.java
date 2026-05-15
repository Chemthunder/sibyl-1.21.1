package com.peak.sibyl.impl;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.registry.RegistryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class Sibyl implements ClientModInitializer, DataGeneratorEntrypoint {
	public static final String MOD_ID = "sibyl";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public void onInitializeClient() {
        try {
            LOGGER.info("Sibyl has initialized!");

            MidnightConfig.init(MOD_ID, Config.class);
        } catch (Exception e) {
            LOGGER.error("Sibyl initialization has failed! {}", e.getLocalizedMessage());
        }
    }

    public static ModMetadata getModData() {
        return FabricLoader.getInstance().getModContainer(MOD_ID).get().getMetadata();
    }

    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(Lang::new);
    }

    public static class Config extends MidnightConfig {
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

        @Entry(category = window, isSlider = true, min = -16, max = 16)
        public static int panoramaSpeed = 0;

        @Entry(category = window)
        public static boolean modInfoOnTitleScreen = true;

        @Entry(category = player)
        public static String customDeathMessage = "";
    }

    public static class Lang extends FabricLanguageProvider {
        public Lang(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(dataOutput, registryLookup);
        }

        public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
            translationBuilder.add("sibyl.title_text", "Sibyl#%s");

            translationBuilder.add("sibyl.midnightconfig.category.hud", "GUI & Hud");
            translationBuilder.add("sibyl.midnightconfig.category.window", "Minecraft Window");
            translationBuilder.add("sibyl.midnightconfig.category.player", "The Player");

            registerConfig(translationBuilder, "render", "Hotbar");
            registerConfig(translationBuilder, "render", "Scoreboard");
            registerConfig(translationBuilder, "render", "Hearts");
            registerConfig(translationBuilder, "render", "Bossbar");
            registerConfig(translationBuilder, "render", "Crosshair");
            registerConfig(translationBuilder, "render", "SwingHand");
            registerConfig(translationBuilder, "render", "PortalOverlay");
            registerConfig(translationBuilder, "render", "PowderSnowOverlay");
            registerConfig(translationBuilder, "render", "PumpkinBlur");

            translationBuilder.add("sibyl.midnightconfig.windowTitle", "Window Title");
            translationBuilder.add("sibyl.midnightconfig.panoramaSpeed", "Panorama Spin Speed");
        }

        private void registerConfig(TranslationBuilder translationBuilder, String prefix, String option) {
            translationBuilder.add("sibyl.midnightconfig." + prefix + option, prefix + option);
        }
    }
}