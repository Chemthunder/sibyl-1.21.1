package com.peak.sibyl.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.peak.sibyl.core.SibylConfig;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundManager;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Chemthunder
 */
@Mixin(value = SoundManager.class)
public abstract class SoundManagerMixin {

    @WrapMethod(method = "play(Lnet/minecraft/client/sound/SoundInstance;)V")
    private void sibyl$override(SoundInstance sound, Operation<Void> original) {
        if (!SibylConfig.fullDeafen) {
            original.call(sound);
        }
    }
}
