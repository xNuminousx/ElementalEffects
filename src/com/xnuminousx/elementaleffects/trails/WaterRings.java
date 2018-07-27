package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class WaterRings {
	
	int ringspeed = Main.getInstance().getConfig().getInt("Trails.Water2.Ring.Speed");
	float size = Main.getInstance().getConfig().getInt("Trails.Water2.Ring.Size");
	int points = 60;
	int currPoint;
	Main plugin = Main.getInstance();

	public WaterRings(Player player) {
		new BukkitRunnable() {

			@Override
			public void run() {
				if (plugin.hydro.isEmpty()) {
					this.cancel();
				}
				progress(player);
			}
			
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public void progress(Player p) {
		for (int i = 0; i < ringspeed; ++i) {
			currPoint += 360 / points;
			if (currPoint > 360) {
				currPoint = 0;
			}
			double angle = currPoint * 3.141592653589793D / 180.0D;
			double x = size * Math.cos(angle);
			double z = size * Math.sin(angle);
			Location loc = p.getLocation().add(x, 1, z);
			ParticleEffect.SPLASH.display(loc, 0f, 0f, 0f, 0, 2);
			ParticleEffect.DRIP_WATER.display(loc, 0f, 0f, 0f, 0, 2);
		}
	}
}
