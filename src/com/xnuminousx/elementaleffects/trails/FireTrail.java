package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class FireTrail {
	
	float speed = Main.getInstance().getConfig().getInt("Trails.Fire.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Fire.Particles.Amount");
	
	boolean vanishInWater = Main.getInstance().getConfig().getBoolean("Trails.Fire.DisappearInWater");
	boolean boilEffect = Main.getInstance().getConfig().getBoolean("Trails.Fire.BoilEffect");
	
	public FireTrail(Player player) {
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		if (Manager.requireElement()) {
			if (bPlayer.hasElement(Element.FIRE)) {
				progress(player);
			}
		} else {
			progress(player);
		}
	}
	
	public static String getName() {
		return Methods.normalizeString(Trails.FIRE.toString());
	}
	
	public void progress(Player p) {
		
		Material getBlock = p.getLocation().add(0, 1, 0).getBlock().getType();
		
		if (vanishInWater) {
			if (getBlock.equals(Material.WATER)) {
				if (boilEffect) {
					p.getWorld().spawnParticle(Particle.WATER_BUBBLE, p.getLocation().add(0, 1, 0), 2, 0.5F, 1, 0.5F, 0.5);
				}
			} else {
				p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation().add(0, 1, 0), 2, 0.5F, 0.5F, 0.5F, 0);
				p.getWorld().spawnParticle(Particle.FLAME, p.getLocation().add(0, 1, 0), amount, 0.5, 0.5, 0.5, speed);
			}
		} else {
			if (boilEffect) {
				if (getBlock.equals(Material.WATER)) {
					p.getWorld().spawnParticle(Particle.WATER_BUBBLE, p.getLocation().add(0, 1, 0), 2, 0.5F, 1, 0.5F, 0.5);
				}
			}
			p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation().add(0, 1, 0), 2, 0.5F, 0.5F, 0.5F, 0);
			p.getWorld().spawnParticle(Particle.FLAME, p.getLocation().add(0, 1, 0), amount, 0.5, 0.5, 0.5, speed);
		}
		
	}

}
