package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class FireTrail {
	
	float speed = Main.getInstance().getConfig().getInt("Trails.Fire.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Fire.Particles.Amount");
	
	boolean vanishInWater = Main.getInstance().getConfig().getBoolean("Trails.Fire.DisappearInWater");
	boolean boilEffect = Main.getInstance().getConfig().getBoolean("Trails.Fire.BoilEffect");
	
	public FireTrail(Player player) {
		progress(player);
	}
	
	public void progress(Player p) {
		
		Material getBlock = p.getLocation().add(0, 1, 0).getBlock().getType();
		
		if (vanishInWater) {
			if (getBlock.equals(Material.STATIONARY_WATER)) {
				if (boilEffect) {
					ParticleEffect.BUBBLE.display(p.getLocation().add(0, 1, 0), 0.5F, 1, 0.5F, 0.5F, 2);
				}
				return;
			} else {
				ParticleEffect.SMOKE.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
				ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
				return;
			}
		} else {
			if (boilEffect) {
				if (getBlock.equals(Material.STATIONARY_WATER)) {
					ParticleEffect.BUBBLE.display(p.getLocation().add(0, 1, 0), 0.5F, 1, 0.5F, 0.5F, 2);
					return;
				}
			}
			ParticleEffect.SMOKE.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
			ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
		}
		
	}

}
