package com.peak.sibyl.impl;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class Sibyl implements ClientModInitializer, DataGeneratorEntrypoint {
	public static final String MOD_ID = "sibyl";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public void onInitializeClient() {
        LOGGER.info("Sibyl has initialized!");

        MidnightConfig.init(MOD_ID, Config.class);
    }

    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(Lang::new);
    }

    public static class Config extends MidnightConfig {
        private static final String hud = "hud";

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
    }

    public static class Lang extends FabricLanguageProvider {
        public Lang(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(dataOutput, registryLookup);
        }

        public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
            translationBuilder.add("sibyl.midnightconfig.title", "Sibyl");

            registerConfig(translationBuilder, "Hotbar");
            registerConfig(translationBuilder, "Scoreboard");
            registerConfig(translationBuilder, "Hearts");
            registerConfig(translationBuilder, "Bossbar");
            registerConfig(translationBuilder, "Crosshair");
        }

        private void registerConfig(TranslationBuilder translationBuilder, String option) {
            translationBuilder.add("sibyl.midnightconfig.render" + option, "render" + option);
        }
    }
}