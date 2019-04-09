package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Particle;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class WaterTrail {
	
	float speed = Main.getInstance().getConfig().getInt("Trails.Water.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Water.Particles.Amount");

	public WaterTrail(Player player) {
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		if (Manager.requireElement()) {
			if (bPlayer.hasElement(Element.WATER)) {
				progress(player);
			}
		} else {
			progress(player);
		}
	}
	
	public static String getName() {
		return Methods.normalizeString(Trails.WATER.toString());
	}
	
	public void progress(Player p) {
		p.getWorld().spawnParticle(Particle.WATER_SPLASH, p.getLocation().add(0, 1, 0), 2, 0.5F, 0.5F, 0.5F, 0.5F);
		p.getWorld().spawnParticle(Particle.DRIP_WATER, p.getLocation().add(0, 1, 0), amount, (float) 0.3, (float) 0.3, (float) 0.3, (float) speed);
	}
}
