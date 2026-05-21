package com.peak.sibyl.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.peak.sibyl.core.SibylInternal;
import com.peak.sibyl.core.SibylConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Chemthunder
 */
@Mixin(value = TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    @Shadow private float backgroundAlpha;

    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At(value = "HEAD"))
    private void sibyl$modInfoOnTitleScreen(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (SibylConfig.modInfoOnTitleScreen) {
            int color = ColorHelper.Argb.withAlpha(
                    (int) this.backgroundAlpha * 255,
                    0xc6fc6f
            );

            context.drawTextWithBackground(
                    this.textRenderer,
                    Text.translatable(
                            "sibyl.title_text", SibylInternal.getModData().getVersion()
                    ),
                    2,
                    this.height - 20,
                    16,
                    color
            );
        }
    }

    @WrapOperation(
            method = "renderPanoramaBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/RotatingCubeMapRenderer;render(Lnet/minecraft/client/gui/DrawContext;IIFF)V"
            )
    )
    private void sibyl$customPanoramaSpeed(RotatingCubeMapRenderer instance, DrawContext context, int width, int height, float alpha, float tickDelta, Operation<Void> original) {
        original.call(
                instance,
                context,
                width,
                height,
                alpha,
                tickDelta + SibylConfig.panoramaSpeed
        );
    }
}
