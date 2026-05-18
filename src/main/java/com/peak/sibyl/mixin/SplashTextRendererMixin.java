package com.peak.sibyl.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.peak.sibyl.impl.SibylConfig;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Chemthunder
 */
@Mixin(value = SplashTextRenderer.class)
public abstract class SplashTextRendererMixin {

    @WrapOperation(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawCenteredTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)V"
            )
    )
    private void sibyl$customSplashMessage(DrawContext instance, TextRenderer textRenderer, String text, int centerX, int y, int color, Operation<Void> original) {
        if (SibylConfig.customSplashMessage.isBlank()) {
            original.call(instance, textRenderer, text, centerX, y, color);
        } else {
            original.call(instance, textRenderer, SibylConfig.customSplashMessage, centerX, y, color);
        }
    }
}
