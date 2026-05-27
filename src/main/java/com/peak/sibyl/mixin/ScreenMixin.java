package com.peak.sibyl.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.peak.sibyl.core.SibylConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Chemthunder
 */
@Mixin(Screen.class)
public abstract class ScreenMixin {

    @WrapOperation(
            method = "renderPanoramaBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/RotatingCubeMapRenderer;render(Lnet/minecraft/client/gui/DrawContext;IIFF)V"
            )
    )
    private void mindsEye$slowDownPanorama(RotatingCubeMapRenderer instance, DrawContext context, int width, int height, float alpha, float tickDelta, Operation<Void> original) {
        original.call(instance, context, width, height, alpha, tickDelta + SibylConfig.panoramaSpeed);
    }
}
