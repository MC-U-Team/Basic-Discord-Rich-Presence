package info.u_team.basic_discord_rich_presence.event;

import info.u_team.basic_discord_rich_presence.config.ClientConfig;
import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence;
import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence.EnumState;
import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence.State;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;

public class UpdateDiscordEventHandler {
	
	private static void onScreenInitPre(Minecraft client, Screen screen, int scaledWidth, int scaledHeight) {
		if (!DiscordRichPresence.isEnabled()) {
			return;
		}
		if (screen instanceof TitleScreen || screen instanceof SelectWorldScreen || screen instanceof JoinMultiplayerScreen) {
			final State state = DiscordRichPresence.getCurrent();
			if (state == null || state.getState() != EnumState.MENU) {
				DiscordRichPresence.setIdling();
			}
		}
	}
	
	private static void onEntityJoinLevel(Entity entity, ClientLevel level) {
		if (!DiscordRichPresence.isEnabled()) {
			return;
		}
		if (entity instanceof LocalPlayer player) {
			if (player.getUUID().equals(Minecraft.getInstance().player.getUUID())) {
				DiscordRichPresence.setDimension(level);
			}
		}
	}
	
	public static void register() {
		ScreenEvents.BEFORE_INIT.register(UpdateDiscordEventHandler::onScreenInitPre);
		ClientEntityEvents.ENTITY_LOAD.register(UpdateDiscordEventHandler::onEntityJoinLevel);
		
		if (ClientConfig.getInstance().discordRichPresence.get()) {
			DiscordRichPresence.start();
		}
	}
}
