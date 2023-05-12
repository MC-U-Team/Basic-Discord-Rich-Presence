package info.u_team.basic_discord_rich_presence.init;

import info.u_team.basic_discord_rich_presence.config.ClientConfig;
import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence;
import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence.DetailsCallback;
import info.u_team.basic_discord_rich_presence.event.UpdateDiscordEventHandler;
import info.u_team.basic_discord_rich_presence.screen.DiscordConfigScreen;
import info.u_team.basic_discord_rich_presence.screen.DiscordConfigScreen.ConfigValueHolder;
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.versions.mcp.MCPVersion;

public class BasicDiscordRichPresenceClientConstruct {
	
	public static void construct() {
		ModLoadingContext.get().registerConfig(Type.CLIENT, ClientConfig.CONFIG);
		ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((minecraft, screen) -> new DiscordConfigScreen(screen, new ConfigValueHolder<>(ClientConfig.getInstance().discordRichPresence, ClientConfig.getInstance().discordRichPresence::set))));
		
		DiscordRichPresence.setDetailsCallback(new DetailsCallback() {
			
			@Override
			public String getMinecraftVersion() {
				return MCPVersion.getMCVersion();
			}
			
			@Override
			public String getModSize() {
				return String.valueOf(ModList.get().size());
			}
			
		});
		
		UpdateDiscordEventHandler.registerMod(Bus.MOD.bus().get());
		UpdateDiscordEventHandler.registerForge(Bus.FORGE.bus().get());
	}
	
}
