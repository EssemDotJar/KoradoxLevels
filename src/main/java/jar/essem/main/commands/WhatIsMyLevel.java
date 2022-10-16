package jar.essem.main.commands;

import jar.essem.main.levelsystem.LevelManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhatIsMyLevel implements CommandExecutor {

	private final String COMMAND_LABEL = "level";

	public String getCOMMAND_LABEL() {
		return COMMAND_LABEL;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		Player player = (Player) sender;

		if (label.equals(COMMAND_LABEL)) {
			LevelManager levelManager = new LevelManager();

			double temp = (double) levelManager.getLevel(player, true); // level with percent to next in decimal
			int level = (int) Math.floor(temp); // only level
			double percentToNext = (temp - level) * 100; // only percent to next

			final String MESSAGE_FORMAT = "Your current level is: " + level + " and " + percentToNext + "%.";
			player.sendMessage(MESSAGE_FORMAT);
		}

		return false;
	}
}
