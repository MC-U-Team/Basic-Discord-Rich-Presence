package info.u_team.basic_discord_rich_presence;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import info.u_team.basic_discord_rich_presence.init.BasicDiscordRichPresenceClientConstruct;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.IExtensionPoint.DisplayTest;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkConstants;

@Mod(BasicDiscordRichPresenceMod.MODID)
public class BasicDiscordRichPresenceMod {
	
	public static final String MODID = "basicdiscordrichpresence";
	
	public static final Logger LOGGER = LogUtils.getLogger();
	
	public BasicDiscordRichPresenceMod() {
		tryCheckSigned();
		ModLoadingContext.get().registerExtensionPoint(DisplayTest.class, () -> new DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (remoteVersion, network) -> true));
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> BasicDiscordRichPresenceClientConstruct::construct);
	}
	
	private void tryCheckSigned() {
		try {
			if (ModList.get().isLoaded("uteamcore")) {
				final Class<?> clazz = Class.forName("info.u_team.u_team_core.util.verify.JarSignVerifier");
				
				final Lookup lookup = MethodHandles.publicLookup();
				final MethodHandle method = lookup.findStatic(clazz, "checkSigned", MethodType.methodType(void.class, String.class));
				method.invoke(MODID);
				return;
			}
		} catch (final Throwable th) {
			LOGGER.warn("JarSignVerifier could not be executed, because uteamcore is not installed.", th);
		}
	}
	
}
