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
import com.xnuminousx.elementaleffects.utils.Methods;
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
			
			if (type.equals(Trails.EARTH) && Methods.hasPermission(player, "trails", EarthTrail.getName())) {
				if (this.hasEle(Element.EARTH)) {
					new EarthTrail(player);
				} else {
					return;
				}
			} else if (type.equals(Trails.SANDCLOAK) && Methods.hasPermission(player, "trails", SandTrail.getName())) {
				if (this.hasEle(Element.EARTH) && this.hasSub(SubElement.SAND)) {
					new SandTrail(player);
				} else {
					return;
				}
				
				
			} else if (type.equals(Trails.AIR) && Methods.hasPermission(player, "trails", AirTrail.getName())) {
				if (this.hasEle(Element.AIR)) {
					new AirTrail(player);
				} else {
					return;
				}
				
				
			} else if (type.equals(Trails.FIRE) && Methods.hasPermission(player, "trails", FireTrail.getName())) {
				if (this.hasEle(Element.FIRE)) {
					new FireTrail(player);
				} else {
					return;
				}
				
				
			} else if (type.equals(Trails.WATER) && Methods.hasPermission(player, "trails", WaterTrail.getName())) {
				if (this.hasEle(Element.WATER)) {
					new WaterTrail(player);
				} else {
					return;
				}
			} else if (type.equals(Trails.ICE) && Methods.hasPermission(player, "trails", IceTrail.getName())) {
				if (this.hasEle(Element.WATER) && this.hasSub(SubElement.ICE)) {
					new IceTrail(player);
				} else {
					return;
				}
				
				
			} else if (type.equals(Trails.CHI) && Methods.hasPermission(player, "trails", ChiTrail.getName())) {
				if (this.hasEle(Element.CHI)) {
					new ChiTrail(player);
				} else {
					return;
				}
			} else if (type.equals(Trails.INTENSITY) && Methods.hasPermission(player, "trails", Intensity.getName())) {
				if (this.hasEle(Element.CHI)) {
					new Intensity(player);
				} else {
					return;
				}
				
				
			} else if (type.equals(Trails.AVATAR) && Methods.hasPermission(player, "trails", AvatarTrail.getName())) {
				if (this.hasEle(Element.AVATAR)) {
					new AvatarTrail(player);
				} else {
					return;
				}
			}
		} else {
			return;
		}
	}
	public boolean hasEle(Element element) {
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
