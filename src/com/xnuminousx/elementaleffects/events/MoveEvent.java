package com.xnuminousx.elementaleffects.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.trails.Trails;

public class MoveEvent implements Listener {

	Main plugin = Main.getInstance();
	
	String prefix = ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "ElementalEffects: ";
	String elementChMess = "Oh no, looks like you've lost your element, therefor your trail is gone!";
	String lackEle = "You don't have the required element!";
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = (Player)e.getPlayer();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(p);
		
		boolean reqEle = Main.getInstance().getConfig().getBoolean("Properties.RequireElement");
		
		if (plugin.earth.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.EARTH)) {
					Trails.earthTrail(p);
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
				Trails.earthTrail(p);
			}
		} else if (plugin.fire.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.FIRE)) {
					Trails.fireTrail(p);
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
				Trails.fireTrail(p);
			}
		} else if (plugin.water.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.WATER)) {
					Trails.waterTrail(p);
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
				Trails.waterTrail(p);
			}
		} else if (plugin.air.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.AIR)) {
					Trails.airTrail(p);
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
				Trails.airTrail(p);
			}
		} else if (plugin.chi.contains(p)) {
			if (reqEle) {
				if (bPlayer.hasElement(Element.CHI)) {
					Trails.chiTrail(p);
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
				Trails.chiTrail(p);
			}
		} else if (plugin.avatar.contains(p)) {
			Trails.avatarTrail(p);
		}
	}
}
