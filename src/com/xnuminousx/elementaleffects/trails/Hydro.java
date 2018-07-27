package com.xnuminousx.elementaleffects.trails;

import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class Hydro {

	float speed = Main.getInstance().getConfig().getInt("Trails.Water2.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Water2.Particles.Amount");
	
	public Hydro(Player player) {
		progress(player);
	}
	
	public void progress(Player p) {
		GeneralMethods.displayColoredParticle(p.getLocation(), "A8A8FF", 0.3F, 0.3F, 0.3F);
		GeneralMethods.displayColoredParticle(p.getLocation().add(0, 1, 0), "A8A8FF", 0.3F, 0.3F, 0.3F);
		GeneralMethods.displayColoredParticle(p.getLocation().add(0, 2, 0), "A8A8FF", 0.3F, 0.3F, 0.3F);
		ParticleEffect.SPLASH.display(p.getLocation(), 0.2F, 0.2F, 0.2F, 0.2F, 5);
		ParticleEffect.SPLASH.display(p.getLocation().add(0, 1, 0), 0.2F, 0.2F, 0.2F, 0F, 5);
		ParticleEffect.SPLASH.display(p.getLocation().add(0, 2, 0), 0.2F, 0.2F, 0.2F, 0F, 5);
		ParticleEffect.DRIP_WATER.display(p.getLocation(), 0.2F, 0.2F, 0.2F, speed, amount);
		ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 1, 0), 0.2F, 0.2F, 0.2F, speed, amount);
		ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 2, 0), 0.2F, 0.2F, 0.2F, speed, amount);
	}
}
