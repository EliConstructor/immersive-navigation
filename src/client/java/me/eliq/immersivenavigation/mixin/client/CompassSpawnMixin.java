package me.eliq.immersivenavigation.mixin.client;

import me.eliq.immersivenavigation.ImmersiveNavigation;
import net.minecraft.item.CompassItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CompassItem.class)
public class CompassSpawnMixin {
    @Inject(method = "createSpawnPos", at = @At("HEAD"), cancellable = true)
    private static void createNorthPos(World world, CallbackInfoReturnable<GlobalPos> cir) {
        if (ImmersiveNavigation.config.compassPointsNorth)
            cir.setReturnValue(GlobalPos.create(world.getRegistryKey(), new BlockPos(0,0,Integer.MIN_VALUE)));
    }
}
