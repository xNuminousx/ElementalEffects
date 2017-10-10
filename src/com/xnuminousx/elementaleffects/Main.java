package com.xnuminousx.elementaleffects;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.xnuminousx.elementaleffects.Config.Manager;

public class Main extends JavaPlugin {
	
	ArrayList<Player> earth = new ArrayList<Player>();
	ArrayList<Player> fire = new ArrayList<Player>();
	ArrayList<Player> water = new ArrayList<Player>();
	ArrayList<Player> air = new ArrayList<Player>();
	
	public static Main plugin;
	
	public void onEnable() {
		plugin = this;
		
		new Manager(this);
		registerCommands();
		registerListeners();
		
		Bukkit.getServer().getLogger().info("ElementalEffects enabled");
	}
	public void onDisable() {
		Bukkit.getServer().getLogger().info("ElementalEffects disabled");
		
	}
	
	public static Main getInstance() {
		return plugin;
	}
	
	public void registerCommands() {
		getCommand("etrails").setExecutor(new GUI());
	}
	
	public void registerListeners() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		
		pm.registerEvents(new InventoryEvent(), this);
		pm.registerEvents(new MoveEvent(), this);
	}
}
