package info.u_team.basic_discord_rich_presence;

import info.u_team.basic_discord_rich_presence.config.ClientConfig;
import info.u_team.basic_discord_rich_presence.init.BasicDiscordRichPresenceClientConstruct;
import net.fabricmc.api.ClientModInitializer;

public class BasicDiscordRichPresenceMod implements ClientModInitializer {
	
	public static final String MODID = BasicDiscordRichPresenceReference.MODID;
	
	@Override
	public void onInitializeClient() {
		System.out.println(ClientConfig.getInstance().discordRichPresence.get());
		ClientConfig.getInstance().discordRichPresence.set(!ClientConfig.getInstance().discordRichPresence.get());
		BasicDiscordRichPresenceClientConstruct.construct();
	}
	
}
