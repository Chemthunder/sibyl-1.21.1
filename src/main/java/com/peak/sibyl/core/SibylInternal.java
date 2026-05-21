package com.peak.sibyl.core;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
public class SibylInternal implements ClientModInitializer {
    public static final String MOD_ID = "sibyl";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public void onInitializeClient() {
        try {
            LOGGER.info("Sibyl has initialized!");

            MidnightConfig.init(
                MOD_ID,
                SibylConfig.class
            );

            SibylEvents.register();
        } catch (Exception e) {
            LOGGER.error("Sibyl initialization has failed! {}", e.getLocalizedMessage());
        }
    }

    public static ModMetadata getModData() {
        return FabricLoader.getInstance().getModContainer(MOD_ID).get().getMetadata();
    }

    public static int convertToHex(String hexString) {
        hexString = hexString.replace("#", "");
        return Integer.parseInt(hexString, 16);
    }
}