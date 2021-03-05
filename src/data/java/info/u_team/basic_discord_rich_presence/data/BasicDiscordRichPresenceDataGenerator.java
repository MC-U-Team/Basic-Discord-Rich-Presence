package info.u_team.basic_discord_rich_presence.data;

import info.u_team.basic_discord_rich_presence.BasicDiscordRichPresenceMod;
import info.u_team.basic_discord_rich_presence.data.provider.BasicDiscordRichPresenceEnLanguageProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = BasicDiscordRichPresenceMod.MODID, bus = Bus.MOD)
public class BasicDiscordRichPresenceDataGenerator {
	
	@SubscribeEvent
	public static void data(GatherDataEvent event) {
		if (event.includeClient()) {
			event.getGenerator().addProvider(new BasicDiscordRichPresenceEnLanguageProvider(event.getGenerator()));
		}
	}
	
}
