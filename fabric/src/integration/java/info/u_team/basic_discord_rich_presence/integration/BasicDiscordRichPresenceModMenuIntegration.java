package info.u_team.basic_discord_rich_presence.integration;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import info.u_team.basic_discord_rich_presence.config.ClientConfig;
import info.u_team.basic_discord_rich_presence.screen.DiscordConfigScreen;

public class BasicDiscordRichPresenceModMenuIntegration implements ModMenuApi {
	
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return screen -> new DiscordConfigScreen(screen, ClientConfig.getInstance().discordRichPresence);
	}
	
}
