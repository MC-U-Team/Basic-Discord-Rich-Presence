package info.u_team.basic_discord_rich_presence.screen;

import com.mojang.blaze3d.vertex.PoseStack;

import info.u_team.basic_discord_rich_presence.config.ClientConfig;
import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class DiscordConfigScreen extends Screen {
	
	private final Screen screenBefore;
	
	public DiscordConfigScreen(Screen screenBefore) {
		super(Component.translatable("screen.basicdiscordrichpresence.config.discord.title"));
		this.screenBefore = screenBefore;
	}
	
	@Override
	protected void init() {
		final BooleanValue discordRichPresence = ClientConfig.getInstance().discordRichPresence;
		
		final Component on = Component.translatable("screen.basicdiscordrichpresence.config.discord.on");
		final Component off = Component.translatable("screen.basicdiscordrichpresence.config.discord.off");
		
		addRenderableWidget(Button.builder(discordRichPresence.get() ? on : off, button -> {
			discordRichPresence.set(!discordRichPresence.get());
			
			if (discordRichPresence.get() && !DiscordRichPresence.isEnabled()) {
				DiscordRichPresence.start();
				if (minecraft.level == null) {
					DiscordRichPresence.setIdling();
				} else {
					DiscordRichPresence.setDimension(minecraft.level);
				}
			} else if (!discordRichPresence.get() && DiscordRichPresence.isEnabled()) {
				DiscordRichPresence.stop();
			}
			button.setMessage(discordRichPresence.get() ? on : off);
		}).pos(width / 2 - 100, 50).size(200, 20).build());
		
		addRenderableWidget(Button.builder(Component.translatable("screen.basicdiscordrichpresence.config.discord.done"), button -> onClose()).pos(width / 2 - 100, 80).size(200, 20).build());
	}
	
	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		renderBackground(poseStack);
		drawCenteredString(poseStack, font, title, width / 2, 15, 0xFFFFFF);
		super.render(poseStack, mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void onClose() {
		minecraft.setScreen(screenBefore);
	}
	
}
