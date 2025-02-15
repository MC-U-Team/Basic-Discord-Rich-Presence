package info.u_team.basic_discord_rich_presence;

import info.u_team.basic_discord_rich_presence.init.BasicDiscordRichPresenceClientConstruct;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(BasicDiscordRichPresenceMod.MODID)
public class BasicDiscordRichPresenceMod {
	
	public static final String MODID = BasicDiscordRichPresenceReference.MODID;
	
	public BasicDiscordRichPresenceMod() {
		if (FMLEnvironment.dist == Dist.CLIENT) {
			BasicDiscordRichPresenceClientConstruct.construct();
		}
	}
	
}
