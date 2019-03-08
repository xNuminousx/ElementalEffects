package com.xnuminousx.elementaleffects.trails;

import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;

public class ChiTrail {
	
	int amount = Main.getInstance().getConfig().getInt("Trails.Chi.Particles.Amount");
	float speed = Main.getInstance().getConfig().getInt("Trails.Chi.Particles.Speed");
	boolean doMagic = Main.getInstance().getConfig().getBoolean("Trails.Chi.Particles.DoMagicCrit");
	
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
	
	public void progress(Player p) {
		ParticleEffect.CRIT.display(p.getLocation().add(0, 1, 0), amount, 0.5F, 0.5F, 0.5F, (float) speed);
		if (doMagic) {
			ParticleEffect.CRIT_MAGIC.display(p.getLocation().add(0, 1, 0), amount, 0.5F, 0.5F, 0.5F, (float) speed);
		}
	}

}
