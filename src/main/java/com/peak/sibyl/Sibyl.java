package com.peak.sibyl;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sibyl implements ClientModInitializer {
	public static final String MOD_ID = "sibyl";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public void onInitializeClient() {
        LOGGER.info("Sibyl has initialized!");

        MidnightConfig.init(MOD_ID, Config.class);
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
}