package com.peak.sibyl.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class SibylData implements DataGeneratorEntrypoint {
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(Lang::new);
    }

    public static class Lang extends FabricLanguageProvider {
        public Lang(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(dataOutput, registryLookup);
        }

        public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
            translationBuilder.add("sibyl.title_text", "Sibyl#%s");

            translationBuilder.add("sibyl.midnightconfig.title", "Sibyl");
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
            translationBuilder.add("sibyl.midnightconfig.customDeathMessage", "Custom Death Message");
            translationBuilder.add("sibyl.midnightconfig.customSplashMessage", "Custom Splash Message");
            translationBuilder.add("sibyl.midnightconfig.modInfoOnTitleScreen", "Mod Information on Title Screen");
            translationBuilder.add("sibyl.midnightconfig.hudCoordinateReadout", "Coordinates Display");
        }

        private void registerConfig(TranslationBuilder translationBuilder, String prefix, String option) {
            translationBuilder.add("sibyl.midnightconfig." + prefix + option, prefix + option);
        }
    }
}
