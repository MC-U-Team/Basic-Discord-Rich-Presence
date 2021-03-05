package info.u_team.basic_discord_rich_presence.data.provider;

import info.u_team.basic_discord_rich_presence.BasicDiscordRichPresenceMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class BasicDiscordRichPresenceEnLanguageProvider extends LanguageProvider {
	
	public BasicDiscordRichPresenceEnLanguageProvider(DataGenerator generator) {
		super(generator, BasicDiscordRichPresenceMod.MODID, "en_us");
	}
	
	@Override
	protected void addTranslations() {
		final String discordConfigScreen = "screen.basicdiscordrichpresence.config.discord.";
		add(discordConfigScreen + "title", "U Team Core Discord Config Options");
		add(discordConfigScreen + "on", "Discord connection is on");
		add(discordConfigScreen + "off", "Discord connection is off");
		add(discordConfigScreen + "done", "Done");
	}
	
}
