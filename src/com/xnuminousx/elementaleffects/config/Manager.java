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
		
		config.addDefault("Language.TrailGUIName", "Elemental Trails");
		config.addDefault("Language.Prefix.Enabled", true);
		
		config.addDefault("Trails.RequireElement", false);
		config.addDefault("Trails.CloseInventoryOnSelect", false);
		
		config.addDefault("Trails.Earth.RequireEarthBlock", true);
		config.addDefault("Trails.Earth.Particles.Speed", 0);
		config.addDefault("Trails.Earth.Particles.Amount", 5);
		
		config.addDefault("Trails.Fire.DisappearInWater", true);
		config.addDefault("Trails.Fire.BoilEffect", true);
		config.addDefault("Trails.Fire.Particles.Speed", 0);
		config.addDefault("Trails.Fire.Particles.Amount", 2);
		
		config.addDefault("Trails.Water.Particles.Speed", 0);
		config.addDefault("Trails.Water.Particles.Amount", 2);
		
		config.addDefault("Trails.Air.DisappearInWater", true);
		config.addDefault("Trails.Air.Particles.Speed", 0);
		config.addDefault("Trails.Air.Particles.Amount", 2);
		
		config.addDefault("Trails.Chi.Particles.DoMagicCrit", false);
		config.addDefault("Trails.Chi.Particles.Amount", 1);
		config.addDefault("Trails.Chi.Particles.Speed", 0);
		
		config.addDefault("Trails.Avatar.Earth.RequireEarthBlock", false);
		config.addDefault("Trails.Avatar.Fire.DisappearInWater", false);
		config.addDefault("Trails.Avatar.Fire.BoilEffect", false);
		config.addDefault("Trails.Avatar.Air.DisappearInWater", false);
		config.addDefault("Trails.Avatar.Particles.Speed", 0);
		config.addDefault("Trails.Avatar.Particles.Amount", 2);
		
		config.options().copyDefaults(true);
		plugin.saveConfig();
	}
	
	public static boolean requireElement() {
		return Main.getInstance().getConfig().getBoolean("Trails.RequireElement");
	}
	
	public static String getGuiName() {
		return Main.getInstance().getConfig().getString("Language.TrailGUIName");
	}
	
	public static boolean closeInv() {
		return Main.getInstance().getConfig().getBoolean("Trails.CloseInventoryOnSelect");
	}
	
	public static boolean doPrefix() {
		return Main.getInstance().getConfig().getBoolean("Language.Prefix.Enabled");
	}
}
