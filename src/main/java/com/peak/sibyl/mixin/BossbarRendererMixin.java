package com.peak.sibyl.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.peak.sibyl.core.SibylConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.BossBarHud;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Chemthunder
 */
@Mixin(value = BossBarHud.class)
public abstract class BossbarRendererMixin {

    @WrapMethod(method = "render")
    private void sibyl$renderBossbar(DrawContext context, Operation<Void> original) {
        if (SibylConfig.renderBossbar) {
            original.call(context);
        }
    }
}
