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
		config.addDefault("Language.IndicatorGUIName", "Elemental Indicators");
		config.addDefault("Language.Prefix.Enabled", true);
		
		config.addDefault("Properties.RequireElement", false);
		config.addDefault("Properties.CloseInventoryOnSelect", false);
		
		config.addDefault("Trails.Earth.RequireEarthBlock", true);
		config.addDefault("Trails.Earth.Particles.Speed", 0);
		config.addDefault("Trails.Earth.Particles.Amount", 5);
		
		config.addDefault("Trails.Eruption.Particles.Amount", 2);
		
		config.addDefault("Trails.Sand.Cloak.RequireSand", false);
		config.addDefault("Trails.Sand.Cloak.Radius", 0.5);
		config.addDefault("Trails.Sand.Particles.Speed", 0);
		config.addDefault("Trails.Sand.Particles.Amount", 10);
		
		config.addDefault("Trails.Fire.DisappearInWater", true);
		config.addDefault("Trails.Fire.BoilEffect", true);
		config.addDefault("Trails.Fire.Particles.Speed", 0);
		config.addDefault("Trails.Fire.Particles.Amount", 2);
		
		config.addDefault("Trails.FlameArms.Particles.Speed", 0);
		config.addDefault("Trails.FlameArms.Particles.Amount", 2);
		config.addDefault("Trails.FlameArms.Rings.Enabled", true);
		
		config.addDefault("Trails.StaticField.Enabled", true);
		config.addDefault("Trails.StaticField.Amount", 2);
		config.addDefault("Trails.StaticField.Speed", 3);
		
		config.addDefault("Trails.Water.Particles.Speed", 0);
		config.addDefault("Trails.Water.Particles.Amount", 2);
		config.addDefault("Trails.Water.Particles.Ring.Speed", 2);
		config.addDefault("Trails.Water.Particles.Ring.Size", 1);
		
		config.addDefault("Trails.Ice.Boots.Speed", 0);
		config.addDefault("Trails.Ice.Boots.Amount", 4);
		config.addDefault("Trails.Ice.Particles.Speed", 0);
		config.addDefault("Trails.Ice.Particles.Amount", 5);
		
		config.addDefault("Trails.Air.DisappearInWater", true);
		config.addDefault("Trails.Air.CloudEffect", true);
		config.addDefault("Trails.Air.Particles.Speed", 0);
		config.addDefault("Trails.Air.Particles.Amount", 2);
		
		config.addDefault("Trails.AeroSphere.Size", 2);
		
		config.addDefault("Trails.Flight.Particles.Speed", 0);
		config.addDefault("Trails.Flight.Particles.Amount", 1);
		config.addDefault("Trails.Flight.Sparks.Enabled", true);
		
		config.addDefault("Trails.Chi.Particles.DoMagicCrit", false);
		config.addDefault("Trails.Chi.Particles.Amount", 1);
		config.addDefault("Trails.Chi.Particles.Speed", 0);
		
		config.addDefault("Trails.Intensity.Sound.Enabled", true);
		config.addDefault("Trails.Intensity.Particles.Amount", 1);
		config.addDefault("Trails.Intensity.Particles.Speed", 0.5);
		config.addDefault("Trails.Intensity.DashEffect.Enabled", true);
		config.addDefault("Trails.Intensity.DashEffect.Speed", 0.05);
		config.addDefault("Trails.Intensity.DashEffect.Amount", 1);
		
		config.addDefault("Trails.Avatar.Earth.RequireEarthBlock", false);
		config.addDefault("Trails.Avatar.Fire.DisappearInWater", false);
		config.addDefault("Trails.Avatar.Fire.BoilEffect", false);
		config.addDefault("Trails.Avatar.Air.DisappearInWater", false);
		config.addDefault("Trails.Avatar.Particles.Speed", 0);
		config.addDefault("Trails.Avatar.Particles.Amount", 2);
		
		config.addDefault("Indicators.Hit.CriticalDamage", 3);
		
		config.addDefault("Indicators.AvatarState.RequireAvatarState", true);
		config.addDefault("Indicators.AvatarState.PlayEyeGlow", true);
		
		config.addDefault("Indicators.Moon.RequireNight", true);
		config.addDefault("Indicators.Moon.RequireWaterElement", true);
		config.addDefault("Indicators.Moon.FullMoon.Enabled", true);
		config.addDefault("Indicators.Moon.FullMoon.EnhanceParticles", true);
		
		config.addDefault("Indicators.Sun.RequireDay", true);
		config.addDefault("Indicators.Sun.RequireFireElement", true);
		
		config.options().copyDefaults(true);
		plugin.saveConfig();
	}
	
	public static double maxDamage() {
		return Main.getInstance().getConfig().getDouble("Indicators.Hit.CriticalDamage");
	}
	
	public static boolean requireElement() {
		return Main.getInstance().getConfig().getBoolean("Properties.RequireElement");
	}
	
	public static String getTrailGuiName() {
		return Main.getInstance().getConfig().getString("Language.TrailGUIName");
	}
	
	public static String getIndicatorGuiName() {
		return Main.getInstance().getConfig().getString("Language.IndicatorGUIName");
	}
	
	public static boolean closeInv() {
		return Main.getInstance().getConfig().getBoolean("Properties.CloseInventoryOnSelect");
	}
	
	public static boolean doPrefix() {
		return Main.getInstance().getConfig().getBoolean("Language.Prefix.Enabled");
	}
}
