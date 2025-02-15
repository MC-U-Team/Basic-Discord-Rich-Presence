package info.u_team.basic_discord_rich_presence;

import info.u_team.basic_discord_rich_presence.init.BasicDiscordRichPresenceClientConstruct;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.IExtensionPoint.DisplayTest;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(BasicDiscordRichPresenceMod.MODID)
public class BasicDiscordRichPresenceMod {
	
	public static final String MODID = BasicDiscordRichPresenceReference.MODID;
	
	@SuppressWarnings("removal")
	public BasicDiscordRichPresenceMod() {
		ModLoadingContext.get().registerExtensionPoint(DisplayTest.class, () -> new DisplayTest(() -> DisplayTest.IGNORESERVERONLY, (remoteVersion, network) -> true));
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> BasicDiscordRichPresenceClientConstruct::construct);
	}
	
}
