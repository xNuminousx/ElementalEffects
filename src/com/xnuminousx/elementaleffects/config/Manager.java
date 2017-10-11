package com.xnuminousx.elementaleffects.config;

import org.bukkit.configuration.file.FileConfiguration;

import com.xnuminousx.elementaleffects.Main;

public class Manager {
	
	private static ConfigFile main;
	static Main plugin; 
	
	public Manager(Main plugin) {
		Manager.plugin = plugin;
		main = new ConfigFile("config");
		loadConfig();
	}
	
	public static FileConfiguration getConfig() {
		return main.getConfig();
	}

	private void loadConfig() {
		FileConfiguration config = Main.plugin.getConfig();
		
		config.addDefault("Properties.RequireElement", false);
		
		config.addDefault("Language.Prefix.Enabled", true);
		
		config.addDefault("Trails.Earth.RequireEarthBlock", true);
		
		config.addDefault("Trails.Fire.DisappearInWater", true);
		config.addDefault("Trails.Fire.BoilEffect", true);
		
		config.addDefault("Trails.Air.DisappearInWater", true);
		
		config.addDefault("Trails.Avatar.Earth.RequireEarthBlock", false);
		config.addDefault("Trails.Avatar.Fire.DisappearInWater", false);
		config.addDefault("Trails.Avatar.Fire.BoilEffect", false);
		config.addDefault("Trails.Avatar.Air.DisappearInWater", false);
		
		config.options().copyDefaults(true);
		plugin.saveConfig();
	}
}
