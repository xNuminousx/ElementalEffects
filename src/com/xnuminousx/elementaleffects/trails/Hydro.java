package com.xnuminousx.elementaleffects.trails;

import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;

public class Hydro {

	float speed = Main.getInstance().getConfig().getInt("Trails.Water2.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Water2.Particles.Amount");
	
	public Hydro(Player player) {
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		if (Manager.requireElement()) {
			if (bPlayer.hasElement(Element.WATER)) {
				progress(player);
			}
		} else {
			progress(player);
		}
	}
	
	public void progress(Player p) {
		Methods.playColoredParticle(p.getLocation(), 1, 0.3, 0.3, 0.3, 168, 168, 255);
		Methods.playColoredParticle(p.getLocation().add(0, 1, 0), 1, 0.3, 0.3, 0.3, 168, 168, 255);
		Methods.playColoredParticle(p.getLocation().add(0, 2, 0), 1, 0.3, 0.3, 0.3, 168, 168, 255);
		ParticleEffect.WATER_SPLASH.display(p.getLocation(), 5, 0.2F, 0.2F, 0.2F, 0.2F);
		ParticleEffect.WATER_SPLASH.display(p.getLocation().add(0, 1, 0), 5, 0.2F, 0.2F, 0.2F, 0F);
		ParticleEffect.WATER_SPLASH.display(p.getLocation().add(0, 2, 0), 5, 0.2F, 0.2F, 0.2F, 0F);
		ParticleEffect.DRIP_WATER.display(p.getLocation(), amount, 0.2F, 0.2F, 0.2F, speed);
		ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 1, 0), amount, 0.2F, 0.2F, 0.2F, speed);
		ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 2, 0), amount, 0.2F, 0.2F, 0.2F, speed);
	}
}
