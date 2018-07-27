package com.xnuminousx.elementaleffects.trails;

import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class WaterTrail {
	
	float speed = Main.getInstance().getConfig().getInt("Trails.Water.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Water.Particles.Amount");

	public WaterTrail(Player player) {
		progress(player);
	}
	
	public void progress(Player p) {
		ParticleEffect.SPLASH.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0.5F, 2);
		ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 1, 0), (float) 0.3, (float) 0.3, (float) 0.3, (float) speed, amount);
	}
}
