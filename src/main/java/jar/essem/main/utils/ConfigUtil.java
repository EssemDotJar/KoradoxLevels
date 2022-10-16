package jar.essem.main.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigUtil {
	private final String path;
	private File file;
	private FileConfiguration fileConfig;

	public ConfigUtil(String path) {
		this.path = path;
		reload();
	}

	public void reloadFile() {
		this.file = new File(path);
	}

	public void reloadConfig() {
		this.fileConfig = YamlConfiguration.loadConfiguration(file);
	}

	public void reload() {
		reloadFile();
		reloadConfig();
	}

	public void save() {
		try {
			this.fileConfig.save(file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	//

	public Object get(String path) {
		return this.fileConfig.get(path);
	}

	public void set(String path, Object input) {
		this.fileConfig.set(path, input);
		this.save();
	}

	public boolean contains(String path) {
		return this.fileConfig.contains(path);
	}
}
