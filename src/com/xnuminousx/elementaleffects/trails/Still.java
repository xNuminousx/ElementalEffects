package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.projectkorra.projectkorra.util.ParticleEffect;

public class Still implements Listener {
	
	public static void fireTrail(Player p) {
		Location location = p.getLocation();
		for (int i = 0; i < 6; i++) {
			int currPoint = 360 / 50;
			if (currPoint > 360) {
				currPoint = 0;
			}
			double angle = currPoint * Math.PI / 180 * Math.cos(Math.PI);
			double x = 0.04 * (Math.PI * 4 - angle) * Math.cos(angle + i);
            double y = 1.2 * Math.cos(angle) + 1.2;
            double z = 0.04F * (Math.PI * 4 - angle) * Math.sin(angle + i);
			location.add(x, y, z);
			ParticleEffect.FLAME.display(location, 0, 0, 0, 0, 1);
			location.subtract(x, y, z);
		}
	}
	
	public static void earthTrail(Player p) {
		
	}
	
	public static void waterTrail(Player p) {
		
	}
	
	public static void airTrail(Player p) {
		
	}
	
	public static void chiTrail(Player p) {
		
	}
	
	public static void avatarTrail(Player p) {
		
	}
}
