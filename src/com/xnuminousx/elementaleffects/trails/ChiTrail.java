package com.xnuminousx.elementaleffects.trails;

import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class ChiTrail {
	
	int amount = Main.getInstance().getConfig().getInt("Trails.Chi.Particles.Amount");
	float speed = Main.getInstance().getConfig().getInt("Trails.Chi.Particles.Speed");
	boolean doMagic = Main.getInstance().getConfig().getBoolean("Trails.Chi.Particles.DoMagicCrit");
	
	public ChiTrail(Player player) {
		progress(player);
	}
	
	public void progress(Player p) {
		ParticleEffect.CRIT.display(p.getLocation().add(0, 1, 0), amount, 0.5F, 0.5F, 0.5F, (float) speed);
		if (doMagic) {
			ParticleEffect.CRIT_MAGIC.display(p.getLocation().add(0, 1, 0), amount, 0.5F, 0.5F, 0.5F, (float) speed);
		}
	}

}
