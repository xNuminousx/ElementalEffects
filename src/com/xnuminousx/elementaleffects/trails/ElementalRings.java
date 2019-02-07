package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.utils.Methods;

public class ElementalRings {
	
	Main plugin = Main.getInstance();
	
	public ElementalRings(Player player) {
		new BukkitRunnable() {
			int anglee;
			int point;
			int currPoint;
			Main plugin = Main.getInstance();
			public void run() {
				for (int i = 0; i < 1.2; ++i) {
					currPoint += 360 / 60;
					if (currPoint > 360) {
						currPoint = 0;
					}
					double angle = currPoint * Math.PI / 180;
					double x = 2 * Math.cos(angle);
					double z = 2 * Math.sin(angle);
					Location loc = player.getLocation().add(x, 1, z);
					ParticleEffect.FLAME.display(loc, 2, 0f, 0f, 0f, 0);
				}
				
				anglee+=12;
				Location location = player.getLocation().add(0, 1, 0);
				double angle = (anglee * Math.PI / 180);
				double xRotation = 3.141592653589793D / 3 * 2.1;
				Vector v = new Vector(Math.cos(angle + point), Math.sin(angle), 0.0D).multiply(1.7);
				Vector negV = new Vector(-Math.cos(angle + point), -Math.sin(angle), 0.0D).multiply(1.7);
				Methods.rotateAroundAxisX(v, xRotation);
				Methods.rotateAroundAxisY(v, -((location.getYaw() * Math.PI / 180) - 1.575));
				Methods.rotateAroundAxisX(negV, -xRotation);
				Methods.rotateAroundAxisY(negV, -((location.getYaw() * Math.PI / 180) - 1.575));

				Methods.playColoredParticle(location.clone().add(v), 5, 0.1, 0.1, 0.1, 6, 193, 255);
				Methods.playColoredParticle(location.clone().add(negV), 5, 0.1, 0.1, 0.1, 117, 71, 25);
				if (anglee == 360) {
					anglee = 0;
				}
				if (plugin.elementrings.isEmpty()) {
					this.cancel();
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
}
