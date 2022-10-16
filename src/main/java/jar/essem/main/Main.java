package jar.essem.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private final String PLUGIN_NAME = "[KoradoxLevels] ";

	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(this.PLUGIN_NAME + "Plugin is working!");
	}
}
