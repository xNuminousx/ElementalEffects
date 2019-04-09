package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.GeneralMethods;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class Intensity {
	
	int amount = Main.getInstance().getConfig().getInt("Trails.Intensity.Particles.Amount");
	float speed = Main.getInstance().getConfig().getInt("Trails.Intensity.Particles.Speed");
	boolean dash = Main.getInstance().getConfig().getBoolean("Trails.Intensity.DashEffect.Enabled");
	int dashamount = Main.getInstance().getConfig().getInt("Trails.Intensity.DashEffect.Amount");
	float dashspeed = Main.getInstance().getConfig().getInt("Trails.Intensity.DashEffect.Speed");
	boolean enablesound = Main.getInstance().getConfig().getBoolean("Trails.Intensity.Sound.Enabled");

	public Intensity(Player player) {
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
		return Methods.normalizeString(Trails.INTENSITY.toString());
	}
	
	public void progress(Player p) {
		p.getWorld().spawnParticle(Particle.CRIT, p.getLocation(), amount, 0.2F, 0.2F, 0.2F, (float) speed);
		p.getWorld().spawnParticle(Particle.CLOUD, p.getLocation(), amount, 0.3F, 0.3F, 0.3F, (float) speed);
		Methods.playColoredParticle(p, p.getLocation().add(0, 1, 0), 1, (float) Math.random(), (float) Math.random(), (float) Math.random(), 244, 206, 66);
		
		if (dash) {
			if (!p.isOnGround()) {
				if (enablesound) {
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.25F, 1F);
				}
				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, GeneralMethods.getRightSide(p.getLocation(), .55).add(0, 1.2, 0), dashamount, 0F, 0F, 0F, dashspeed);
				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, GeneralMethods.getLeftSide(p.getLocation(), .55).add(0, 1.2, 0), dashamount, 0F, 0F, 0F, dashspeed);
			}
		}
	}
}
