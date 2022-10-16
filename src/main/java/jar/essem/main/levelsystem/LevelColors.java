package jar.essem.main.levelsystem;

import org.bukkit.ChatColor;

public enum LevelColors {
	ONE(ChatColor.translateAlternateColorCodes('&', "&x&c&7&c&7&c&7")),
	TEN(ChatColor.translateAlternateColorCodes('&', "&x&9&0&e&8&e&a")),
	TWENTY(ChatColor.translateAlternateColorCodes('&', "&x&0&0&f&f&d&5")),
	THIRTY(ChatColor.translateAlternateColorCodes('&', "&x&6&6&f&f&9&9")),
	FORTY(ChatColor.translateAlternateColorCodes('&', "&x&0&0&f&f&0&0")),
	FIFTY(ChatColor.translateAlternateColorCodes('&', "&x&a&e&f&f&0&0")),
	SIXTY(ChatColor.translateAlternateColorCodes('&', "&x&e&e&f&f&0&0")),
	SEVENTY(ChatColor.translateAlternateColorCodes('&', "&x&f&f&f&f&0&0")),
	EIGHTY(ChatColor.translateAlternateColorCodes('&', "&x&f&f&d&5&0&0")),
	NINETY(ChatColor.translateAlternateColorCodes('&', "&x&f&f&7&b&0&0")),
	HUNDRED(ChatColor.translateAlternateColorCodes('&', "&x&f&f&0&0&0&0"));

	private final String color;

	LevelColors(String chatColor) {
		this.color = chatColor;
	}

	public String getColor() {
		return this.color;
	}
}
