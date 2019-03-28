package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class WaterRings {
	
	int ringspeed = Main.getInstance().getConfig().getInt("Trails.Water.Particles.Ring.Speed");
	float size = Main.getInstance().getConfig().getInt("Trails.Water.Particles.Ring.Size");
	int points = 60;
	int currPoint;
	Main plugin = Main.getInstance();

	public WaterRings(Player player) {
		new BukkitRunnable() {

			@Override
			public void run() {
				if (!plugin.trails.containsKey(player) || !plugin.trails.get(player).getType().equals(Trails.WATER)) {
					this.cancel();
				}
				BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
				if (Manager.requireElement()) {
					if (bPlayer.hasElement(Element.WATER)) {
						progress(player);
					}
				} else {
					progress(player);
				}
			}
			
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public void progress(Player p) {
		for (int i = 0; i < ringspeed; ++i) {
			currPoint += 360 / points;
			if (currPoint > 360) {
				currPoint = 0;
			}
			double angle = currPoint * Math.PI / 180;
			double x = size * Math.cos(angle);
			double z = size * Math.sin(angle);
			Location loc = p.getLocation().add(x, 1, z);
			ParticleEffect.WATER_SPLASH.display(loc, 2, 0f, 0f, 0f, 0);
			ParticleEffect.DRIP_WATER.display(loc, 2, 0f, 0f, 0f, 0);
		}
	}
}
