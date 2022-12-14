package jar.essem.main.commands;

import jar.essem.main.utils.customitems.CustomItems;
import jar.essem.main.utils.customitems.CustomItemsEnum;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetCustomItem implements CommandExecutor {
	private final String COMMAND_LABEL = "giveitem";

	public String getCOMMAND_LABEL() {
		return COMMAND_LABEL;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		Player player = (Player) sender;

		if (label.equals(COMMAND_LABEL)) {
			if (args.length < 1) {
				return false;
			}

			for (Player input : Bukkit.getOnlinePlayers()) {
				if (args[0].equals(input.getName())) {
					if (args.length < 2) {
						return false;
					}
					CustomItems items = new CustomItems();
					ItemStack item = switch (args[1].toUpperCase()) {
						case "GEM_BASE" -> items.getGem(CustomItemsEnum.GEM_BASE);
						case "GEM_COMMON" -> items.getGem(CustomItemsEnum.GEM_COMMON);
						case "GEM_UNCOMMON" -> items.getGem(CustomItemsEnum.GEM_UNCOMMON);
						case "GEM_RARE" -> items.getGem(CustomItemsEnum.GEM_RARE);
						case "GEM_EPIC" -> items.getGem(CustomItemsEnum.GEM_EPIC);
						case "GEM_LEGENDARY" -> items.getGem(CustomItemsEnum.GEM_LEGENDARY);
						case "MAT_COMMON" -> items.getMaterial(CustomItemsEnum.MAT_COMMON);
						case "MAT_UNCOMMON" -> items.getMaterial(CustomItemsEnum.MAT_UNCOMMON);
						case "MAT_RARE" -> items.getMaterial(CustomItemsEnum.MAT_RARE);
						case "MAT_EPIC" -> items.getMaterial(CustomItemsEnum.MAT_EPIC);
						case "MAT_LEGENDARY" -> items.getMaterial(CustomItemsEnum.MAT_LEGENDARY);
						default -> new ItemStack(Material.STONE);
					};
					if (item.getType() == Material.STONE) {
						return false;
					}
					int amount = 1;
					if (args.length > 2) {
						try {

							amount = Integer.parseInt(args[2]);
						} catch (IllegalArgumentException exception) {

						}
					}
					item.setAmount(amount);
					input.getInventory().addItem(item);
					player.sendMessage("Gave " + player.getName() + " " + amount + " " + item.getItemMeta().getDisplayName());
					if (input != player) {
						input.sendMessage(player.getName() + " has given you " + amount + " " + item.getItemMeta().getDisplayName());
					}
					return true;
				}
			}

			player.sendMessage("Player is not online.");
		}

		return false;
	}


	/*private static final String[] ITEMS = {
			"GEM_BASE",
			"GEM_COMMON",
			"GEM_UNCOMMON",
			"GEM_RARE",
			"GEM_EPIC",
			"GEM_LEGENDARY",
			"MAT_COMMON",
			"MAT_UNCOMMON",
			"MAT_RARE",
			"MAT_EPIC",
			"MAT_LEGENDARY"};

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		//create new array
		final List<String> completions = new ArrayList<>();
		//copy matches of first argument from list (ex: if first arg is 'm' will return just 'minecraft')
		StringUtil.copyPartialMatches(args[0], Arrays.asList(ITEMS), completions);
		//sort the list
		Collections.sort(completions);
		return completions;
	}*/
}
