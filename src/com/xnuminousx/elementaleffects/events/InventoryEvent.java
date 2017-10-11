package com.xnuminousx.elementaleffects.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.xnuminousx.elementaleffects.Main;

public class InventoryEvent implements Listener {
	
	Main plugin = Main.getInstance();
	String prefix = ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "ElementalEffects: ";

	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player p = (Player)event.getWhoClicked();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(p);
		
		boolean reqEle = Main.getInstance().getConfig().getBoolean("Properties.RequireElement");
		
		if (event.getInventory().getName() != "ElementalEffects") {
			return;
		}
		
		if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getItemMeta().getDisplayName().equals(null)) {
			event.setCancelled(true);
			return;
			
		// Enable/Disable Earth Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Earth Trail")) {
			if (plugin.earth.contains(p)) {
				// Disable trail
				event.setCancelled(true);
				plugin.earth.remove(p);
				p.sendMessage(prefix + ChatColor.GREEN + "Earth Trail disabled!");
				return;
			} else if (p.hasPermission("elementaleffects.earth") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.EARTH)) {
						// give trail
						event.setCancelled(true);
						plugin.earth.add(p);
						plugin.fire.remove(p);
						plugin.water.remove(p);
						plugin.air.remove(p);
						plugin.avatar.remove(p);
						plugin.chi.remove(p);
						p.sendMessage(prefix + ChatColor.GREEN + "Earth Trail enabled!");
						return;
					} else {
						// don't have the element warning
						event.setCancelled(true);
						p.closeInventory();
						p.sendMessage(prefix + ChatColor.GREEN + "You don't have the necessary element!");
						return;
					}
				} else {
					//give trail
					event.setCancelled(true);
					plugin.earth.add(p);
					plugin.fire.remove(p);
					plugin.water.remove(p);
					plugin.air.remove(p);
					plugin.avatar.remove(p);
					plugin.chi.remove(p);
					p.sendMessage(prefix + ChatColor.GREEN + "Earth Trail enabled!");
					return;
				}
			} else {
				// don't have permission
				event.setCancelled(true);
				p.closeInventory();
				p.sendMessage(prefix + ChatColor.GREEN + "You don't have the necessary permission!");
			}
			return;
			
		// Enable/Disable Fire Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Fire Trail")) {
			if (plugin.fire.contains(p)) {
				// Disable trail
				event.setCancelled(true);
				plugin.fire.remove(p);
				p.sendMessage(prefix + ChatColor.RED + "Fire Trail disabled!");
				return;
			} else if (p.hasPermission("elementaleffects.fire") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.FIRE)) {
						// give trail
						event.setCancelled(true);
						plugin.fire.add(p);
						plugin.earth.remove(p);
						plugin.water.remove(p);
						plugin.air.remove(p);
						plugin.avatar.remove(p);
						plugin.chi.remove(p);
						p.sendMessage(prefix + ChatColor.RED + "Fire Trail enabled!");
						return;
					} else {
						// don't have the element warning
						event.setCancelled(true);
						p.closeInventory();
						p.sendMessage(prefix + ChatColor.RED + "You don't have the necessary element!");
						return;
					}
				} else {
					//give trail
					event.setCancelled(true);
					plugin.fire.add(p);
					plugin.earth.remove(p);
					plugin.water.remove(p);
					plugin.air.remove(p);
					plugin.avatar.remove(p);
					plugin.chi.remove(p);
					p.sendMessage(prefix + ChatColor.RED + "Fire Trail enabled!");
					return;
				}
			} else {
				// don't have permission
				event.setCancelled(true);
				p.closeInventory();
				p.sendMessage(prefix + ChatColor.RED + "You don't have the necessary permission!");
			}
			return;
			
		// Enable/Disable Water Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Water Trail")) {
			if (plugin.water.contains(p)) {
				// Disable trail
				event.setCancelled(true);
				plugin.water.remove(p);
				p.sendMessage(prefix + ChatColor.AQUA + "Water Trail disabled!");
				return;
			} else if (p.hasPermission("elementaleffects.water") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.WATER)) {
						// give trail
						event.setCancelled(true);
						plugin.water.add(p);
						plugin.fire.remove(p);
						plugin.earth.remove(p);
						plugin.air.remove(p);
						plugin.avatar.remove(p);
						plugin.chi.remove(p);
						p.sendMessage(prefix + ChatColor.AQUA + "Water Trail enabled!");
						return;
					} else {
						// don't have the element warning
						event.setCancelled(true);
						p.closeInventory();
						p.sendMessage(prefix + ChatColor.AQUA + "You don't have the necessary element!");
						return;
					}
				} else {
					//give trail
					event.setCancelled(true);
					plugin.water.add(p);
					plugin.fire.remove(p);
					plugin.earth.remove(p);
					plugin.air.remove(p);
					plugin.avatar.remove(p);
					plugin.chi.remove(p);
					p.sendMessage(prefix + ChatColor.AQUA + "Water Trail enabled!");
					return;
				}
			} else {
				// don't have permission
				event.setCancelled(true);
				p.closeInventory();
				p.sendMessage(prefix + ChatColor.AQUA + "You don't have the necessary permission!");
			}
			return;
		
			
		// Enable/Disable Chi Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Chi Trail")) {
			if (plugin.chi.contains(p)) {
				// Disable trail
				event.setCancelled(true);
				plugin.chi.remove(p);
				p.sendMessage(prefix + ChatColor.GOLD + "Chi Trail disabled!");
				return;
			} else if (p.hasPermission("elementaleffects.chi") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.CHI)) {
						// give trail
						event.setCancelled(true);
						plugin.chi.add(p);
						plugin.fire.remove(p);
						plugin.water.remove(p);
						plugin.air.remove(p);
						plugin.avatar.remove(p);
						plugin.earth.remove(p);
						p.sendMessage(prefix + ChatColor.GOLD + "Chi Trail enabled!");
						return;
					} else {
						// don't have the element warning
						event.setCancelled(true);
						p.closeInventory();
						p.sendMessage(prefix + ChatColor.GOLD + "You don't have the necessary element!");
						return;
					}
				} else {
					//give trail
					event.setCancelled(true);
					plugin.chi.add(p);
					plugin.fire.remove(p);
					plugin.water.remove(p);
					plugin.air.remove(p);
					plugin.avatar.remove(p);
					plugin.earth.remove(p);
					p.sendMessage(prefix + ChatColor.GOLD + "Chi Trail enabled!");
					return;
				}
			} else {
				// don't have permission
				event.setCancelled(true);
				p.closeInventory();
				p.sendMessage(prefix + ChatColor.GOLD + "You don't have the necessary permission!");
			}
			return;
			
		// Enable/Disable Avatar Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Avatar Trail")) {
			if (plugin.avatar.contains(p)) {
				// Disable trail
				event.setCancelled(true);
				plugin.avatar.remove(p);
				p.sendMessage(prefix + ChatColor.DARK_PURPLE + "Avatar Trail disabled!");
				return;
			} else if (p.hasPermission("elementaleffects.avatar") || p.hasPermission("elementaleffects.*")) {
				event.setCancelled(true);
				plugin.avatar.add(p);
				plugin.fire.remove(p);
				plugin.water.remove(p);
				plugin.air.remove(p);
				plugin.earth.remove(p);
				plugin.chi.remove(p);
				p.sendMessage(prefix + ChatColor.DARK_PURPLE + "Avatar Trail enabled!");
				return;
			} else {
				// don't have permission
				event.setCancelled(true);
				p.closeInventory();
				p.sendMessage(prefix + ChatColor.DARK_PURPLE + "You don't have the necessary permission!");
			}
			return;
			
		}  else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Air Trail")) {
			if (plugin.air.contains(p)) {
				// Disable trail
				event.setCancelled(true);
				plugin.air.remove(p);
				p.sendMessage(prefix + ChatColor.GRAY + "Air Trail disabled!");
				return;
			} else if (p.hasPermission("elementaleffects.air") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.AIR)) {
						// give trail
						event.setCancelled(true);
						plugin.air.add(p);
						plugin.fire.remove(p);
						plugin.water.remove(p);
						plugin.avatar.remove(p);
						plugin.earth.remove(p);
						plugin.chi.remove(p);
						p.sendMessage(prefix + ChatColor.GRAY + "Air Trail enabled!");
						return;
					} else {
						// don't have the element warning
						event.setCancelled(true);
						p.closeInventory();
						p.sendMessage(prefix + ChatColor.GRAY + "You don't have the necessary element!");
						return;
					}
				} else {
					//give trail
					event.setCancelled(true);
					plugin.air.add(p);
					plugin.fire.remove(p);
					plugin.water.remove(p);
					plugin.avatar.remove(p);
					plugin.earth.remove(p);
					plugin.chi.remove(p);
					p.sendMessage(prefix + ChatColor.GRAY + "Air Trail enabled!");
					return;
				}
			} else {
				// don't have permission
				event.setCancelled(true);
				p.closeInventory();
				p.sendMessage(prefix + ChatColor.GRAY + "You don't have the necessary permission!");
			}
			return;
		}
	}
}