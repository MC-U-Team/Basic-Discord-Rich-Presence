package info.u_team.basic_discord_rich_presence.init;

import info.u_team.basic_discord_rich_presence.screen.DiscordConfigScreen;
import info.u_team.u_team_core.intern.discord.UpdateDiscordEventHandler;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

public class BasicDiscordRichPresenceClientConstruct {
	
	public static void construct() {
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (minecraft, screen) -> new DiscordConfigScreen(screen));
		
		UpdateDiscordEventHandler.registerMod(Bus.MOD.bus().get());
		UpdateDiscordEventHandler.registerForge(Bus.FORGE.bus().get());
	}
	
}
