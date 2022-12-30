package hibi.blahaj;

import hibi.blahaj.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("blahaj")
public class Common {
	public static final String MOD_ID = "blahaj";
	private static final Logger LOGGER = LogManager.getLogger();


	public Common() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModItems.register(eventBus);
		MinecraftForge.EVENT_BUS.register(this);
	}

}
