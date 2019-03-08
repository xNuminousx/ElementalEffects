package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;

public class WaterRings {
	
	int ringspeed = Main.getInstance().getConfig().getInt("Trails.Hydro.Ring.Speed");
	float size = Main.getInstance().getConfig().getInt("Trails.Hydro.Ring.Size");
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
			double angle = currPoint * 3.141592653589793D / 180.0D;
			double x = size * Math.cos(angle);
			double z = size * Math.sin(angle);
			Location loc = p.getLocation().add(x, 1, z);
			ParticleEffect.WATER_SPLASH.display(loc, 2, 0f, 0f, 0f, 0);
			ParticleEffect.DRIP_WATER.display(loc, 2, 0f, 0f, 0f, 0);
		}
	}
}
