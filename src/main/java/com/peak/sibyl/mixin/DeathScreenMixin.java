package com.peak.sibyl.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.peak.sibyl.core.SibylConfig;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Chemthunder
 */
@Mixin(value = DeathScreen.class)
public abstract class DeathScreenMixin extends Screen {
    protected DeathScreenMixin(Text title) {
        super(title);
    }

    @WrapOperation(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/text/Text;translatable(Ljava/lang/String;)Lnet/minecraft/text/MutableText;"
            )
    )
    private static MutableText sibyl$customDeathScreenTitle(String key, Operation<MutableText> original) {
        if (!SibylConfig.customDeathMessage.isBlank()) {
            return Text.literal(SibylConfig.customDeathMessage);
        }
        return original.call(key);
    }
}
