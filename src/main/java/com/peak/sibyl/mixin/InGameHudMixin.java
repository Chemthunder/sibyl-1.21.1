package com.peak.sibyl.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.systems.RenderSystem;
import com.peak.sibyl.impl.Sibyl;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Chemthunder
 */
@Mixin(value = InGameHud.class)
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
    private void sibyl$renderHearts(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, Operation<Void> original) {
        if (Sibyl.Config.renderHearts) {
            original.call(context, player, x, y, lines, regeneratingHeartIndex, maxHealth, lastHealth, health, absorption, blinking);
        }
    }

    @WrapMethod(method = "renderPortalOverlay")
    private void sibyl$renderPortalOverlay(DrawContext context, float nauseaStrength, Operation<Void> original) {
        if (Sibyl.Config.renderPortalOverlay) {
            original.call(context, nauseaStrength);
        }
    }

    @WrapOperation(
            method = "renderMiscOverlays",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V",
                    ordinal = 0
            )
    )
    private void sibyl$pwoder(InGameHud instance, DrawContext context, Identifier texture, float opacity, Operation<Void> original) {
        if (Sibyl.Config.renderPowderSnowOverlay) {
            original.call(instance, context, texture, opacity);
        }
    }
}
