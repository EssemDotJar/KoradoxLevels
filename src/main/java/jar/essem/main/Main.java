package jar.essem.main;

import jar.essem.main.commands.WhatIsMyLevel;
import jar.essem.main.listeners.JoinQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private final String PLUGIN_NAME = "[KoradoxLevels] ";

	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(this.PLUGIN_NAME + "Plugin is working!");

		registerEvents();
		registerCommands();
	}

	public final void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinQuitListener(), this);
	}

	public final void registerCommands() {
		WhatIsMyLevel levelCommand = new WhatIsMyLevel();
		this.getCommand(levelCommand.getCOMMAND_LABEL()).setExecutor(levelCommand);
	}
}
