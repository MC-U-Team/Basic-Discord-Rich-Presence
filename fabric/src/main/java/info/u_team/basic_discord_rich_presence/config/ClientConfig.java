package info.u_team.basic_discord_rich_presence.config;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import info.u_team.basic_discord_rich_presence.BasicDiscordRichPresenceMod;
import info.u_team.basic_discord_rich_presence.BasicDiscordRichPresenceReference;
import info.u_team.basic_discord_rich_presence.util.ConfigValueHolder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.Util;

public class ClientConfig {
	
	private static final ClientConfig INSTANCE = new ClientConfig();
	
	public static ClientConfig getInstance() {
		return INSTANCE;
	}
	
	public final ConfigValueHolder<Boolean> discordRichPresence;
	
	private final Path path = FabricLoader.getInstance().getConfigDir().resolve(BasicDiscordRichPresenceMod.MODID + ".properties");
	private final Properties properties;
	
	private ClientConfig() {
		properties = new Properties();
		
		if (Files.exists(path)) {
			load();
		}
		
		properties.computeIfAbsent("discordRichPresence", unused -> "true");
		
		discordRichPresence = new ConfigValueHolder<>(() -> {
			return Boolean.valueOf(properties.getProperty("discordRichPresence", "true"));
		}, value -> {
			properties.put("discordRichPresence", value.toString());
			Util.ioPool().submit(this::save);
		});
		
		if (!Files.exists(path)) {
			save();
		}
	}
	
	private void load() {
		try (final Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			properties.load(reader);
		} catch (final IOException ex) {
			BasicDiscordRichPresenceReference.LOGGER.warn("Could not read property file '" + path.toAbsolutePath() + "'", ex);
		}
	}
	
	private void save() {
		try (final BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
			properties.store(writer, "Configuration file to Basic Discord Rich Presence Mod");
		} catch (final IOException ex) {
			BasicDiscordRichPresenceReference.LOGGER.warn("Could not read property file '" + path.toAbsolutePath() + "'", ex);
		}
	}
}
