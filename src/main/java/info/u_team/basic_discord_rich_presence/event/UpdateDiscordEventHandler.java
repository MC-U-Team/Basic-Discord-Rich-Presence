package info.u_team.basic_discord_rich_presence.event;

import info.u_team.basic_discord_rich_presence.config.ClientConfig;
import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence;
import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence.EnumState;
import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence.State;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.client.event.ScreenEvent.InitScreenEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class UpdateDiscordEventHandler {
	
	private static void setup(FMLCommonSetupEvent event) {
		if (ClientConfig.getInstance().discordRichPresence.get()) {
			DiscordRichPresence.start();
		}
	}
	
	private static void onInitGuiPre(InitScreenEvent.Pre event) {
		if (!DiscordRichPresence.isEnabled()) {
			return;
		}
		if (event.getScreen() instanceof TitleScreen || event.getScreen() instanceof SelectWorldScreen || event.getScreen() instanceof JoinMultiplayerScreen) {
			final State state = DiscordRichPresence.getCurrent();
			if (state == null || state.getState() != EnumState.MENU) {
				DiscordRichPresence.setIdling();
			}
		}
	}
	
	private static void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (!DiscordRichPresence.isEnabled()) {
			return;
		}
		if (event.getEntity() instanceof LocalPlayer) {
			final LocalPlayer player = (LocalPlayer) event.getEntity();
			if (player.getUUID().equals(Minecraft.getInstance().player.getUUID())) {
				DiscordRichPresence.setDimension(player.getLevel());
			}
		}
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(UpdateDiscordEventHandler::setup);
	}
	
	public static void registerForge(IEventBus bus) {
		bus.addListener(UpdateDiscordEventHandler::onInitGuiPre);
		bus.addListener(UpdateDiscordEventHandler::onEntityJoinWorld);
	}
}
