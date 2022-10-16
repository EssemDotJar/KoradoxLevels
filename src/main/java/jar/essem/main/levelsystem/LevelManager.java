package jar.essem.main.levelsystem;

import jar.essem.main.utils.ConfigUtil;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class LevelManager {
	private static final HashMap<Player, Integer> onlinePlayersExperienceMap = new HashMap<>();
	private final String PATH = ".experience";

	public void insertPlayer(Player player) {
		final int experience = getExperience(player);
		onlinePlayersExperienceMap.put(player, experience);
	}

	public int getExperience(Player player) {
		if (onlinePlayersExperienceMap.containsKey(player)) {
			return onlinePlayersExperienceMap.get(player);
		} else {
			ConfigUtil config = new ConfigUtil("plugins/KoradoxLevels/levels.yml"); // automate this further by making a method for getting the full path
			if (config.contains(player.getName() + PATH)) { // automate this further by making a method for getting the full path
				return (int) config.get(player.getName() + PATH); // automate this further by making a method for getting the full path
			} else {
				config.set(player.getName() + PATH, 0);
				return 0;
			}
		}
	}

	public void removePlayer(Player player) {
		onlinePlayersExperienceMap.remove(player);
	}

	public LevelColors determineLevelColor(Player player) {
		final int level = (int) this.getLevel(player, false) / 10;

		return switch (level) {
			case 0 -> LevelColors.ONE;
			case 1 -> LevelColors.TEN;
			case 2 -> LevelColors.TWENTY;
			case 3 -> LevelColors.THIRTY;
			case 4 -> LevelColors.FORTY;
			case 5 -> LevelColors.FIFTY;
			case 6 -> LevelColors.SIXTY;
			case 7 -> LevelColors.SEVENTY;
			case 8 -> LevelColors.EIGHTY;
			case 9 -> LevelColors.NINETY;
			case 10 -> LevelColors.HUNDRED;
			default -> throw new IllegalStateException("Unexpected value: " + level);
		};
	}

	/*
	Max level is 101
	Max exp is 1 million

	Level 101 is 1 million exp
	Level 2 is 100 exp

	So if I do levels-- -> 101=100 and 2=1:
	Then the equation is as simple as -> 100*level^2 = experience -> sqrt(experience/100) = level--;
	|-> Last thing I need to do then is increase level by 1 before returning...
	 */
	public Object getLevel(Player player, boolean returnAsDouble) {
		int experience = onlinePlayersExperienceMap.get(player);
		experience /= 100;

		int level = (int) Math.sqrt(experience);
		double levelDouble = Math.sqrt(experience);

		return returnAsDouble ? ++levelDouble : ++level; // need to increase by one to get real level
	}

	public void setExp(Player player, int experience) {
		onlinePlayersExperienceMap.put(player, experience);

		ConfigUtil config = new ConfigUtil("plugins/KoradoxLevels/levels.yml"); // automate this further by making a method for getting the full path
		config.set(player.getName() + PATH, experience); // automate this further by making a method for getting the full path
	}

	public void addExp(Player player, int experienceToAdd) {
		int experience = onlinePlayersExperienceMap.get(player);
		experience += experienceToAdd;
		if (experience < 0) {
			setExp(player, 0);
		} else if (experience > 1_000_000) {
			setExp(player, 1_000_000);
		} else {
			setExp(player, experience);
		}
	}
}
