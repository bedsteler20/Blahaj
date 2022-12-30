package hibi.blahaj.event;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

public class ChanceLootModifier extends LootModifier {
	Item item;
	float chance;

	protected ChanceLootModifier(ILootCondition[] conditionsIn, Item item, float chance) {
		super(conditionsIn);
		this.item = item;
		this.chance = chance;
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		if (context.getRandom().nextFloat() > chance) {
			generatedLoot.add(new ItemStack(item, 1));
		}
		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<ChanceLootModifier> {
		@Override
		public ChanceLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(object.get("item").getAsString()));
			float chance = object.get("chance").getAsFloat();
			return new ChanceLootModifier(ailootcondition, item, chance);
		}

		@Override
		public JsonObject write(ChanceLootModifier instance) {
			JsonObject object = makeConditions(instance.conditions);
			object.addProperty("item", ForgeRegistries.ITEMS.getKey(instance.item).toString());
			object.addProperty("chance", instance.chance);
			return object;
		}
	}
}
