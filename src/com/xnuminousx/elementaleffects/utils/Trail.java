package com.xnuminousx.elementaleffects.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.xnuminousx.elementaleffects.Main;

public class Trail {
	private Trails type;
	private String name;
	
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
	
	public static Trails getActiveTrail(Player player) {
		HashMap<Player, Trail> trails = Main.plugin.trails;
		return trails.get(player).getType();
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
		HYDRO,
		ICE,
		
		AIR,
		AEROSPHERE,
		FLOAT,
		
		FIRE,
		FLAMEARMS,
		STATICFIELD,
		
		CHI,
		INTENSITY,
		
		AVATAR,
		ELEMENTALRINGS
	}
}
