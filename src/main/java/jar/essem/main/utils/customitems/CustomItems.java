package jar.essem.main.utils.customitems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class CustomItems {

	private final Material OVERRIDE_GEM = Material.SLIME_BALL;
	private final Material OVERRIDE_MAT = Material.COAL;

	public ItemStack getGem(CustomItemsEnum gem) {
		return switch (gem) {
			case GEM_BASE -> this.gemBase();
			case GEM_COMMON -> this.gemCommon();
			case GEM_UNCOMMON -> this.gemUncommon();
			case GEM_RARE -> this.gemRare();
			case GEM_EPIC -> this.gemEpic();
			case GEM_LEGENDARY -> this.gemLegendary();
			default -> new ItemStack(Material.AIR);
		};
	}

	public ItemStack getMaterial(CustomItemsEnum material) {
		return switch (material) {
			case MAT_COMMON -> this.matCommon();
			case MAT_UNCOMMON -> this.matUncommon();
			case MAT_RARE -> this.matRare();
			case MAT_EPIC -> this.matEpic();
			case MAT_LEGENDARY -> this.matLegendary();
			default -> new ItemStack(Material.AIR);
		};
	}

	public ItemStack gemBase() {
		final String displayName = "base";
		final List<String> lore = Arrays.asList("", "");
		return itemStack(OVERRIDE_GEM, 1, displayName, lore);
	}

	public ItemStack gemCommon() {
		final String displayName = "common";
		final List<String> lore = Arrays.asList("", "");
		return itemStack(OVERRIDE_GEM, 2, displayName, lore);
	}

	public ItemStack gemUncommon() {
		final String displayName = "uncommon";
		final List<String> lore = Arrays.asList("", "");
		return itemStack(OVERRIDE_GEM, 3, displayName, lore);
	}

	public ItemStack gemRare() {
		final String displayName = "rare";
		final List<String> lore = Arrays.asList("", "");
		return itemStack(OVERRIDE_GEM, 4, displayName, lore);
	}

	public ItemStack gemEpic() {
		final String displayName = "epic";
		final List<String> lore = Arrays.asList("", "");
		return itemStack(OVERRIDE_GEM, 5, displayName, lore);
	}

	public ItemStack gemLegendary() {
		final String displayName = "legendary";
		final List<String> lore = Arrays.asList("", "");
		return itemStack(OVERRIDE_GEM, 6, displayName, lore);
	}

	public ItemStack matCommon() {
		final String displayName = "common";
		final List<String> lore = Arrays.asList("", "");
		return itemStack(OVERRIDE_MAT, 1, displayName, lore);
	}

	public ItemStack matUncommon() {
		final String displayName = "uncommon";
		final List<String> lore = Arrays.asList("", "");
		return itemStack(OVERRIDE_MAT, 2, displayName, lore);
	}

	public ItemStack matRare() {
		final String displayName = "rare";
		final List<String> lore = Arrays.asList("", "");
		return itemStack(OVERRIDE_MAT, 3, displayName, lore);
	}

	public ItemStack matEpic() {
		final String displayName = "epic";
		final List<String> lore = Arrays.asList("", "");
		return itemStack(OVERRIDE_MAT, 4, displayName, lore);
	}

	public ItemStack matLegendary() {
		final String displayName = "legendary";
		final List<String> lore = Arrays.asList("", "");
		return itemStack(OVERRIDE_MAT, 5, displayName, lore);
	}

	public ItemStack itemStack(Material material, int customModelData, String displayName, List<String> lore) { // can make this its own class and instead do new ItemStack like an override typa thing...
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();

		meta.setCustomModelData(customModelData);
		meta.setDisplayName(displayName);
		meta.setLore(lore);

		item.setItemMeta(meta);
		return item;
	}
}
