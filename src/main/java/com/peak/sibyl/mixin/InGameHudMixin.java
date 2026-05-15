package com.peak.sibyl.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.peak.sibyl.Sibyl;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.ScoreboardObjective;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Chemthunder
 */
@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @WrapMethod(method = "renderHotbar")
    private void sibyl$renderHotbar(DrawContext context, RenderTickCounter tickCounter, Operation<Void> original) {
        if (Sibyl.Config.renderHotbar) {
            original.call(context, tickCounter);
        }
    }

    @WrapMethod(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V")
    private void sibyl$renderScoreboard(DrawContext context, RenderTickCounter tickCounter, Operation<Void> original) {
        if (Sibyl.Config.renderScoreboard) {
            original.call(context, tickCounter);
        }
    }

    @WrapMethod(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V")
    private void sibyl$renderScoreboard1(DrawContext context, ScoreboardObjective objective, Operation<Void> original) {
        if (Sibyl.Config.renderScoreboard) {
            original.call(context, objective);
        }
    }

    @WrapMethod(method = "renderCrosshair")
    private void sibyl$renderCrosshair(DrawContext context, RenderTickCounter tickCounter, Operation<Void> original) {
        if (Sibyl.Config.renderCrosshair) {
            original.call(context, tickCounter);
        }
    }

    @WrapMethod(method = "renderHealthBar")
    private void sibyl$renderMainHud0(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, Operation<Void> original) {
        if (Sibyl.Config.renderHearts) {
            original.call(context, player, x, y, lines, regeneratingHeartIndex, maxHealth, lastHealth, health, absorption, blinking);
        }
    }
}
