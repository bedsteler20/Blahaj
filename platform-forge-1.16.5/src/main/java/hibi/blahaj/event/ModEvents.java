package hibi.blahaj.event;

import hibi.blahaj.Common;
import hibi.blahaj.item.ModItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.List;

@Mod.EventBusSubscriber(modid = Common.MOD_ID)
public class ModEvents {
	@SubscribeEvent
	public static void addCustomTrades(VillagerTradesEvent event) {
		if (event.getType() == VillagerProfession.SHEPHERD) {
			Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();
			ItemStack stack = new ItemStack(ModItems.KLAPPAR_HAJ.get(), 1);
			int villagerLevel = 5;

			trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 15),
					stack, 2, 30, 0.1F));
		}
	}

	@SubscribeEvent
	public static void addLootModifiers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
		event.getRegistry().registerAll(
				new ChanceLootModifier.Serializer().setRegistryName(
						new ModelResourceLocation(Common.MOD_ID, "chance_loot_modifier"))
		);
	}
}
