package com.peak.sibyl.impl.event;

import com.peak.sibyl.impl.SibylConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
public class CoordinateReadoutEvent implements HudRenderCallback {

    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        if (SibylConfig.hudCoordinateReadout) {
            drawContext.drawCenteredTextWithShadow(
                    MinecraftClient.getInstance().textRenderer,
                    Text.literal("[" + getPosition(player) + "]"),
                    drawContext.getScaledWindowWidth() - 65,
                    drawContext.getScaledWindowHeight() / 2  + 100,
                    0xffffff
            );
        }
    }

    private String getPosition(PlayerEntity player) {
        return player.getBlockX() + ", " + player.getBlockY() + ", " + player.getBlockZ();
    }
}
