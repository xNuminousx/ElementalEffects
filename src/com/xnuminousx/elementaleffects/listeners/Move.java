package com.xnuminousx.elementaleffects.listeners;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.Element.SubElement;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.trails.AirTrail;
import com.xnuminousx.elementaleffects.trails.AvatarTrail;
import com.xnuminousx.elementaleffects.trails.ChiTrail;
import com.xnuminousx.elementaleffects.trails.EarthTrail;
import com.xnuminousx.elementaleffects.trails.FireTrail;
import com.xnuminousx.elementaleffects.trails.IceTrail;
import com.xnuminousx.elementaleffects.trails.Intensity;
import com.xnuminousx.elementaleffects.trails.SandTrail;
import com.xnuminousx.elementaleffects.trails.WaterTrail;
import com.xnuminousx.elementaleffects.utils.Trail;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class Move implements Listener {
	
	HashMap<Player, Trail> trails;
	BendingPlayer bPlayer;
	String prefix;
	String prefixColor = ChatColor.DARK_AQUA + "" + ChatColor.BOLD;
	boolean doPrefix = Main.getInstance().getConfig().getBoolean("Language.Prefix.Enabled");

	String noEle = prefix + ChatColor.RED + "You do not have the necessary element! Element needed: ";
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		bPlayer = BendingPlayer.getBendingPlayer(player);
		trails = Main.plugin.trails;
		
		if (doPrefix) {
			prefix = prefixColor + "ElementalEffects: ";
		} else {
			prefix = "";
		}
		
		if (player.equals(null)) {
			return;
		} else if (trails.containsKey(player)) {
			Trails type = trails.get(player).getType();
			
			if (type.equals(Trails.EARTH)) {
				if (this.hasEle(bPlayer, Element.EARTH)) {
					new EarthTrail(player);
				} else {
					return;
				}
			} else if (type.equals(Trails.SANDCLOAK)) {
				if (this.hasEle(bPlayer, Element.EARTH) && this.hasSub(Element.SAND)) {
					new SandTrail(player);
				} else {
					return;
				}
				
				
			} else if (type.equals(Trails.AIR)) {
				if (this.hasEle(bPlayer, Element.AIR)) {
					new AirTrail(player);
				} else {
					return;
				}
				
				
			} else if (type.equals(Trails.FIRE)) {
				if (this.hasEle(bPlayer, Element.FIRE)) {
					new FireTrail(player);
				} else {
					return;
				}
			} else if (type.equals(Trails.FLAMEARMS)) {
				if (this.hasEle(bPlayer, Element.FIRE)) {
					new FireTrail(player);
				} else {
					return;
				}
				
				
			} else if (type.equals(Trails.WATER)) {
				if (this.hasEle(bPlayer, Element.WATER)) {
					new WaterTrail(player);
				} else {
					return;
				}
			} else if (type.equals(Trails.ICE)) {
				if (this.hasEle(bPlayer, Element.WATER) && this.hasEle(bPlayer, Element.ICE)) {
					new IceTrail(player);
				} else {
					return;
				}
				
				
			} else if (type.equals(Trails.CHI)) {
				if (this.hasEle(bPlayer, Element.CHI)) {
					new ChiTrail(player);
				} else {
					return;
				}
			} else if (type.equals(Trails.INTENSITY)) {
				if (this.hasEle(bPlayer, Element.CHI)) {
					new Intensity(player);
				} else {
					return;
				}
				
				
			} else if (type.equals(Trails.AVATAR)) {
				if (this.hasEle(bPlayer, Element.AVATAR)) {
					new AvatarTrail(player);
				} else {
					return;
				}
			}
		} else {
			return;
		}
	}
	public boolean hasEle(BendingPlayer bPlayer, Element element) {
		boolean reqEle = Manager.requireElement();
		if (reqEle) {
			if (bPlayer.hasElement(element)) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	public boolean hasSub(SubElement sub) {
		boolean reqEle = Manager.requireElement();
		if (reqEle) {
			if (bPlayer.hasSubElement(sub)) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
}
