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
	boolean doPrefix = Main.getInstance().getConfig().getBoolean("Language.Prefix.Enabled");
	
	String trailGuiName = Main.getInstance().getConfig().getString("Language.TrailGUIName");
	
	String prefix;
	String prefixColor = ChatColor.DARK_AQUA + "" + ChatColor.BOLD;
	
	ChatColor elementColor;
	String trailType;
	
	@EventHandler
	public void onTrailInvClick(InventoryClickEvent event) {
		Player p = (Player)event.getWhoClicked();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(p);
		
		boolean reqEle = Main.getInstance().getConfig().getBoolean("Trails.Properties.RequireElement");
		boolean closeInv = Main.getInstance().getConfig().getBoolean("Trails.CloseInventoryOnSelect");
		
		if (doPrefix) {
			prefix = prefixColor + "ElementalEffects: ";
		} else {
			prefix = "";
		}
		
		if (event.getInventory().getName() != trailGuiName) {
			return;
		}
		
		if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getItemMeta().getDisplayName().equals(null)) {
			event.setCancelled(true);
			return;
			
		// Enable/Disable Earth Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Earth Trail")) {
			elementColor = ChatColor.GREEN;
			trailType = "Earth Trail";
			String enableMessage = prefix + elementColor + trailType + " enabled!";
			String disableMessage = prefix + elementColor + trailType + " disabled!";
			String noElement = prefix + elementColor + "You don't have the necessary element!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			
			if (plugin.earth.contains(p)) {
				// Disable trail
				event.setCancelled(true);
				plugin.earth.remove(p);
				if (closeInv) {
					p.closeInventory();
				}
				p.sendMessage(disableMessage);
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
						p.sendMessage(enableMessage);
						return;
					} else {
						// don't have the element warning
						event.setCancelled(true);
						if (closeInv) {
							p.closeInventory();
						}
						p.sendMessage(noElement);
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
					if (closeInv) {
						p.closeInventory();
					}
					p.sendMessage(enableMessage);
					return;
				}
			} else {
				// don't have permission
				event.setCancelled(true);
				if (closeInv) {
					p.closeInventory();
				}
				p.sendMessage(noPerm);
			}
			return;
			
		// Enable/Disable Fire Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Fire Trail")) {
			elementColor = ChatColor.RED;
			trailType = "Fire Trail";
			String enableMessage = prefix + elementColor + trailType + " enabled!";
			String disableMessage = prefix + elementColor + trailType + " disabled!";
			String noElement = prefix + elementColor + "You don't have the necessary element!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			if (plugin.fire.contains(p)) {
				// Disable trail
				event.setCancelled(true);
				plugin.fire.remove(p);
				if (closeInv) {
					p.closeInventory();
				}
				p.sendMessage(disableMessage);
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
						if (closeInv) {
							p.closeInventory();
						}
						p.sendMessage(enableMessage);
						return;
					} else {
						// don't have the element warning
						event.setCancelled(true);
						if (closeInv) {
							p.closeInventory();
						}
						p.sendMessage(noElement);
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
					if (closeInv) {
						p.closeInventory();
					}
					p.sendMessage(enableMessage);
					return;
				}
			} else {
				// don't have permission
				event.setCancelled(true);
				if (closeInv) {
					p.closeInventory();
				}
				p.sendMessage(noPerm);
			}
			return;
			
		// Enable/Disable Water Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Water Trail")) {
			elementColor = ChatColor.AQUA;
			trailType = "Water Trail";
			String enableMessage = prefix + elementColor + trailType + " enabled!";
			String disableMessage = prefix + elementColor + trailType + " disabled!";
			String noElement = prefix + elementColor + "You don't have the necessary element!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			if (plugin.water.contains(p)) {
				// Disable trail
				event.setCancelled(true);
				plugin.water.remove(p);
				if (closeInv) {
					p.closeInventory();
				}
				p.sendMessage(disableMessage);
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
						if (closeInv) {
							p.closeInventory();
						}
						p.sendMessage(enableMessage);
						return;
					} else {
						// don't have the element warning
						event.setCancelled(true);
						if (closeInv) {
							p.closeInventory();
						}
						p.sendMessage(noElement);
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
					if (closeInv) {
						p.closeInventory();
					}
					p.sendMessage(enableMessage);
					return;
				}
			} else {
				// don't have permission
				event.setCancelled(true);
				if (closeInv) {
					p.closeInventory();
				}
				p.sendMessage(noPerm);
			}
			return;
		
			
		// Enable/Disable Chi Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Chi Trail")) {
			elementColor = ChatColor.GOLD;
			trailType = "Chi Trail";
			String enableMessage = prefix + elementColor + trailType + " enabled!";
			String disableMessage = prefix + elementColor + trailType + " disabled!";
			String noElement = prefix + elementColor + "You don't have the necessary element!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			if (plugin.chi.contains(p)) {
				// Disable trail
				event.setCancelled(true);
				plugin.chi.remove(p);
				if (closeInv) {
					p.closeInventory();
				}
				p.sendMessage(disableMessage);
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
						if (closeInv) {
							p.closeInventory();
						}
						p.sendMessage(enableMessage);
						return;
					} else {
						// don't have the element warning
						event.setCancelled(true);
						if (closeInv) {
							p.closeInventory();
						}
						p.sendMessage(noElement);
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
					if (closeInv) {
						p.closeInventory();
					}
					p.sendMessage(enableMessage);
					return;
				}
			} else {
				// don't have permission
				event.setCancelled(true);
				if (closeInv) {
					p.closeInventory();
				}
				p.sendMessage(noPerm);
			}
			return;
			
		// Enable/Disable Avatar Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Avatar Trail")) {
			elementColor = ChatColor.DARK_PURPLE;
			trailType = "Avatar Trail";
			String enableMessage = prefix + elementColor + trailType + " enabled!";
			String disableMessage = prefix + elementColor + trailType + " disabled!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			if (plugin.avatar.contains(p)) {
				// Disable trail
				event.setCancelled(true);
				plugin.avatar.remove(p);
				if (closeInv) {
					p.closeInventory();
				}
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.avatar") || p.hasPermission("elementaleffects.*")) {
				event.setCancelled(true);
				plugin.avatar.add(p);
				plugin.fire.remove(p);
				plugin.water.remove(p);
				plugin.air.remove(p);
				plugin.earth.remove(p);
				plugin.chi.remove(p);
				if (closeInv) {
					p.closeInventory();
				}
				p.sendMessage(enableMessage);
				return;
			} else {
				// don't have permission
				event.setCancelled(true);
				if (closeInv) {
					p.closeInventory();
				}
				p.sendMessage(noPerm);
			}
			return;
			
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Air Trail")) {
			elementColor = ChatColor.GRAY;
			trailType = "Air Trail";
			String enableMessage = prefix + elementColor + trailType + " enabled!";
			String disableMessage = prefix + elementColor + trailType + " disabled!";
			String noElement = prefix + elementColor + "You don't have the necessary element!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			if (plugin.air.contains(p)) {
				// Disable trail
				event.setCancelled(true);
				plugin.air.remove(p);
				if (closeInv) {
					p.closeInventory();
				}
				p.sendMessage(disableMessage);
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
						if (closeInv) {
							p.closeInventory();
						}
						p.sendMessage(enableMessage);
						return;
					} else {
						// don't have the element warning
						event.setCancelled(true);
						if (closeInv) {
							p.closeInventory();
						}
						p.sendMessage(noElement);
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
					if (closeInv) {
						p.closeInventory();
					}
					p.sendMessage(enableMessage);
					return;
				}
			} else {
				// don't have permission
				event.setCancelled(true);
				if (closeInv) {
					p.closeInventory();
				}
				p.sendMessage(noPerm);
			}
			return;
		}
	}
}