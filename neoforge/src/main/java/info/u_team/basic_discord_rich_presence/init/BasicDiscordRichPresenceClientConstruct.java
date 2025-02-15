package info.u_team.basic_discord_rich_presence.init;

import info.u_team.basic_discord_rich_presence.config.ClientConfig;
import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence;
import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence.DetailsCallback;
import info.u_team.basic_discord_rich_presence.event.UpdateDiscordEventHandler;
import info.u_team.basic_discord_rich_presence.screen.DiscordConfigScreen;
import info.u_team.basic_discord_rich_presence.util.ConfigValueHolder;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig.Type;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

public class BasicDiscordRichPresenceClientConstruct {
	
	public static void construct() {
		ModLoadingContext.get().getActiveContainer().registerConfig(Type.CLIENT, ClientConfig.CONFIG);
		ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (minecraft, screen) -> new DiscordConfigScreen(screen, new ConfigValueHolder<>(ClientConfig.getInstance().discordRichPresence, ClientConfig.getInstance().discordRichPresence::set)));
		
		DiscordRichPresence.setDetailsCallback(new DetailsCallback() {
			
			@Override
			public String getMinecraftVersion() {
				return FMLLoader.versionInfo().mcVersion();
			}
			
			@Override
			public String getModSize() {
				return String.valueOf(ModList.get().size());
			}
			
		});
		
		UpdateDiscordEventHandler.registerMod(ModLoadingContext.get().getActiveContainer().getEventBus());
		UpdateDiscordEventHandler.registerNeoforge(NeoForge.EVENT_BUS);
	}
	
}
