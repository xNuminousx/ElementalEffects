package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.utils.Methods;

public class LavaTrail {
	
	double t = 0;
	Main plugin = Main.getInstance();
	int amount = Main.getInstance().getConfig().getInt("Trails.Lava.Particles.Amount");
	int dustamount = 2;
	
	public LavaTrail(Player player) {
		new BukkitRunnable() {

			@Override
			public void run() {
				if (plugin.lava.isEmpty()) {
					this.cancel();
				}
				progress(player);
			}
			
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public void progress(Player p) {
		t += Math.PI / 16;
		Location loc = p.getLocation();
		for (double phi = 0; phi <= 2 * Math.PI; phi += Math.PI / 5) {
			double x = 0.3 * (4 * Math.PI - t) * Math.cos(t + phi);
			double y = 0.2 * t;
			double z = 0.3 * (4 * Math.PI - t) * Math.sin(t + phi);
			loc.add(x, y, z);
			Methods.playColoredParticle(loc, dustamount, 0.1, 0.1, 0.1, 244, 113, 66);
			Methods.playColoredParticle(loc, dustamount, 0.1, 0.1, 0.1, 111, 63, 0);
			loc.subtract(x, y, z);
		
			if (t >= 4 * Math.PI) {
				ParticleEffect.LAVA.display(p.getLocation().add(0, 3, 0), 20, 0F, 0F, 0F, 0);
				t = 0;
			} else if (t >= 3 * Math.PI) {
				dustamount = 1;
			}
		}
	}

}
