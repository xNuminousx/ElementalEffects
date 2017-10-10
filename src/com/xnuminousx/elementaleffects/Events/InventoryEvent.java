package com.xnuminousx.elementaleffects.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.projectkorra.projectkorra.configuration.ConfigManager;
import com.xnuminousx.elementaleffects.Main;

public class InventoryEvent implements Listener {
	
	private boolean canDo2;
	Main plugin = Main.getInstance();
	String prefix = ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "ElementalEffects: ";

	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player p = (Player)event.getWhoClicked();
		canDo2 = ConfigManager.getConfig().getBoolean("Properties.CanHaveMultipleTrails");
		
		if (event.getInventory().getName() != "ElementalEffects") {
			return;
		}
		
		if (event.getCurrentItem().getItemMeta().getDisplayName().contains("EarthTrail")) {
			if (plugin.earth.contains(p)) {
				event.setCancelled(true);
				plugin.earth.remove(p);
				p.sendMessage(prefix + ChatColor.GREEN + "EarthTrail disabled!");
				return;
			} else {
				event.setCancelled(true);
				plugin.earth.add(p);
				if (!canDo2) {
					plugin.fire.remove(p);
					plugin.water.remove(p);
					plugin.air.remove(p);
				}
				p.sendMessage(prefix + ChatColor.GREEN + "EarthTrail enabled!");
			}
			return;
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("FireTrail")) {
			if (plugin.fire.contains(p)) {
				event.setCancelled(true);
				plugin.fire.remove(p);
				p.sendMessage(prefix + ChatColor.RED + "FireTrail disabled!");
				return;
			} else {
				event.setCancelled(true);
				plugin.fire.add(p);
				if (!canDo2) {
					plugin.earth.remove(p);
					plugin.water.remove(p);
					plugin.air.remove(p);
				}
				p.sendMessage(prefix + ChatColor.RED + "FireTrail enabled!");
				return;
			}
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("WaterTrail")) {
			if (plugin.water.contains(p)) {
				event.setCancelled(true);
				plugin.water.remove(p);
				p.sendMessage(prefix + ChatColor.AQUA+ "WaterTrail disabled!");
				return;
			} else {
				event.setCancelled(true);
				plugin.water.add(p);
				if (!canDo2) {
					plugin.fire.remove(p);
					plugin.earth.remove(p);
					plugin.air.remove(p);
				}
				p.sendMessage(prefix + ChatColor.AQUA + "WaterTrail enabled!");
				return;
			}
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("AirTrail")) {
			if (plugin.air.contains(p)) {
				event.setCancelled(true);
				plugin.air.remove(p);
				p.sendMessage(prefix + ChatColor.GRAY + "AirTrail disabled!");
				return;
			} else {
				event.setCancelled(true);
				plugin.air.add(p);
				if (!canDo2) {
					plugin.fire.remove(p);
					plugin.water.remove(p);
					plugin.earth.remove(p);
				}
				p.sendMessage(prefix + ChatColor.GRAY + "AirTrail enabled!");
				return;
			}
		} else if (event.equals(null)) {
			event.setCancelled(true);
			return;
		}
	}
}
