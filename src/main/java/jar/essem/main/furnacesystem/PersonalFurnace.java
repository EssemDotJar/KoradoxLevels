package jar.essem.main.furnacesystem;

import jar.essem.main.utils.customitems.CustomItems;
import jar.essem.main.utils.customitems.CustomItemsEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class PersonalFurnace {

	public static HashMap<Player, Furnace> onlinePersonalFurnaceMap = new HashMap<>();

	// temporary statics
	public static int COOK_TIME = 800;
	public static int BURN_TIME = 100; // must be COOK_TIME divided by 8

	private static final String WRONG_FUEL_MESSAGE = ChatColor.RED + "This material is not compatible with the source.";

	public void open(Player player) {
		player.openInventory(onlinePersonalFurnaceMap.get(player).getInventory());
	}

	public void create(Player player) {
		// determine where to create it depending on the size of the hashmap


		Block block = new Location(Bukkit.getWorld("world_nether"), 0, 0, 0).getBlock();
		block.setType(Material.FURNACE);

		Furnace furnace = (Furnace) block.getState();
		furnace.setCustomName(player.getName() + "'s Smelter");
		furnace.update();

		onlinePersonalFurnaceMap.put(player, furnace);
	}

	public void remove(Player player) {
		onlinePersonalFurnaceMap.get(player).getBlock().setType(Material.AIR);
		onlinePersonalFurnaceMap.remove(player);
	}

	/*
	 *
	 * EVENTS
	 *
	 */
	public void onBurn(FurnaceBurnEvent event) {
		FurnaceInventory furnaceInventory = ((Furnace) event.getBlock().getState()).getInventory();
		Player player = null;
		for (HumanEntity human : furnaceInventory.getViewers()) { // consider a furnace lock or something else to indicate owner to enable the possibility of another viewer (an admin for example)
			player = (Player) human;
		}

		if (!isFuelCompatibleWithSource(event)) {
			Bukkit.getConsoleSender().sendMessage(isFuelCompatibleWithSource(event) + "");
			setNotCompatible(event, furnaceInventory, player);
			return;
		}

		event.setBurnTime(BURN_TIME);
	}

	public void onStartSmelt(FurnaceStartSmeltEvent event) {
		FurnaceInventory furnaceInventory = ((Furnace) event.getBlock().getState()).getInventory();
		Player player = null;
		for (HumanEntity human : furnaceInventory.getViewers()) {
			player = (Player) human;
		}

		if (!sourceEqualsGem(furnaceInventory.getItem(0))) { // index 0 is the source item
			setNotCompatible(event, furnaceInventory, player);
			return; // code will not run beyond this point if the gem and material is not matching
		}

		event.setTotalCookTime(COOK_TIME);
	}

	public void onSmelt(FurnaceSmeltEvent event) {

		ItemStack result = event.getResult();
		CustomItems customItems = new CustomItems();

		ItemStack index0 = event.getSource();
		ItemStack compareTo;
		CustomItemsEnum source = null;
		for (CustomItemsEnum item : CustomItemsEnum.values()) {
			compareTo = customItems.getGem(item);
			compareTo.setAmount(index0.getAmount());
			if (index0.equals(compareTo)) source = item;
		}
		switch (source) {
			case GEM_BASE:
				result = customItems.getGem(CustomItemsEnum.GEM_COMMON);
				break;
			case GEM_COMMON:
				result = customItems.getGem(CustomItemsEnum.GEM_UNCOMMON);
				break;
			case GEM_UNCOMMON:
				result = customItems.getGem(CustomItemsEnum.GEM_RARE);
				break;
			case GEM_RARE:
				result = customItems.getGem(CustomItemsEnum.GEM_EPIC);
				break;
			case GEM_EPIC:
				result = customItems.getGem(CustomItemsEnum.GEM_LEGENDARY);
				break;
			default:
				break;
		}

		event.setResult(result);
	}

	public void onExtract(FurnaceExtractEvent event) {

	}

	public void onClick(InventoryClickEvent event) {
		Furnace furnace = (Furnace) event.getInventory().getLocation().getBlock().getState();

		int index = event.getSlot();
		if (index == 0) {
			if (furnace.getCookTime() != 0) event.setCancelled(true);
		}
	}

	/*
	 *
	 * METHODS
	 *
	 */

	public boolean isFuelCompatibleWithSource(FurnaceBurnEvent event) {
		FurnaceInventory furnaceInventory = ((Furnace) event.getBlock().getState()).getInventory(); // this just gets the inventory of furnace involved
		ItemStack source = furnaceInventory.getItem(0);
		ItemStack fuel = furnaceInventory.getItem(1);

		if (!sourceEqualsGem(source)) return false;
		if (!fuelEqualsMaterial(fuel))
			return false; // if we get past here we know that the source is a gem and the fuel is a material

		CustomItemsEnum gem = getGemType(source);
		CustomItemsEnum material = getMaterialType(fuel);

		return switch (gem) {
			case GEM_BASE -> material.equals(CustomItemsEnum.MAT_COMMON);
			case GEM_COMMON -> material.equals(CustomItemsEnum.MAT_UNCOMMON);
			case GEM_UNCOMMON -> material.equals(CustomItemsEnum.MAT_RARE);
			case GEM_RARE -> material.equals(CustomItemsEnum.MAT_EPIC);
			case GEM_EPIC -> material.equals(CustomItemsEnum.MAT_LEGENDARY);
			default -> false;
		};
	}

	public boolean sourceEqualsGem(ItemStack source) {
		return compareGemOrMaterial("gem", source);
	}

	public boolean fuelEqualsMaterial(ItemStack fuel) {
		return compareGemOrMaterial("material", fuel);
	}

	public boolean compareGemOrMaterial(String gemOrMaterial, ItemStack item) {
		ItemStack CompareTo;
		CustomItems customItems = new CustomItems();
		for (CustomItemsEnum customItem : CustomItemsEnum.values()) {
			CompareTo = switch (gemOrMaterial) {
				case "gem" -> customItems.getGem(customItem);
				case "material" -> customItems.getMaterial(customItem);
				default -> new ItemStack(Material.AIR);
			};
			CompareTo.setAmount(item.getAmount()); // they must be the same amount to equal the same exact itemStack
			if (item.equals(CompareTo)) return true;
		}

		return false;
	}

	public CustomItemsEnum getGemType(ItemStack gem) {
		return getGemOrMaterialType("gem", gem);
	}

	public CustomItemsEnum getMaterialType(ItemStack material) {
		return getGemOrMaterialType("material", material);
	}

	public CustomItemsEnum getGemOrMaterialType(String gemOrMaterial, ItemStack item) {
		ItemStack CompareTo;
		CustomItems customItems = new CustomItems();
		for (CustomItemsEnum customItem : CustomItemsEnum.values()) {
			CompareTo = switch (gemOrMaterial) {
				case "gem" -> customItems.getGem(customItem);
				case "material" -> customItems.getMaterial(customItem);
				default -> new ItemStack(Material.AIR);
			};
			CompareTo.setAmount(item.getAmount()); // they must be the same amount to equal the same exact itemStack
			if (item.equals(CompareTo)) return customItem;
		}
		return null;
	}

	// **** MAKE IT SO THAT WHEN FIRE RUNS OUT THE ELAPSED COOK TIME DOES NOT REVERT BACK TO 0 AND RATHER STAYS AS ELAPSED UNTIL...


	public void setNotCompatible(Event event, FurnaceInventory furnaceInventory, Player player) {
		if (event instanceof FurnaceBurnEvent) {
			cancelBurnEvent((FurnaceBurnEvent) event);
			returnNonCompatibleFuel((FurnaceBurnEvent) event, furnaceInventory, player);
		} else if (event instanceof FurnaceStartSmeltEvent) {
			cancelStartSmeltEvent((FurnaceStartSmeltEvent) event);
		}
	}

	public void cancelBurnEvent(FurnaceBurnEvent event) {
		event.setBurning(false);
		event.setBurnTime(1);
	}

	public void returnNonCompatibleFuel(FurnaceBurnEvent event, FurnaceInventory furnaceInventory, Player player) {
		ItemStack fuel = event.getFuel();
		furnaceInventory.remove(fuel);

		player.getInventory().addItem(fuel);
		player.sendMessage(WRONG_FUEL_MESSAGE);

		if (!sourceEqualsGem(furnaceInventory.getItem(0))) returnNonGemSource(furnaceInventory, player);
	}

	public void returnNonGemSource(FurnaceInventory furnaceInventory, Player player) {
		ItemStack source = furnaceInventory.getItem(0);
		furnaceInventory.setItem(0, new ItemStack(Material.AIR));

		player.getInventory().addItem(source);
	}

	public void cancelStartSmeltEvent(FurnaceStartSmeltEvent event) {
		event.setTotalCookTime(1_000_000_000);
	}
}
