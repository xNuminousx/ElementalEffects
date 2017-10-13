package com.xnuminousx.elementaleffects.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;

public class InventoryEvent implements Listener {
	
	Main plugin = Main.getInstance();
	String trailGuiName = Manager.getGuiName();
	boolean reqEle = Manager.requireElement();
	boolean closeInv = Manager.closeInv();
	
	boolean doPrefix = Manager.doPrefix();
	String prefix;
	String prefixColor = ChatColor.DARK_AQUA + "" + ChatColor.BOLD;
	
	ChatColor elementColor;
	String trailType;
	
	@EventHandler
	public void onTrailInvClick(InventoryClickEvent event) {
		Player p = (Player)event.getWhoClicked();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(p);
		
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
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.earth") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.EARTH)) {
						// Give trail
						event.setCancelled(true);
						giveTrail(p, "earth");
						closeInv(p);
						p.sendMessage(enableMessage);
						return;
					} else {
						// Don't have the element warning
						event.setCancelled(true);
						closeInv(p);
						p.sendMessage(noElement);
						return;
					}
				} else {
					// Give trail
					event.setCancelled(true);
					giveTrail(p, "earth");
					closeInv(p);
					p.sendMessage(enableMessage);
					return;
				}
			} else {
				// Don't have permission
				event.setCancelled(true);
				closeInv(p);
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
				event.setCancelled(true);
				plugin.fire.remove(p);
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.fire") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.FIRE)) {
						event.setCancelled(true);
						giveTrail(p, "fire");
						closeInv(p);
						p.sendMessage(enableMessage);
						return;
					} else {
						event.setCancelled(true);
						closeInv(p);
						p.sendMessage(noElement);
						return;
					}
				} else {
					event.setCancelled(true);
					giveTrail(p, "fire");
					closeInv(p);
					p.sendMessage(enableMessage);
					return;
				}
			} else {
				event.setCancelled(true);
				closeInv(p);
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
				event.setCancelled(true);
				plugin.water.remove(p);
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.water") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.WATER)) {
						event.setCancelled(true);
						giveTrail(p, "water");
						closeInv(p);
						p.sendMessage(enableMessage);
						return;
					} else {
						event.setCancelled(true);
						closeInv(p);
						p.sendMessage(noElement);
						return;
					}
				} else {
					event.setCancelled(true);
					giveTrail(p, "water");
					closeInv(p);
					p.sendMessage(enableMessage);
					return;
				}
			} else {
				event.setCancelled(true);
				closeInv(p);
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
				event.setCancelled(true);
				plugin.chi.remove(p);
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.chi") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.CHI)) {
						event.setCancelled(true);
						giveTrail(p, "chi");
						closeInv(p);
						p.sendMessage(enableMessage);
						return;
					} else {
						event.setCancelled(true);
						closeInv(p);
						p.sendMessage(noElement);
						return;
					}
				} else {
					event.setCancelled(true);
					giveTrail(p, "chi");
					closeInv(p);
					p.sendMessage(enableMessage);
					return;
				}
			} else {
				event.setCancelled(true);
				closeInv(p);
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
				event.setCancelled(true);
				plugin.avatar.remove(p);
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.avatar") || p.hasPermission("elementaleffects.*")) {
				event.setCancelled(true);
				giveTrail(p, "avatar");
				closeInv(p);
				p.sendMessage(enableMessage);
				return;
			} else {
				event.setCancelled(true);
				closeInv(p);
				p.sendMessage(noPerm);
			}
			return;
		
		// Enable/Disable Air Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Air Trail")) {
			elementColor = ChatColor.GRAY;
			trailType = "Air Trail";
			String enableMessage = prefix + elementColor + trailType + " enabled!";
			String disableMessage = prefix + elementColor + trailType + " disabled!";
			String noElement = prefix + elementColor + "You don't have the necessary element!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			if (plugin.air.contains(p)) {
				event.setCancelled(true);
				plugin.air.remove(p);
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.air") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.AIR)) {
						event.setCancelled(true);
						giveTrail(p, "air");
						closeInv(p);
						p.sendMessage(enableMessage);
						return;
					} else {
						event.setCancelled(true);
						if (closeInv) {
							p.closeInventory();
						}
						p.sendMessage(noElement);
						return;
					}
				} else {
					event.setCancelled(true);
					giveTrail(p, "air");
					closeInv(p);
					p.sendMessage(enableMessage);
					return;
				}
			} else {
				event.setCancelled(true);
				closeInv(p);
				p.sendMessage(noPerm);
			}
			return;
		}
	}
	public void giveTrail(Player p, String element) {
		if (element.equalsIgnoreCase("earth")) {
			plugin.earth.add(p);
			plugin.fire.remove(p);
			plugin.water.remove(p);
			plugin.air.remove(p);
			plugin.avatar.remove(p);
			plugin.chi.remove(p);
		} else if (element.equalsIgnoreCase("fire")) {
			plugin.fire.add(p);
			plugin.earth.remove(p);
			plugin.water.remove(p);
			plugin.air.remove(p);
			plugin.avatar.remove(p);
			plugin.chi.remove(p);
		} else if (element.equalsIgnoreCase("water")) {
			plugin.water.add(p);
			plugin.fire.remove(p);
			plugin.earth.remove(p);
			plugin.air.remove(p);
			plugin.avatar.remove(p);
			plugin.chi.remove(p);
		} else if (element.equalsIgnoreCase("air")) {
			plugin.air.add(p);
			plugin.fire.remove(p);
			plugin.water.remove(p);
			plugin.avatar.remove(p);
			plugin.earth.remove(p);
			plugin.chi.remove(p);
		} else if (element.equalsIgnoreCase("chi")) {
			plugin.chi.add(p);
			plugin.fire.remove(p);
			plugin.water.remove(p);
			plugin.air.remove(p);
			plugin.avatar.remove(p);
			plugin.earth.remove(p);
		} else if (element.equalsIgnoreCase("avatar")) {
			plugin.avatar.add(p);
			plugin.fire.remove(p);
			plugin.water.remove(p);
			plugin.air.remove(p);
			plugin.earth.remove(p);
			plugin.chi.remove(p);
		}
	}
	public void closeInv(Player p) {
		if (closeInv) {
			p.closeInventory();
		}
	}
}