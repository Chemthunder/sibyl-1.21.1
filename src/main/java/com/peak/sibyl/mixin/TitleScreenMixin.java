package com.peak.sibyl.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.peak.sibyl.impl.Sibyl;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    @Shadow private float backgroundAlpha;

    @Shadow
    @Nullable
    private SplashTextRenderer splashText;

    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void sibyl$modOnTitleScreen(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (Sibyl.Config.modInfoOnTitleScreen) {
            int color = ColorHelper.Argb.withAlpha(
                    (int) this.backgroundAlpha * 255,
                    0xc6fc6f
            );

            context.drawTextWithBackground(
                    this.textRenderer,
                    Text.translatable(
                            "sibyl.title_text", Sibyl.getModData().getVersion()
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
    private void mindsEye$slowDownPanorama(RotatingCubeMapRenderer instance, DrawContext context, int width, int height, float alpha, float tickDelta, Operation<Void> original) {
        original.call(instance, context, width, height, alpha, tickDelta + Sibyl.Config.panoramaSpeed);
    }
}
