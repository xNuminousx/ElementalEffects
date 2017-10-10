package com.xnuminousx.elementaleffects.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.xnuminousx.elementaleffects.Main;

public class InventoryEvent implements Listener {
	
	Main plugin = Main.getInstance();
	String prefix = ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "ElementalEffects: ";

	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player p = (Player)event.getWhoClicked();
		
		if (event.getInventory().getName() != "ElementalEffects") {
			return;
		}
		
		if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getItemMeta().getDisplayName().equals(null)) {
			event.setCancelled(true);
			return;
			
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("EarthTrail")) {
			if (plugin.earth.contains(p)) {
				event.setCancelled(true);
				plugin.earth.remove(p);
				p.sendMessage(prefix + ChatColor.GREEN + "EarthTrail disabled!");
				return;
			} else {
				event.setCancelled(true);
				plugin.earth.add(p);
				plugin.fire.remove(p);
				plugin.water.remove(p);
				plugin.air.remove(p);
				plugin.avatar.remove(p);
				plugin.chi.remove(p);
				
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
				plugin.earth.remove(p);
				plugin.water.remove(p);
				plugin.air.remove(p);
				plugin.avatar.remove(p);
				plugin.chi.remove(p);
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
				plugin.fire.remove(p);
				plugin.earth.remove(p);
				plugin.air.remove(p);
				plugin.avatar.remove(p);
				plugin.chi.remove(p);
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
				plugin.fire.remove(p);
				plugin.water.remove(p);
				plugin.earth.remove(p);
				plugin.avatar.remove(p);
				plugin.chi.remove(p);
				p.sendMessage(prefix + ChatColor.GRAY + "AirTrail enabled!");
				return;
			}
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("ChiTrail")) {
			if (plugin.chi.contains(p)) {
				event.setCancelled(true);
				plugin.chi.remove(p);
				p.sendMessage(prefix + ChatColor.GOLD + "ChiTrail disabled!");
				return;
			} else {
				event.setCancelled(true);
				plugin.chi.add(p);
				plugin.air.remove(p);
				plugin.fire.remove(p);
				plugin.water.remove(p);
				plugin.earth.remove(p);
				plugin.avatar.remove(p);
				p.sendMessage(prefix + ChatColor.GOLD + "ChiTrail enabled!");
				return;
			}
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("AvatarTrail")) {
			if (plugin.avatar.contains(p)) {
				event.setCancelled(true);
				plugin.avatar.remove(p);
				p.sendMessage(prefix + ChatColor.DARK_PURPLE + "AvatarTrail disabled!");
				return;
			} else {
				event.setCancelled(true);
				plugin.avatar.add(p);
				plugin.chi.remove(p);
				plugin.air.remove(p);
				plugin.fire.remove(p);
				plugin.water.remove(p);
				plugin.earth.remove(p);
				p.sendMessage(prefix + ChatColor.DARK_PURPLE + "AvatarTrail enabled!");
				return;
			}
		} else if (event.equals(null)) {
			event.setCancelled(true);
			return;
		}
	}
}