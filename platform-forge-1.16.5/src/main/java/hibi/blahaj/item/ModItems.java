package hibi.blahaj.item;

import hibi.blahaj.Common;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

	public static final DeferredRegister<Item> ITEMS
			= DeferredRegister.create(ForgeRegistries.ITEMS, Common.MOD_ID);

	public static final RegistryObject<Item> BLAHAJ = ITEMS.register("blue_shark", () ->
			new CuddlyItem(
					new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1),
					"item.blahaj.blue_shark.tooltip"));
	public static final RegistryObject<Item> KLAPPAR_HAJ = ITEMS.register("gray_shark", () ->
			new CuddlyItem(
					new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1),
					"item.blahaj.gray_shark.tooltip"));
	public static final RegistryObject<Item> BREAD = ITEMS.register("bread", () ->
			new CuddlyItem(
					new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1),
					null));

	public static void register(IEventBus bus) {
		ITEMS.register(bus);
	}
}
