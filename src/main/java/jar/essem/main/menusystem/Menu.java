package jar.essem.main.menusystem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public abstract class Menu implements InventoryHolder {

	protected Inventory inventory;

	public abstract String getName();

	public abstract int getSize();

	public abstract void handleMenu(InventoryClickEvent event);

	public abstract void setItems(Player player); // sets the items to the proper index slots

	public abstract void initializeItems(Player player); // stores the items to private fields

	public void setItem(int index, ItemStack item) { // utility method for setItems()
		inventory.setItem(index, item);
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	public void open(Player player) { // also reconsider params
		inventory = Bukkit.createInventory(this, getSize(), getName()); // fix this

		setItems(player);

		player.openInventory(inventory);
	}
}
