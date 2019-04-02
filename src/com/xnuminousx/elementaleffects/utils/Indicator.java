package com.xnuminousx.elementaleffects.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.xnuminousx.elementaleffects.Main;

public class Indicator {
	private Indicators type;
	private String name;
	
	/**
	 * Used when giving a player an indicator
	 * @param type The type of indicator
	 */
	public Indicator(Indicators type) {
		this.type = type;
		this.name = type.toString();
	}
	
	public String getName() {
		return name;
	}
	
	public Indicators getType() {
		return type;
	}
	
	public static Indicators getIndicator(Player player) {
		HashMap<Player, Indicator> inds = Main.plugin.inds;
		return !inds.containsKey(player) ? null : inds.get(player).getType();
	}
	
	public static void removeIndicator(Player player) {
		HashMap<Player, Indicator> inds = Main.plugin.inds;
		if (inds.containsKey(player)) {
			inds.remove(player);
		}
	}
	
	public static void setIndicator(Player player, Indicators type) {
		HashMap<Player, Indicator> inds = Main.plugin.inds;
		inds.put(player, new Indicator(type));
	}
	
	public enum Indicators {
		AVATARSTATE,
		HIT,
		MOON,
		SUN,
	}
}
