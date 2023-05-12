package info.u_team.basic_discord_rich_presence;

import info.u_team.basic_discord_rich_presence.init.BasicDiscordRichPresenceClientConstruct;
import net.fabricmc.api.ClientModInitializer;

public class BasicDiscordRichPresenceMod implements ClientModInitializer {
	
	public static final String MODID = BasicDiscordRichPresenceReference.MODID;
	
	@Override
	public void onInitializeClient() {
		BasicDiscordRichPresenceClientConstruct.construct();
	}
	
}
