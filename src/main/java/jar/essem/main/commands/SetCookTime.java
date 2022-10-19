package jar.essem.main.commands;

import jar.essem.main.furnacesystem.PersonalFurnace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetCookTime implements CommandExecutor {

	private final String COMMAND_LABEL = "setcooktime";

	public String getCommandLabel() {
		return COMMAND_LABEL;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (label.equals(COMMAND_LABEL)) {
			if (args.length < 1) {
				sender.sendMessage("The current cookTime is " + PersonalFurnace.COOK_TIME + " ticks.");
				return true;
			}

			try {
				int number = Integer.parseInt(args[0]);

				if (number % 8 != 0) {
					sender.sendMessage("The number must be a multiple of 8.");
					return true;
				}

				PersonalFurnace.COOK_TIME = number;
				PersonalFurnace.BURN_TIME = number / 8;

				sender.sendMessage("cookTime successfully set to " + number + " ticks.");
				return true;
			} catch (IllegalArgumentException ex) {
				sender.sendMessage("Invalid argument!");
			}
		}

		return false;
	}
}
