package jar.essem.main.commands;

import jar.essem.main.furnacesystem.PersonalFurnace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenFurnace implements CommandExecutor {

	private final String COMMAND_LABEL = "openfurnace";

	public String getCOMMAND_LABEL() {
		return COMMAND_LABEL;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		Player player = (Player) sender;

		if (label.equals(COMMAND_LABEL)) {
			PersonalFurnace personalFurnace = new PersonalFurnace();
			personalFurnace.open(player);

			return true;
		}

		return false;
	}
}
