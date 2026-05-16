package com.peak.sibyl.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.peak.sibyl.impl.SibylConfig;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Chemthunder
 */
@Mixin(value = MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @WrapMethod(method = "getWindowTitle")
    private String sibyl$windowTitle(Operation<String> original) {
        if (!SibylConfig.windowTitle.isBlank()) {
            return SibylConfig.windowTitle;
        }

        return original.call();
    }
}
