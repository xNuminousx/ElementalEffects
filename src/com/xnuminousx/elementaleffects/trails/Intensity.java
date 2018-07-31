package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class Intensity {
	
	int amount = Main.getInstance().getConfig().getInt("Trails.Intensity.Particles.Amount");
	float speed = Main.getInstance().getConfig().getInt("Trails.Intensity.Particles.Speed");
	boolean dash = Main.getInstance().getConfig().getBoolean("Trails.Intensity.DashEffect.Enabled");
	int dashamount = Main.getInstance().getConfig().getInt("Trails.Intensity.DashEffect.Amount");
	float dashspeed = Main.getInstance().getConfig().getInt("Trails.Intensity.DashEffect.Speed");
	boolean enablesound = Main.getInstance().getConfig().getBoolean("Trails.Intensity.Sound.Enabled");

	public Intensity(Player player) {
		progress(player);
	}
	
	public void progress(Player p) {
		ParticleEffect.CRIT.display(p.getLocation(), 0.2F, 0.2F, 0.2F, (float) speed, amount);
		ParticleEffect.CLOUD.display(p.getLocation(), 0.3F, 0.3F, 0.3F, (float) speed, amount);
		GeneralMethods.displayColoredParticle(p.getLocation().add(0, 1, 0), "F4CE42", (float) Math.random(), (float) Math.random(), (float) Math.random());
		
		if (dash) {
			if (!p.isOnGround()) {
				if (enablesound) {
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.25F, 1F);
				}
				ParticleEffect.SWEEP.display(GeneralMethods.getRightSide(p.getLocation(), .55).add(0, 1.2, 0), 0F, 0F, 0F, dashspeed, dashamount);
				ParticleEffect.SWEEP.display(GeneralMethods.getLeftSide(p.getLocation(), .55).add(0, 1.2, 0), 0F, 0F, 0F, dashspeed, dashamount);
			}
		}
	}
}
