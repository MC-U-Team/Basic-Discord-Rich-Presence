package info.u_team.basic_discord_rich_presence.init;

import info.u_team.basic_discord_rich_presence.config.ClientConfig;
import info.u_team.basic_discord_rich_presence.event.UpdateDiscordEventHandler;
import info.u_team.basic_discord_rich_presence.screen.DiscordConfigScreen;
import net.minecraftforge.client.ConfigGuiHandler.ConfigGuiFactory;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Type;

public class BasicDiscordRichPresenceClientConstruct {
	
	public static void construct() {
		ModLoadingContext.get().registerConfig(Type.CLIENT, ClientConfig.CONFIG);
		ModLoadingContext.get().registerExtensionPoint(ConfigGuiFactory.class, () -> new ConfigGuiFactory((minecraft, screen) -> new DiscordConfigScreen(screen)));
		
		UpdateDiscordEventHandler.registerMod(Bus.MOD.bus().get());
		UpdateDiscordEventHandler.registerForge(Bus.FORGE.bus().get());
	}
	
}
