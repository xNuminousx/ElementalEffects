package com.xnuminousx.elementaleffects.trails;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class Float {
	
	Main plugin = Main.getInstance();
	float speed = Main.getInstance().getConfig().getInt("Trails.Flight.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Flight.Particles.Amount");
	boolean enablesparks = Main.getInstance().getConfig().getBoolean("Trails.Flight.Sparks.Enabled");
	
	public Float(Player player) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (plugin.flight.isEmpty()) {
					this.cancel();
				}
				progress(player);
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public void progress(Player p) {
		if (p.isSprinting()) {
			ParticleEffect.SWEEP_ATTACK.display(p.getLocation().add(0, 1, 0), 1, 0.5F, 0.5F, 0.5F, 0);
			if (enablesparks) {
				ParticleEffect.FIREWORKS_SPARK.display(p.getLocation().add(0, 1, 0), 3, 0.5F, 0.5F, 0.5F, 0.05F);
			}
		}
		ParticleEffect.CLOUD.display(GeneralMethods.getRightSide(p.getLocation(), .55).add(0, 1.2, 0), amount, 0F, 0F, 0F, speed);
		ParticleEffect.CLOUD.display(GeneralMethods.getLeftSide(p.getLocation(), .55).add(0, 1.2, 0), amount, 0F, 0F, 0F, speed);
		ParticleEffect.CLOUD.display(GeneralMethods.getRightSide(p.getLocation(), -.55).add(0, 0, 0), amount, 0F, 0F, 0F, speed);
		ParticleEffect.CLOUD.display(GeneralMethods.getLeftSide(p.getLocation(), -.55).add(0, 0, 0), amount, 0F, 0F, 0F, speed);
	}

}
