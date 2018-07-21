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
import com.xnuminousx.elementaleffects.gui.IndGui;
import com.xnuminousx.elementaleffects.trails.Move;

public class TrailInvEvent implements Listener {
	
	Main plugin = Main.getInstance();
	String trailGuiName = Manager.getTrailGuiName();
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
			
		// Enable/Disable Earth Trail 2 (Eruption)	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Eruption")) {
			elementColor = ChatColor.DARK_GREEN;
			trailType = "Eruption";
			String enableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.GREEN + " enabled!";
			String disableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.RED + " disabled!";
			String noElement = prefix + elementColor + "You don't have the necessary element!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			
			if (plugin.lava.contains(p)) {
				// Disable trail
				event.setCancelled(true);
				plugin.lava.remove(p);
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.lava") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.EARTH) && bPlayer.hasElement(Element.LAVA)) {
						// Give trail
						event.setCancelled(true);
						giveTrail(p, "lava");
						Move.lavaTrail(p);
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
					giveTrail(p, "lava");
					Move.lavaTrail(p);
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
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Sandy Cloak")) {
			elementColor = ChatColor.YELLOW;
			trailType = "Sandy Cloak";
			String enableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.GREEN + " enabled!";
			String disableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.RED + " disabled!";
			String noElement = prefix + elementColor + "You don't have the necessary element!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			
			if (plugin.sand.contains(p)) {
				// Disable trail
				event.setCancelled(true);
				plugin.sand.remove(p);
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.sand") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.EARTH) && bPlayer.hasElement(Element.SAND)) {
						// Give trail
						event.setCancelled(true);
						giveTrail(p, "sand");
						Move.sandCloak(p);
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
					giveTrail(p, "sand");
					Move.sandCloak(p);
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
			
		// Enable/Disable Fire Trail 2 (Flame Arms)	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Flame Arms")) {
			elementColor = ChatColor.RED;
			trailType = "Flame Arms";
			String enableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.GREEN + " enabled!";
			String disableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.RED + " disabled!";
			String noElement = prefix + elementColor + "You don't have the necessary element!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			if (plugin.fire2.contains(p)) {
				event.setCancelled(true);
				plugin.fire2.remove(p);
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.fire2") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.FIRE)) {
						event.setCancelled(true);
						giveTrail(p, "fire2");
						Move.fireTrail2(p);
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
					giveTrail(p, "fire2");
					Move.fireTrail2(p);
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
			
		// Enable/Disable Lightning (Static Field) Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Static Field")) {
			elementColor = ChatColor.DARK_RED;
			trailType = "Static Field";
			String enableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.GREEN + " enabled!";
			String disableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.RED + " disabled!";
			String noElement = prefix + elementColor + "You don't have the necessary element!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			if (plugin.lightning.contains(p)) {
				event.setCancelled(true);
				plugin.lightning.remove(p);
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.lightning") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.FIRE) && bPlayer.hasElement(Element.LIGHTNING)) {
						event.setCancelled(true);
						giveTrail(p, "lightning");
						Move.staticField(p);
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
					giveTrail(p, "lightning");
					Move.staticField(p);
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
			
			// Enable/Disable Water Trail 2 (Hydro)
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Hydro")) {
			elementColor = ChatColor.BLUE;
			trailType = "Hydro";
			String enableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.GREEN + " enabled!";
			String disableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.RED + " disabled!";
			String noElement = prefix + elementColor + "You don't have the necessary element!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			if (plugin.water2.contains(p)) {
				event.setCancelled(true);
				plugin.water2.remove(p);
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.water2") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.WATER)) {
						event.setCancelled(true);
						giveTrail(p, "water2");
						Move.playWaterRings(p);
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
					giveTrail(p, "water2");
					Move.playWaterRings(p);
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
		
			
		// Enable/Disable Ice Trail	(Ice Shoes)
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Icicle Shoes")) {
			elementColor = ChatColor.DARK_AQUA;
			trailType = "Icicle Shoes";
			String enableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.GREEN + " enabled!";
			String disableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.RED + " disabled!";
			String noElement = prefix + elementColor + "You don't have the necessary element!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			if (plugin.ice.contains(p)) {
				event.setCancelled(true);
				plugin.ice.remove(p);
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.ice") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.WATER) && bPlayer.hasElement(Element.ICE)) {
						event.setCancelled(true);
						giveTrail(p, "ice");
						Move.iceBoots(p);
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
					giveTrail(p, "ice");
					Move.iceBoots(p);
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
			
		//Enable or Disable Chi Trail 2 (Intensity)
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Intensity")) {
			elementColor = ChatColor.YELLOW;
			trailType = "Intensity";
			String enableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.GREEN + " enabled!";
			String disableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.RED + " disabled!";
			String noElement = prefix + elementColor + "You don't have the necessary element!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			if (plugin.chi.contains(p)) {
				event.setCancelled(true);
				plugin.chi.remove(p);
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.chi2") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.CHI)) {
						event.setCancelled(true);
						giveTrail(p, "chi2");
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
					giveTrail(p, "chi2");
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
		
		// Enable/Disable Avatar Trail 2 (Elemental Rings)
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Elemental Rings")) {
			elementColor = ChatColor.DARK_PURPLE;
			trailType = "Elemental Rings";
			String enableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.GREEN + " enabled!";
			String disableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.RED + " disabled!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			if (plugin.avatar2.contains(p)) {
				event.setCancelled(true);
				plugin.avatar2.remove(p);
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.avatar") || p.hasPermission("elementaleffects.*")) {
				event.setCancelled(true);
				giveTrail(p, "avatar2");
				Move.elementalRings(p);
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
			String enableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.GREEN + " enabled!";
			String disableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.RED + " disabled!";
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
			
		// Enable/Disable Air Trail 2 (Aero)	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Aero Sphere")) {
			elementColor = ChatColor.GRAY;
			trailType = "Aero Sphere";
			String enableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.GREEN + " enabled!";
			String disableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.RED + " disabled!";
			String noElement = prefix + elementColor + "You don't have the necessary element!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			if (plugin.air2.contains(p)) {
				event.setCancelled(true);
				plugin.air2.remove(p);
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.air2") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.AIR)) {
						event.setCancelled(true);
						Move.aeroSphere(p);
						giveTrail(p, "air2");
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
					giveTrail(p, "air2");
					Move.aeroSphere(p);
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
			
		// Enable/Disable Flight Trail (Float!)	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Float!")) {
			elementColor = ChatColor.DARK_GRAY;
			trailType = "Float!";
			String enableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.GREEN + " enabled!";
			String disableMessage = prefix + elementColor + ChatColor.BOLD + "" + trailType + ChatColor.RESET + "" + ChatColor.RED + " disabled!";
			String noElement = prefix + elementColor + "You don't have the necessary element!";
			String noPerm = prefix + elementColor + "You don't have the necessary permission!";
			if (plugin.flight.contains(p)) {
				event.setCancelled(true);
				plugin.flight.remove(p);
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.flight") || p.hasPermission("elementaleffects.*")) {
				if (reqEle) {
					if (bPlayer.hasElement(Element.AIR) && bPlayer.hasElement(Element.FLIGHT)) {
						event.setCancelled(true);
						Move.ffloat(p);
						giveTrail(p, "flight");
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
					giveTrail(p, "flight");
					Move.ffloat(p);
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
			
		//Open indicator GUI	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Open Indicator GUI")) {
			event.setCancelled(true);
			IndGui.openGui(p);
			return;
		}
	}
	public void giveTrail(Player p, String element) {
		if (element.equalsIgnoreCase("earth")) {
			plugin.earth.add(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.fire.remove(p);
			plugin.fire2.remove(p);
			plugin.lightning.remove(p);
			plugin.water.remove(p);
			plugin.water2.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.air2.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.avatar2.remove(p);
			plugin.chi.remove(p);
			plugin.chi2.remove(p);
		} else if (element.equalsIgnoreCase("lava")) {
			plugin.lava.add(p);
			plugin.sand.remove(p);
			plugin.fire2.remove(p);
			plugin.lightning.remove(p);
			plugin.earth.remove(p);
			plugin.water.remove(p);
			plugin.water2.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.air2.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.avatar2.remove(p);
			plugin.chi.remove(p);
			plugin.chi2.remove(p);
		} else if (element.equalsIgnoreCase("sand")) {
			plugin.sand.add(p);
			plugin.fire2.remove(p);
			plugin.lightning.remove(p);
			plugin.earth.remove(p);
			plugin.lava.remove(p);
			plugin.water.remove(p);
			plugin.water2.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.air2.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.avatar2.remove(p);
			plugin.chi.remove(p);
			plugin.chi2.remove(p);
		} else if (element.equalsIgnoreCase("fire")) {
			plugin.fire.add(p);
			plugin.fire2.remove(p);
			plugin.lightning.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.water.remove(p);
			plugin.water2.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.air2.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.avatar2.remove(p);
			plugin.chi.remove(p);
			plugin.chi2.remove(p);
		} else if (element.equalsIgnoreCase("fire2")) {
			plugin.fire2.add(p);
			plugin.fire.remove(p);
			plugin.lightning.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.water.remove(p);
			plugin.water2.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.air2.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.avatar2.remove(p);
			plugin.chi.remove(p);
			plugin.chi2.remove(p);
		} else if (element.equalsIgnoreCase("lightning")) {
			plugin.lightning.add(p);
			plugin.fire2.remove(p);
			plugin.fire.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.water.remove(p);
			plugin.water2.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.air2.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.avatar2.remove(p);
			plugin.chi.remove(p);
			plugin.chi2.remove(p);
		} else if (element.equalsIgnoreCase("water")) {
			plugin.water.add(p);
			plugin.water2.remove(p);
			plugin.ice.remove(p);
			plugin.fire.remove(p);
			plugin.fire2.remove(p);
			plugin.lightning.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.air.remove(p);
			plugin.air2.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.avatar2.remove(p);
			plugin.chi.remove(p);
			plugin.chi2.remove(p);
		} else if (element.equalsIgnoreCase("water2")) {
			plugin.water2.add(p);
			plugin.water.remove(p);
			plugin.ice.remove(p);
			plugin.fire.remove(p);
			plugin.fire2.remove(p);
			plugin.lightning.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.air.remove(p);
			plugin.air2.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.avatar2.remove(p);
			plugin.chi.remove(p);
			plugin.chi2.remove(p);
		} else if (element.equalsIgnoreCase("ice")) {
			plugin.ice.add(p);
			plugin.water2.remove(p);
			plugin.water.remove(p);
			plugin.fire.remove(p);
			plugin.fire2.remove(p);
			plugin.lightning.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.air.remove(p);
			plugin.air2.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.avatar2.remove(p);
			plugin.chi.remove(p);
			plugin.chi2.remove(p);
		} else if (element.equalsIgnoreCase("air")) {
			plugin.air.add(p);
			plugin.air2.remove(p);
			plugin.flight.remove(p);
			plugin.fire.remove(p);
			plugin.fire2.remove(p);
			plugin.lightning.remove(p);
			plugin.water.remove(p);
			plugin.water2.remove(p);
			plugin.ice.remove(p);
			plugin.avatar.remove(p);
			plugin.avatar2.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.chi.remove(p);
			plugin.chi2.remove(p);
		} else if (element.equalsIgnoreCase("air2")) {
			plugin.air2.add(p);
			plugin.air.remove(p);
			plugin.flight.remove(p);
			plugin.fire.remove(p);
			plugin.fire2.remove(p);
			plugin.lightning.remove(p);
			plugin.water.remove(p);
			plugin.water2.remove(p);
			plugin.ice.remove(p);
			plugin.avatar.remove(p);
			plugin.avatar2.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.chi.remove(p);
			plugin.chi2.remove(p);
		} else if (element.equalsIgnoreCase("flight")) {
			plugin.flight.add(p);
			plugin.air2.remove(p);
			plugin.air.remove(p);
			plugin.fire.remove(p);
			plugin.fire2.remove(p);
			plugin.lightning.remove(p);
			plugin.water.remove(p);
			plugin.water2.remove(p);
			plugin.ice.remove(p);
			plugin.avatar.remove(p);
			plugin.avatar2.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.chi.remove(p);
			plugin.chi2.remove(p);
		} else if (element.equalsIgnoreCase("chi")) {
			plugin.chi.add(p);
			plugin.chi2.remove(p);
			plugin.fire.remove(p);
			plugin.fire2.remove(p);
			plugin.lightning.remove(p);
			plugin.water.remove(p);
			plugin.water2.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.air2.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.avatar2.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
		} else if (element.equalsIgnoreCase("chi2")) {
			plugin.chi2.add(p);
			plugin.water2.remove(p);
			plugin.water.remove(p);
			plugin.ice.remove(p);
			plugin.fire.remove(p);
			plugin.fire2.remove(p);
			plugin.lightning.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.air.remove(p);
			plugin.air2.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.avatar2.remove(p);
			plugin.chi.remove(p);
		} else if (element.equalsIgnoreCase("avatar")) {
			plugin.avatar.add(p);
			plugin.avatar2.remove(p);
			plugin.fire.remove(p);
			plugin.fire2.remove(p);
			plugin.lightning.remove(p);
			plugin.water.remove(p);
			plugin.water2.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.air2.remove(p);
			plugin.flight.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.chi.remove(p);
			plugin.chi2.remove(p);
		} else if (element.equalsIgnoreCase("avatar2")) {
			plugin.avatar2.add(p);
			plugin.avatar.remove(p);
			plugin.fire.remove(p);
			plugin.fire2.remove(p);
			plugin.lightning.remove(p);
			plugin.water.remove(p);
			plugin.water2.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.air2.remove(p);
			plugin.flight.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.chi.remove(p);
			plugin.chi2.remove(p);
		}
	}
	public void closeInv(Player p) {
		if (closeInv) {
			p.closeInventory();
		}
	}
}
