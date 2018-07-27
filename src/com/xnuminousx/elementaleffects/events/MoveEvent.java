package com.xnuminousx.elementaleffects.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.trails.AirTrail;
import com.xnuminousx.elementaleffects.trails.AvatarTrail;
import com.xnuminousx.elementaleffects.trails.ChiTrail;
import com.xnuminousx.elementaleffects.trails.EarthTrail;
import com.xnuminousx.elementaleffects.trails.FireTrail;
import com.xnuminousx.elementaleffects.trails.Hydro;
import com.xnuminousx.elementaleffects.trails.IceTrail;
import com.xnuminousx.elementaleffects.trails.Intensity;
import com.xnuminousx.elementaleffects.trails.SandTrail;
import com.xnuminousx.elementaleffects.trails.WaterTrail;

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
					new EarthTrail(p);
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
				new EarthTrail(p);
			}
		} else if (plugin.sand.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.EARTH) && bPlayer.hasElement(Element.SAND)) {
					new SandTrail(p);
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
				new SandTrail(p);
			}
		}  else if (plugin.fire.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.FIRE)) {
					new FireTrail(p);
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
				new FireTrail(p);
			}
		} else if (plugin.flamearms.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.FIRE)) {
					new FireTrail(p);
				} else {
					if (plugin.flamearms.contains(p)) {
						plugin.flamearms.remove(p);
						p.sendMessage(prefix + ChatColor.RED + elementChMess);
						return;
					} else {
						p.sendMessage(prefix + ChatColor.RED + lackEle);
						return;
					}
				}
			} else {
				new FireTrail(p);
			}
		} else if (plugin.water.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.WATER)) {
					new WaterTrail(p);
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
				new WaterTrail(p);
			}
		} else if (plugin.hydro.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.WATER)) {
					new Hydro(p);
				} else {
					if (plugin.hydro.contains(p)) {
						plugin.hydro.remove(p);
						p.sendMessage(prefix + ChatColor.BLUE + elementChMess);
						return;
					} else {
						p.sendMessage(prefix + ChatColor.BLUE + lackEle);
						return;
					}
				}
			} else {
				new Hydro(p);
			}
		} else if (plugin.ice.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.WATER) && bPlayer.hasElement(Element.ICE)) {
					new IceTrail(p);
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
				new IceTrail(p);
			}
		} else if (plugin.air.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.AIR)) {
					new AirTrail(p);
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
				new AirTrail(p);
			}
		} else if (plugin.intensity.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.CHI)) {
					new Intensity(p);
				} else {
					if (plugin.intensity.contains(p)) {
						plugin.intensity.remove(p);
						p.sendMessage(prefix + ChatColor.YELLOW + elementChMess);
						return;
					} else {
						p.sendMessage(prefix + ChatColor.YELLOW + lackEle);
						return;
					}
				}
			} else {
				new Intensity(p);
			}
		} else if (plugin.chi.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.CHI)) {
					new ChiTrail(p);
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
				new ChiTrail(p);
			}
		} else if (plugin.avatar.contains(p)) {
			new AvatarTrail(p);
		}
	}
}
