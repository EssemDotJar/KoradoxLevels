package jar.essem.main.listeners;

import jar.essem.main.furnacesystem.PersonalFurnace;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;

public class FurnaceListener implements Listener {

	@EventHandler
	public void onFurnaceBurn(FurnaceBurnEvent event) {
		handleEvents(event, "onBurn", event.getBlock());
	}

	@EventHandler
	public void onFurnaceStartSmelt(FurnaceStartSmeltEvent event) {
		handleEvents(event, "onStartSmelt", event.getBlock());
	}

	@EventHandler
	public void onFurnaceSmelt(FurnaceSmeltEvent event) {
		handleEvents(event, "onSmelt", event.getBlock());
	}

	@EventHandler
	public void onFurnaceExtract(FurnaceExtractEvent event) {
		if (event.getBlock().getType().equals(Material.AIR)) {
			handleEvents(event, "onExtract", event.getBlock());
		}
	}

	// OnClick
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!(event.getInventory().getType().equals(InventoryType.FURNACE))) return;

		handleEvents(event, "onClick", event.getInventory().getLocation().getBlock());
	}

	// Make an enum for this
	public void handleEvents(Event event, String type, Block block) {
		if (!(block.getWorld().getName().equals("world_nether")) && !type.equals("onExtract"))
			return; // onExtract block is in world

		PersonalFurnace personalFurnace = new PersonalFurnace();
		switch (type) {
			case "onBurn" -> personalFurnace.onBurn((FurnaceBurnEvent) event);
			case "onStartSmelt" -> personalFurnace.onStartSmelt((FurnaceStartSmeltEvent) event);
			case "onSmelt" -> personalFurnace.onSmelt((FurnaceSmeltEvent) event);
			case "onExtract" -> personalFurnace.onExtract((FurnaceExtractEvent) event);
			case "onClick" -> personalFurnace.onClick((InventoryClickEvent) event);
		}
	}


}
