package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;

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
	
	public void progress(Player p) {
		
		Material getBlock = p.getLocation().add(0, 1, 0).getBlock().getType();
		
		if (vanishInWater) {
			if (getBlock.equals(Material.WATER)) {
				if (boilEffect) {
					ParticleEffect.WATER_BUBBLE.display(p.getLocation().add(0, 1, 0), 2, 0.5F, 1, 0.5F, 0.5);
				}
				return;
			} else {
				ParticleEffect.SMOKE_NORMAL.display(p.getLocation().add(0, 1, 0), 2, 0.5F, 0.5F, 0.5F, 0);
				ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), amount, 0.5, 0.5, 0.5, speed);
				return;
			}
		} else {
			if (boilEffect) {
				if (getBlock.equals(Material.WATER)) {
					ParticleEffect.WATER_BUBBLE.display(p.getLocation().add(0, 1, 0), 2, 0.5F, 1, 0.5F, 0.5F);
					return;
				}
			}
			ParticleEffect.SMOKE_NORMAL.display(p.getLocation().add(0, 1, 0), 2, 0.5F, 0.5F, 0.5F, 0);
			ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), amount, 0.5, 0.5, 0.5, speed);
		}
		
	}

}
