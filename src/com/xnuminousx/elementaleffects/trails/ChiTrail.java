package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Particle;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class ChiTrail {
	
	int amount = Main.getInstance().getConfig().getInt("Trails.Chi.Particles.Amount");
	float speed = Main.getInstance().getConfig().getInt("Trails.Chi.Particles.Speed");
	boolean showMagic = Main.getInstance().getConfig().getBoolean("Trails.Chi.Particles.DoMagicCrit");
	
	public ChiTrail(Player player) {
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		if (Manager.requireElement()) {
			if (bPlayer.hasElement(Element.CHI)) {
				progress(player);
			}
		} else {
			progress(player);
		}
	}
	
	public static String getName() {
		return Methods.normalizeString(Trails.CHI.toString());
	}
	
	public void progress(Player p) {
		p.getWorld().spawnParticle(Particle.CRIT, p.getLocation().add(0, 1, 0), amount, 0.5F, 0.5F, 0.5F, (float) speed);
		if (showMagic) {
			p.getWorld().spawnParticle(Particle.CRIT_MAGIC, p.getLocation().add(0, 1, 0), amount, 0.5F, 0.5F, 0.5F, (float) speed);
		}
	}

}
