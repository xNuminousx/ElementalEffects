package com.xnuminousx.elementaleffects.trails;

import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;

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
	
	public void progress(Player p) {
		ParticleEffect.WATER_SPLASH.display(p.getLocation().add(0, 1, 0), 2, 0.5F, 0.5F, 0.5F, 0.5F);
		ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 1, 0), amount, (float) 0.3, (float) 0.3, (float) 0.3, (float) speed);
	}
}
