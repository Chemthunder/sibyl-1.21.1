package com.peak.sibyl.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.peak.sibyl.core.SibylConfig;
import net.minecraft.client.util.Icons;
import net.minecraft.client.util.Window;
import net.minecraft.resource.ResourcePack;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Chemthunder
 */
@Mixin(value = Window.class)
public abstract class WindowMixin {

    @WrapMethod(method = "setIcon")
    private void sibyl$customWindowIcon(ResourcePack resourcePack, Icons icons, Operation<Void> original) {
        original.call(resourcePack, SibylConfig.windowIcon == SibylConfig.WindowIconSet.RELEASE ? Icons.RELEASE : Icons.SNAPSHOT);
    }
}
