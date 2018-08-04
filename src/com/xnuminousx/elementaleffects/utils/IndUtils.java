package com.xnuminousx.elementaleffects.utils;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import com.xnuminousx.elementaleffects.Main;

public class IndUtils {

	public static ArrayList<Player> getActiveIndicator(Player player) {
		Main plugin = Main.getInstance();
		
		if (plugin.hit.contains(player)) {
			return plugin.hit;
		} else if (plugin.avatarstate.contains(player)) {
			return plugin.avatarstate;
		} else if (plugin.moon.contains(player)) {
			return plugin.moon;
		} else if (plugin.sun.contains(player)) {
			return plugin.sun;
		} else {
			return null;
		}
	}
	
	public static void removeActiveIndicator(ArrayList<Player> object, Player player) {
		if (object.contains(player)) {
			object.remove(player);
		}
	}
	
	public static void removeActiveIndicators(Player player) {
		Main plugin = Main.getInstance();
		
		plugin.avatarstate.remove(player);
		plugin.hit.remove(player);
		plugin.moon.remove(player);
		plugin.sun.remove(player);
	}
}