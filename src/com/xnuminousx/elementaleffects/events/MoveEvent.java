package com.xnuminousx.elementaleffects.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.trails.Move;

public class MoveEvent implements Listener {

	Main plugin = Main.getInstance();
	
	boolean doPrefix = Main.getInstance().getConfig().getBoolean("Language.Prefix.Enabled");
	String prefix;
	String prefixColor = ChatColor.DARK_AQUA + "" + ChatColor.BOLD;
	String elementChMess = "Oh no, looks like you've lost your element, therefor your trail is gone!";
	String lackEle = "You don't have the required element!";
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = (Player)e.getPlayer();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(p);
		
		boolean reqEle = Main.getInstance().getConfig().getBoolean("Trails.RequireElement");
		if (doPrefix) {
			prefix = prefixColor + "ElementalEffects: ";
		} else {
			prefix = "";
		}
		
		if (plugin.earth.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.EARTH)) {
					Move.earthTrail(p);
				} else {
					if (plugin.earth.contains(p)) {
						plugin.earth.remove(p);
						p.sendMessage(prefix + ChatColor.GREEN + elementChMess);
						return;
					} else {
						p.sendMessage(prefix + ChatColor.GREEN + lackEle);
						return;
					}
				}
			} else {
				Move.earthTrail(p);
			}
		} else if (plugin.sand.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.EARTH) && bPlayer.hasElement(Element.SAND)) {
					Move.sandTrail(p);;
				} else {
					if (plugin.sand.contains(p)) {
						plugin.sand.remove(p);
						p.sendMessage(prefix + ChatColor.RED + elementChMess);
						return;
					} else {
						p.sendMessage(prefix + ChatColor.RED + lackEle);
						return;
					}
				}
			} else {
				Move.sandTrail(p);
			}
		}  else if (plugin.fire.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.FIRE)) {
					Move.fireTrail(p);
				} else {
					if (plugin.fire.contains(p)) {
						plugin.fire.remove(p);
						p.sendMessage(prefix + ChatColor.RED + elementChMess);
						return;
					} else {
						p.sendMessage(prefix + ChatColor.RED + lackEle);
						return;
					}
				}
			} else {
				Move.fireTrail(p);
			}
		} else if (plugin.fire2.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.FIRE)) {
					Move.fireTrail(p);
				} else {
					if (plugin.fire2.contains(p)) {
						plugin.fire2.remove(p);
						p.sendMessage(prefix + ChatColor.RED + elementChMess);
						return;
					} else {
						p.sendMessage(prefix + ChatColor.RED + lackEle);
						return;
					}
				}
			} else {
				Move.fireTrail(p);
			}
		} else if (plugin.water.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.WATER)) {
					Move.waterTrail(p);
				} else {
					if (plugin.water.contains(p)) {
						plugin.water.remove(p);
						p.sendMessage(prefix + ChatColor.AQUA + elementChMess);
						return;
					} else {
						p.sendMessage(prefix + ChatColor.AQUA + lackEle);
						return;
					}
				}
			} else {
				Move.waterTrail(p);
			}
		} else if (plugin.water2.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.WATER)) {
					Move.waterTrail2(p);
				} else {
					if (plugin.water2.contains(p)) {
						plugin.water2.remove(p);
						p.sendMessage(prefix + ChatColor.BLUE + elementChMess);
						return;
					} else {
						p.sendMessage(prefix + ChatColor.BLUE + lackEle);
						return;
					}
				}
			} else {
				Move.waterTrail2(p);
			}
		} else if (plugin.ice.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.WATER) && bPlayer.hasElement(Element.ICE)) {
					Move.iceTrail(p);
				} else {
					if (plugin.ice.contains(p)) {
						plugin.ice.remove(p);
						p.sendMessage(prefix + ChatColor.DARK_AQUA + elementChMess);
						return;
					} else {
						p.sendMessage(prefix + ChatColor.DARK_AQUA + lackEle);
						return;
					}
				}
			} else {
				Move.iceTrail(p);
			}
		} else if (plugin.air.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.AIR)) {
					Move.airTrail(p);
				} else {
					if (plugin.air.contains(p)) {
						plugin.air.remove(p);
						p.sendMessage(prefix + ChatColor.GRAY + elementChMess);
						return;
					} else {
						p.sendMessage(prefix + ChatColor.GRAY + lackEle);
						return;
					}
				}
			} else {
				Move.airTrail(p);
			}
		} else if (plugin.chi2.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.CHI)) {
					Move.chiTrail2(p);
				} else {
					if (plugin.chi2.contains(p)) {
						plugin.chi2.remove(p);
						p.sendMessage(prefix + ChatColor.YELLOW + elementChMess);
						return;
					} else {
						p.sendMessage(prefix + ChatColor.YELLOW + lackEle);
						return;
					}
				}
			} else {
				Move.chiTrail2(p);
			}
		} else if (plugin.chi.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.CHI)) {
					Move.chiTrail(p);
				} else {
					if (plugin.chi.contains(p)) {
						plugin.chi.remove(p);
						p.sendMessage(prefix + ChatColor.GOLD + elementChMess);
						return;
					} else {
						p.sendMessage(prefix + ChatColor.GOLD + lackEle);
						return;
					}
				}
			} else {
				Move.chiTrail(p);
			}
		} else if (plugin.avatar.contains(p)) {
			Move.avatarTrail(p);
		}
	}
}
