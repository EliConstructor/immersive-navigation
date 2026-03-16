package me.eliq.immersivenavigation.mixin.client;

import me.eliq.immersivenavigation.ImmersiveNavigation;
import net.minecraft.client.render.item.property.numeric.CompassProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HeldItemContext;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CompassProperty.class)
public class CompassSpawnMixin {
	@Inject(method = "getValue", at = @At("HEAD"), cancellable = true)
	private void getNorthValue(ItemStack stack, ClientWorld world, HeldItemContext context, int seed, CallbackInfoReturnable<Float> cir) {
		if (!ImmersiveNavigation.config.compassPointsNorth) return;
		if (world == null || context == null) return;
		if (stack.get(DataComponentTypes.LODESTONE_TRACKER) != null) return;

		Vec3d pos = context.getEntityPos();
		double targetAngle = Math.atan2(-1000000.0, 0.0) / (Math.PI * 2.0);
		double rotation = MathHelper.floorMod(context.getBodyYaw() / 360.0, 1.0);

		cir.setReturnValue(MathHelper.floorMod((float)(0.5 - (rotation - 0.25 - targetAngle)), 1.0F));
	}
}