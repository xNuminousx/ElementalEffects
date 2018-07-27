package com.xnuminousx.elementaleffects.trails;

import org.bukkit.entity.Player;

public class AvatarTrail {

	public AvatarTrail(Player player) {
		progress(player);
	}
	
	public void progress(Player p) {
		new FireTrail(p);
		new EarthTrail(p);
		new AirTrail(p);
		new WaterTrail(p);
	}
}
