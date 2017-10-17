package com.xnuminousx.elementaleffects;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.xnuminousx.elementaleffects.commands.Commands;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.events.EntityDamageEvent;
import com.xnuminousx.elementaleffects.events.IndicatorInvEvent;
import com.xnuminousx.elementaleffects.events.MoveEvent;
import com.xnuminousx.elementaleffects.events.TrailInvEvent;
import com.xnuminousx.elementaleffects.trails.Still;

public class Main extends JavaPlugin implements Listener {
	
	public ArrayList<Player> earth = new ArrayList<Player>();
	public ArrayList<Player> fire = new ArrayList<Player>();
	public ArrayList<Player> water = new ArrayList<Player>();
	public ArrayList<Player> air = new ArrayList<Player>();
	public ArrayList<Player> chi = new ArrayList<Player>();
	public ArrayList<Player> avatar = new ArrayList<Player>();
	
	public ArrayList<Player> hit = new ArrayList<Player>();
	
	public static Main plugin;
	public static boolean isMoving;
	
	public void onEnable() {
		plugin = this;
		
		new Manager(this);
		registerCommands();
		registerListeners();
		
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			new BukkitRunnable() {

				@Override
				public void run() {
					if (!isMoving) {
						if (plugin.fire.contains(player)) {
							Still.fireTrail(player);
						}
					} else {
						return;
					}
				}
				
			}.runTaskTimerAsynchronously(plugin, 0, 0);
		}
		
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
		pm.registerEvents(this, this);
	}
}
