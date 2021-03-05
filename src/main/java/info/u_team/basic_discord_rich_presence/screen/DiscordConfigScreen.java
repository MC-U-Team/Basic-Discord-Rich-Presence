package info.u_team.basic_discord_rich_presence.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import info.u_team.basic_discord_rich_presence.config.ClientConfig;
import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.*;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class DiscordConfigScreen extends Screen {
	
	private final Screen screenBefore;
	
	public DiscordConfigScreen(Screen screenBefore) {
		super(new TranslationTextComponent("screen.basicdiscordrichpresence.config.discord.title"));
		this.screenBefore = screenBefore;
	}
	
	@Override
	protected void init() {
		final BooleanValue discordRichPresence = ClientConfig.getInstance().discordRichPresence;
		
		final ITextComponent on = new TranslationTextComponent("screen.basicdiscordrichpresence.config.discord.on");
		final ITextComponent off = new TranslationTextComponent("screen.basicdiscordrichpresence.config.discord.off");
		
		addButton(new Button(width / 2 - 100, 50, 200, 20, discordRichPresence.get() ? on : off, button -> {
			discordRichPresence.set(!discordRichPresence.get());
			
			if (discordRichPresence.get() && !DiscordRichPresence.isEnabled()) {
				DiscordRichPresence.start();
				if (minecraft.world == null) {
					DiscordRichPresence.setIdling();
				} else {
					DiscordRichPresence.setDimension(minecraft.world);
				}
			} else if (!discordRichPresence.get() && DiscordRichPresence.isEnabled()) {
				DiscordRichPresence.stop();
			}
			
			// toggleDiscordRichPresenceButton.setActivated(discordRichPresence.get());
			button.setMessage(discordRichPresence.get() ? on : off);
		}) {
			
			@Override
			public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
				super.renderButton(matrixStack, mouseX, mouseY, partialTicks);
			}
		});
		
		addButton(new Button(width / 2 - 100, 80, 200, 20, new TranslationTextComponent("screen.basicdiscordrichpresence.config.discord.done"), button -> closeScreen()));
	}
	
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrixStack);
		drawCenteredString(matrixStack, font, title, width / 2, 15, 0xFFFFFF);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		buttons.forEach(widget -> widget.renderToolTip(matrixStack, mouseX, mouseY));
	}
	
	@Override
	public void closeScreen() {
		minecraft.displayGuiScreen(screenBefore);
	}
	
}
