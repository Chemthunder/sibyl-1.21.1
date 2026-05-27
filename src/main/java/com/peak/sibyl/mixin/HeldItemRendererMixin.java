package com.peak.sibyl.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.peak.sibyl.core.SibylConfig;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Chemthunder
 */
@Mixin(value = HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {

    @WrapOperation(
            method = "renderFirstPersonItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V",
                    ordinal = 0
            )
    )
    private void sibyl$itemSize0(MatrixStack instance, float x, float y, float z, Operation<Void> original) {
        original.call(instance, SibylConfig.itemSize, SibylConfig.itemSize, SibylConfig.itemSize);
    }

    @WrapOperation(
            method = "renderFirstPersonItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V",
                    ordinal = 1
            )
    )
    private void sibyl$itemSize1(MatrixStack instance, float x, float y, float z, Operation<Void> original) {
        original.call(instance, SibylConfig.itemSize, SibylConfig.itemSize, SibylConfig.itemSize);
    }

    @WrapOperation(
            method = "renderFirstPersonItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V",
                    ordinal = 2
            )
    )
    private void sibyl$itemSize2(MatrixStack instance, float x, float y, float z, Operation<Void> original) {
        original.call(instance, SibylConfig.itemSize, SibylConfig.itemSize, SibylConfig.itemSize);
    }
}
