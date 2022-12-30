package hibi.blahaj.mixin;

import hibi.blahaj.item.CuddlyItem;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedModel.class)
public class BipedModelMixin {
	@Shadow
	public ModelRenderer rightArm;
	@Shadow
	public ModelRenderer leftArm;

	@Inject(
			method = "poseRightArm",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/renderer/model/ModelHelper;animateCrossbowHold(Lnet/minecraft/client/renderer/model/ModelRenderer;Lnet/minecraft/client/renderer/model/ModelRenderer;Lnet/minecraft/client/renderer/model/ModelRenderer;Z)V",
					shift = At.Shift.AFTER
			),
			cancellable = true
	)
	public void poseArms(LivingEntity entity, CallbackInfo ci) {
		if (entity.getMainHandItem().getItem() instanceof CuddlyItem || entity.getMainHandItem().getItem() instanceof CuddlyItem) {
			this.rightArm.y = -0.95F;
			this.rightArm.z = (float) (-Math.PI / 8);
			this.leftArm.y = -0.90F;
			this.leftArm.z = (float) (Math.PI / 8);
			ci.cancel();
		}
	}
}
