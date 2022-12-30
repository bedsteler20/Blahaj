package hibi.blahaj.mixin;

import hibi.blahaj.item.CuddlyItem;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.BipedModel.ArmPose;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PlayerRenderer.class, remap = false)
public class PlayerRendererMixin {

	@Inject(
			method = "getArmPose",
			at = @At("TAIL"),
			cancellable = true
	)
	private static void getArmPose(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedModel.ArmPose> ci) {
		ItemStack lv = player.getItemInHand(hand);
		if (lv.getItem() instanceof CuddlyItem) {
			ci.setReturnValue(ArmPose.CROSSBOW_HOLD);
			ci.cancel();
		}
	}
//	@Inject(
//			method = "scale(Lnet/minecraft/client/entity/player/AbstractClientPlayerEntity;Lcom/mojang/blaze3d/matrix/MatrixStack;F)V",
//			at = @At("HEAD")
//	)
//	protected void scale(AbstractClientPlayerEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_, CallbackInfo ci) {
//		System.out.println("afgdsofysdf");
//	}


}
