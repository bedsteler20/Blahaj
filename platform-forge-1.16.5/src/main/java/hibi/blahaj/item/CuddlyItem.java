package hibi.blahaj.item;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import java.util.List;

public class CuddlyItem extends Item {
	public static final String OWNER_KEY = "Owner";

	private final TextComponent subtitle;

	public CuddlyItem(Properties props, String subtitle) {
		super(props);
		this.subtitle = subtitle == null ? null : (TextComponent) new TranslationTextComponent(subtitle).withStyle(TextFormatting.GRAY);
	}

	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flags) {
		if (this.subtitle != null) {
			tooltip.add(this.subtitle);
		}
		if (stack.hasTag()) {
			CompoundNBT nbt = stack.getTag();
			assert nbt != null;
			String owner = nbt.getString(OWNER_KEY);
			if (owner.isEmpty()) {
				return;
			}
			TextComponent ownerText = new StringTextComponent(owner);
			if (stack.hasCustomHoverName()) {
				tooltip.add(new TranslationTextComponent("tooltip.blahaj.owner.rename", this.getName(stack), ownerText).withStyle(TextFormatting.GRAY));
			} else {
				tooltip.add(new TranslationTextComponent("tooltip.blahaj.owner.craft", ownerText).withStyle(TextFormatting.GRAY));
			}
		}
	}

	@Override
	public void onCraftedBy(ItemStack stack, World world, PlayerEntity player) {
		if (player != null) { // compensate for auto-crafter mods
			CompoundNBT nbt = stack.getOrCreateTag();
			nbt.putString(OWNER_KEY, player.getName().getString());
		}
		super.onCraftedBy(stack, world, player);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return 0.25f;
	}

	@Override
	public UseAction getUseAnimation(ItemStack p_77661_1_) {
		return UseAction.CROSSBOW;
	}

}
