package info.u_team.basic_discord_rich_presence.data;

import info.u_team.basic_discord_rich_presence.BasicDiscordRichPresenceMod;
import info.u_team.basic_discord_rich_presence.data.provider.BasicDiscordRichPresenceEnLanguageProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = BasicDiscordRichPresenceMod.MODID, bus = Bus.MOD)
public class BasicDiscordRichPresenceDataGenerator {
	
	@SubscribeEvent
	public static void data(GatherDataEvent event) {
		event.getGenerator().addProvider(event.includeClient(), new BasicDiscordRichPresenceEnLanguageProvider(event.getGenerator().getPackOutput()));
	}
	
}
