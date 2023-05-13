package info.u_team.basic_discord_rich_presence.init;

import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence;
import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence.DetailsCallback;
import info.u_team.basic_discord_rich_presence.event.UpdateDiscordEventHandler;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;

public class BasicDiscordRichPresenceClientConstruct {
	
	public static void construct() {
		DiscordRichPresence.setDetailsCallback(new DetailsCallback() {
			
			@Override
			public String getMinecraftVersion() {
				return SharedConstants.getCurrentVersion().getName();
			}
			
			@Override
			public String getModSize() {
				return String.valueOf(FabricLoader.getInstance().getAllMods().size());
			}
			
		});
		
		UpdateDiscordEventHandler.register();
	}
	
}
