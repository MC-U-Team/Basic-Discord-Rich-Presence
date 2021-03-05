package info.u_team.basic_discord_rich_presence;

import java.lang.reflect.Method;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.*;

import info.u_team.basic_discord_rich_presence.init.BasicDiscordRichPresenceClientConstruct;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLNetworkConstants;

@Mod(BasicDiscordRichPresenceMod.MODID)
public class BasicDiscordRichPresenceMod {
	
	public static final String MODID = "basicdiscordrichpresence";
	
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	public BasicDiscordRichPresenceMod() {
		tryCheckSigned();
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> BasicDiscordRichPresenceClientConstruct::construct);
	}
	
	private void tryCheckSigned() {
		try {
			if (ModList.get().isLoaded("uteamcore")) {
				Class<?> clazz = Class.forName("info.u_team.u_team_core.util.verify.JarSignVerifier");
				Method method = clazz.getDeclaredMethod("checkSigned", String.class);
				method.invoke(null, MODID);
				return;
			}
		} catch (Exception ex) {
		}
		LOGGER.warn("JarSignVerifier could not be executed, because uteamcore is not installed.");
	}
	
}
