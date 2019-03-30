package com.xnuminousx.elementaleffects;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.xnuminousx.elementaleffects.commands.Commands;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.gui.IndGui;
import com.xnuminousx.elementaleffects.gui.TrailGui;
import com.xnuminousx.elementaleffects.listeners.EntityDamage;
import com.xnuminousx.elementaleffects.listeners.Move;
import com.xnuminousx.elementaleffects.utils.Indicator;
import com.xnuminousx.elementaleffects.utils.Indicator.Indicators;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class Main extends JavaPlugin implements Listener {
	public HashMap<Player, Trail> trails = new HashMap<Player, Trail>();
	public HashMap<Player, Indicator> inds = new HashMap<Player, Indicator>();
	public static Main plugin;
	
	public void onEnable() {
		plugin = this;
		new Manager(this);
		registerCommands();
		registerListeners();
		
		new BukkitRunnable() {
			@Override
			public void run() {
				for (Player player : Bukkit.getOnlinePlayers()) {
					if (!trails.containsKey(player)) {
						return;
					} else {
						Trails trail = Trail.getTrail(player);
						if (!Methods.hasPermission(player, trail.toString())) {
							trails.remove(player);
						}
					}
					if (!inds.containsKey(player)) {
						return;
					} else {
						Indicators ind = Indicator.getIndicator(player);
						if (!Methods.hasPermission(player, ind.toString())) {
							inds.remove(player);
						}
					}
				}
			}
		}.runTaskTimer(this, 30, 20);
		
		plugin.getLogger().info("Successfully enabled ElementalEffects v" + plugin.getDescription().getVersion());
	}
	public void onDisable() {
		plugin.getLogger().info("Successfully disabled ElementalEffects v" + plugin.getDescription().getVersion());
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
		pm.registerEvents(new TrailGui(), this);
		pm.registerEvents(new IndGui(), this);
		pm.registerEvents(new Move(), this);
		pm.registerEvents(new EntityDamage(), this);
		pm.registerEvents(this, this);
	}
}