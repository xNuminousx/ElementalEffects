package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

//Plays with WaterTrail
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
				if (!Methods.hasPermission(player, "trails", WaterTrail.getName()) || 
						!plugin.trails.containsKey(player) || 
						!plugin.trails.get(player).getType().equals(Trails.WATER) ||
						!player.isOnline()) {
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
			p.getWorld().spawnParticle(Particle.WATER_SPLASH, loc, 2, 0f, 0f, 0f, 0);
			p.getWorld().spawnParticle(Particle.DRIP_WATER, loc, 2, 0f, 0f, 0f, 0);
		}
	}
}
