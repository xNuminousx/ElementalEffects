package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.GeneralMethods;
import com.xnuminousx.elementaleffects.Main;

public class SandCloak {
	
	double cloakRadius = Main.getInstance().getConfig().getDouble("Trails.Sand.Cloak.Radius");
	Main plugin = Main.getInstance();
	
	public SandCloak(Player player) {
		new BukkitRunnable() {

			@Override
			public void run() {
				
				progress(player);
				
				if (plugin.sand.isEmpty()) {
					this.cancel();
				}
			}
			
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public void progress(Player p) {
		for (int i = -180; i < 180; i += 10) {
			Location loc = p.getLocation().clone().add(0, cloakRadius, 0);
			Location locUp = p.getLocation().clone().add(0, cloakRadius, 0);
			Location locDown = p.getLocation().clone().add(0, cloakRadius, 0);
			double x = Math.cos(Math.toRadians(i)) * cloakRadius;
			double z = Math.sin(Math.toRadians(i)) * cloakRadius;
			
			GeneralMethods.displayColoredParticle(loc.add(x, 0.5, z), "FBFFBA");
			GeneralMethods.displayColoredParticle(locDown.add(x, 0, z), "FBFFBA");
			GeneralMethods.displayColoredParticle(locUp.add(x, 1, z), "FBFFBA");
			
		}
	}

}
