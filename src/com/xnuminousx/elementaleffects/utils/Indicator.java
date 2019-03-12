package com.xnuminousx.elementaleffects.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.xnuminousx.elementaleffects.Main;

public class Indicator {
	private Indicators ind;
	private String name;
	
	public Indicator(Indicators ind) {
		this.ind = ind;
		this.name = ind.toString();
	}
	
	public String getName() {
		return name;
	}
	
	public Indicators getType() {
		return ind;
	}
	
	public static Indicators getActiveIndicator(Player player) {
		HashMap<Player, Indicator> inds = Main.plugin.inds;
		return inds.get(player).getType();
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
