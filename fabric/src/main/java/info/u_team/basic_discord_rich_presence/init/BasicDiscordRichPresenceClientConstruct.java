package info.u_team.basic_discord_rich_presence.init;

import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence;
import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence.DetailsCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;

public class BasicDiscordRichPresenceClientConstruct {
	
	public static void construct() {
		DiscordRichPresence.setDetailsCallback(new DetailsCallback() {
			
			@Override
			public String getMinecraftVersion() {
				return Minecraft.getInstance().getLaunchedVersion();
			}
			
			@Override
			public String getModSize() {
				return String.valueOf(FabricLoader.getInstance().getAllMods().size());
			}
			
		});
		
		// UpdateDiscordEventHandler.registerMod(Bus.MOD.bus().get());
		// UpdateDiscordEventHandler.registerForge(Bus.FORGE.bus().get());
	}
	
}
