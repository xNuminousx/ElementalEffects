package com.xnuminousx.elementaleffects.Config;

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
		
		config.addDefault("Properties.CanHaveMultipleTrails", false);
		
		config.options().copyDefaults(true);
		plugin.saveConfig();
	}
}
