package com.xnuminousx.elementaleffects;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.xnuminousx.elementaleffects.commands.Commands;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.events.EntityDamageEvent;
import com.xnuminousx.elementaleffects.events.IndicatorInvEvent;
import com.xnuminousx.elementaleffects.events.MoveEvent;
import com.xnuminousx.elementaleffects.events.TrailInvEvent;

public class Main extends JavaPlugin {
	
	public ArrayList<Player> earth = new ArrayList<Player>();
	public ArrayList<Player> fire = new ArrayList<Player>();
	public ArrayList<Player> water = new ArrayList<Player>();
	public ArrayList<Player> air = new ArrayList<Player>();
	public ArrayList<Player> chi = new ArrayList<Player>();
	public ArrayList<Player> avatar = new ArrayList<Player>();
	
	public ArrayList<Player> hit = new ArrayList<Player>();
	
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
		getCommand("elementaleffects").setExecutor(new Commands());
		getCommand("ee").setExecutor(new Commands());
	}
	
	public void registerListeners() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		
		pm.registerEvents(new TrailInvEvent(), this);
		pm.registerEvents(new IndicatorInvEvent(), this);
		pm.registerEvents(new MoveEvent(), this);
		pm.registerEvents(new EntityDamageEvent(), this);
	}
}
