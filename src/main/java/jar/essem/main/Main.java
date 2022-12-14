package jar.essem.main;

import jar.essem.main.commands.GetCustomItem;
import jar.essem.main.commands.OpenFurnace;
import jar.essem.main.commands.SetCookTime;
import jar.essem.main.commands.WhatIsMyLevel;
import jar.essem.main.listeners.FurnaceListener;
import jar.essem.main.listeners.JoinQuitListener;
import jar.essem.main.utils.customitems.CustomItems;
import jar.essem.main.utils.customitems.CustomItemsEnum;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private final String PLUGIN_NAME = "[KoradoxLevels] ";

	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(this.PLUGIN_NAME + "Plugin is working!");

		registerEvents();
		registerCommands();
		registerRecipes();
	}

	public final void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinQuitListener(), this);
		pm.registerEvents(new FurnaceListener(), this);
	}

	public final void registerCommands() {
		WhatIsMyLevel levelCommand = new WhatIsMyLevel();
		this.getCommand(levelCommand.getCOMMAND_LABEL()).setExecutor(levelCommand);

		GetCustomItem getCustomItemCommand = new GetCustomItem();
		this.getCommand(getCustomItemCommand.getCOMMAND_LABEL()).setExecutor(getCustomItemCommand);

		OpenFurnace openFurnaceCommand = new OpenFurnace();
		this.getCommand(openFurnaceCommand.getCOMMAND_LABEL()).setExecutor(openFurnaceCommand);

		SetCookTime setCookTimeCommand = new SetCookTime();
		this.getCommand(setCookTimeCommand.getCommandLabel()).setExecutor(setCookTimeCommand);
	}

	public final void registerRecipes() {
		itemRecipes();
		furnaceRecipes();
	}

	public void itemRecipes() {
		// create a NamespacedKey for your recipe
		NamespacedKey key = new NamespacedKey(this, "gem_base");

		// Create our custom recipe variable
		ShapedRecipe recipe = new ShapedRecipe(key, new CustomItems().getGem(CustomItemsEnum.GEM_BASE));

		// Here we will set the places. E and S can represent anything, and the letters can be anything. Beware; this is case sensitive.
		recipe.shape("BBB", "BRB", "BBB");

		// Set what the letters represent.
		recipe.setIngredient('B', Material.NETHER_BRICK);
		recipe.setIngredient('R', Material.BLAZE_POWDER);

		// Finally, add the recipe to the bukkit recipes
		Bukkit.addRecipe(recipe);
	}

	public void furnaceRecipes() {
		CustomItems items = new CustomItems();

		NamespacedKey key = new NamespacedKey(this, "gem");
		FurnaceRecipe recipe = new FurnaceRecipe(key, items.getGem(CustomItemsEnum.GEM_BASE), Material.SLIME_BALL, 10f, 10);
		Bukkit.addRecipe(recipe);
	}
}
