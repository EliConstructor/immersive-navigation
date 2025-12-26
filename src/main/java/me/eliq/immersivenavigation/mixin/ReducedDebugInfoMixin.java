package me.eliq.immersivenavigation.mixin;

import me.eliq.immersivenavigation.ImmersiveNavigation;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class ReducedDebugInfoMixin {
    @Inject(method = "hasReducedDebugInfo", at = @At("RETURN"), cancellable = true)
    public void hasReducedDebugInfo(CallbackInfoReturnable<Boolean> cir) {
        var player = (PlayerEntity) (Object) this;
        if (!player.isCreativeLevelTwoOp() && ImmersiveNavigation.config.noSurvivalDebug){
            cir.setReturnValue(true);
        }
    }
}