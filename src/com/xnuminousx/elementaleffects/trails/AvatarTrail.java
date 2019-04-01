package com.xnuminousx.elementaleffects.trails;

import org.bukkit.entity.Player;

import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class AvatarTrail {

	public AvatarTrail(Player player) {
		if (Methods.hasPermission(player, "trails", getName())) {
			progress(player);
		}
	}
	
	public static String getName() {
		return Methods.normalizeString(Trails.AVATAR.toString());
	}
	
	public void progress(Player p) {
		new FireTrail(p);
		new EarthTrail(p);
		new AirTrail(p);
		new WaterTrail(p);
	}
}
