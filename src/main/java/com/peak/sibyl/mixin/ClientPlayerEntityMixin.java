package com.peak.sibyl.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.peak.sibyl.impl.Sibyl;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Chemthunder
 */
@Mixin(value = ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {

    @WrapMethod(method = "swingHand")
    private void sibyl$swingHand(Hand hand, Operation<Void> original) {
        if (Sibyl.Config.renderSwingHand) {
            original.call(hand);
        }
    }
}
