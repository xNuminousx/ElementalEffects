package com.xnuminousx.elementaleffects.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.xnuminousx.elementaleffects.Main;

public class Trail {
	private Trails type;
	private String name;
	
	/**
	 * Used when giving a player a trail
	 * @param type The type of trail
	 */
	public Trail(Trails type) {
		this.type = type;
		this.name = type.toString();
	}
	
	public String getName() {
		return name;
	}
	
	public Trails getType() {
		return type;
	}
	
	public static Trails getTrail(Player player) {
		HashMap<Player, Trail> trails = Main.plugin.trails;
		return !trails.containsKey(player) ? null : trails.get(player).getType();
	}
	
	public static void removeTrail(Player player) {
		HashMap<Player, Trail> trails = Main.plugin.trails;
		if (trails.containsKey(player)) {
			trails.remove(player);
		}
	}
	
	public static void setTrail(Player player, Trails trail) {
		HashMap<Player, Trail> trails = Main.plugin.trails;
		trails.put(player, new Trail(trail));
	}
	
	public enum Trails {
		EARTH,
		SANDCLOAK,
		ERUPTION,
		
		WATER,
		BLOOD,
		ICE,
		
		AIR,
		AEROSPHERE,
		FLOAT,
		
		FIRE,
		STATICFIELD,
		COMBUST,
		
		CHI,
		INTENSITY,
		
		AVATAR,
		ELEMENTALRINGS;
	}
}
