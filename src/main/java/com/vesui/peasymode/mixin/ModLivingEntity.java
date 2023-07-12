package com.vesui.peasymode.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ModLivingEntity extends Entity {

    public ModLivingEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "canTarget(Lnet/minecraft/entity/LivingEntity;)Z", at = @At("HEAD"), cancellable = true, expect = 1)
    public void onCanTarget(LivingEntity target, CallbackInfoReturnable<Boolean> ci) {
        // Can't target if target is a player and difficulty is peaceful or easy
        boolean readable = this.getWorld().getDifficulty() == Difficulty.PEACEFUL || this.getWorld().getDifficulty() == Difficulty.EASY;
        ci.setReturnValue((!(target instanceof PlayerEntity) || !readable) && target.canTakeDamage());
    }
}
