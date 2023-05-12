package info.u_team.basic_discord_rich_presence.screen;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.mojang.blaze3d.vertex.PoseStack;

import info.u_team.basic_discord_rich_presence.discord.DiscordRichPresence;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class DiscordConfigScreen extends Screen {
	
	private final Screen screenBefore;
	private final ConfigValueHolder<Boolean> configValueHolder;
	
	public DiscordConfigScreen(Screen screenBefore, ConfigValueHolder<Boolean> configValueHolder) {
		super(Component.translatable("screen.basicdiscordrichpresence.config.discord.title"));
		this.screenBefore = screenBefore;
		this.configValueHolder = configValueHolder;
	}
	
	@Override
	protected void init() {
		final Component on = Component.translatable("screen.basicdiscordrichpresence.config.discord.on");
		final Component off = Component.translatable("screen.basicdiscordrichpresence.config.discord.off");
		
		addRenderableWidget(Button.builder(configValueHolder.get() ? on : off, button -> {
			configValueHolder.set(!configValueHolder.get());
			
			if (configValueHolder.get() && !DiscordRichPresence.isEnabled()) {
				DiscordRichPresence.start();
				if (minecraft.level == null) {
					DiscordRichPresence.setIdling();
				} else {
					DiscordRichPresence.setDimension(minecraft.level);
				}
			} else if (!configValueHolder.get() && DiscordRichPresence.isEnabled()) {
				DiscordRichPresence.stop();
			}
			button.setMessage(configValueHolder.get() ? on : off);
		}).pos(width / 2 - 100, 50).size(200, 20).build());
		
		addRenderableWidget(Button.builder(Component.translatable("screen.basicdiscordrichpresence.config.discord.done"), button -> onClose()).pos(width / 2 - 100, 80).size(200, 20).build());
	}
	
	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
		renderBackground(poseStack);
		drawCenteredString(poseStack, font, title, width / 2, 15, 0xFFFFFF);
		super.render(poseStack, mouseX, mouseY, partialTick);
	}
	
	@Override
	public void onClose() {
		minecraft.setScreen(screenBefore);
	}
	
	public static record ConfigValueHolder<V> (Supplier<V> getter, Consumer<V> setter) {
		
		public V get() {
			return getter.get();
		}
		
		public void set(V value) {
			setter.accept(value);
		}
	}
	
}
