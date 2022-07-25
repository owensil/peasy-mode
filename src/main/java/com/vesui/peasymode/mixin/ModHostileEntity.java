package com.vesui.peasymode.mixin;

import net.minecraft.entity.mob.HostileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HostileEntity.class)
public abstract class ModHostileEntity {
    @Inject(method = "isDisallowedInPeaceful", at = @At("HEAD"), cancellable = true, expect = 1)
    protected void onIsDisallowedInPeaceful(CallbackInfoReturnable<Boolean> ci) {
        ci.setReturnValue(false);
    }
}
