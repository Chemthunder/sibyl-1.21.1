package com.peak.sibyl.mixin.toast;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.peak.sibyl.core.SibylConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.toast.AdvancementToast;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Chemthunder
 */
@Mixin(value = SystemToast.class)
public abstract class SystemToastMixin {

    @WrapMethod(method = "draw")
    private Toast.Visibility sibyl$override(DrawContext context, ToastManager manager, long startTime, Operation<Toast.Visibility> original) {
        return SibylConfig.shouldShowSystemToasts ? original.call(context, manager, startTime) : Toast.Visibility.HIDE;
    }
}
