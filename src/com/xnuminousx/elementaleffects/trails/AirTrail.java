package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.ability.AirAbility;
import com.xnuminousx.elementaleffects.Main;

public class AirTrail {
	
	float speed = Main.getInstance().getConfig().getInt("Trails.Air.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Air.Particles.Amount");
	boolean doCloud = Main.getInstance().getConfig().getBoolean("Trails.Air.CloudEffect");
	boolean vanishInWater = Main.getInstance().getConfig().getBoolean("Trails.Air.DisappearInWater");
	
	public AirTrail(Player player) {
		progress(player);
	}
	
	public void progress(Player p) {
		Material getBlock = p.getLocation().add(0, -1, 0).getBlock().getType();
		
		if (vanishInWater) {
			
			if (getBlock.equals(Material.STATIONARY_WATER)) {
				return;
			} else {
				if (doCloud) {
					if (getBlock.equals(Material.AIR)) {
						AirAbility.getAirbendingParticles().display(p.getLocation(), (float) 0.5, 0, (float) 0.5, (float) speed, 5);
						return;
					} else {
						AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
						return;
					}
				} else {
					AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
					return;
				}
			}
		} else {
			if (doCloud) {
				if (getBlock.equals(Material.AIR)) {
					AirAbility.getAirbendingParticles().display(p.getLocation(), (float) 0.5, 0, (float) 0.5, (float) speed, 5);
					return;
				} else {
					AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
					return;
				}
			} else {
				AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
				return;
			}
		}
		
	}

}
