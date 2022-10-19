package jar.essem.main.listeners;

import jar.essem.main.furnacesystem.PersonalFurnace;
import jar.essem.main.levelsystem.LevelManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		addToExperienceMap(player);
		addToFurnaceMap(player);
	}

	public void addToExperienceMap(Player player) {
		LevelManager levelManager = new LevelManager();
		levelManager.insertPlayer(player);
	}

	public void addToFurnaceMap(Player player) {
		PersonalFurnace personalFurnace = new PersonalFurnace();
		personalFurnace.create(player);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		removeFromExperienceMap(player);
		removeFromFurnaceMap(player);
	}

	public void removeFromExperienceMap(Player player) {
		LevelManager levelManager = new LevelManager();
		levelManager.removePlayer(player);
	}

	public void removeFromFurnaceMap(Player player) {
		PersonalFurnace personalFurnace = new PersonalFurnace();
		personalFurnace.remove(player);
	}
}
