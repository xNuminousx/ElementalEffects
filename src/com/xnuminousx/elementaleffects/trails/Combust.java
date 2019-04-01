package com.xnuminousx.elementaleffects.trails;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.Element.SubElement;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class Combust {
	
	Main plugin = Main.getInstance();
	int currPoint;
	
	int sparkAmount = 3;
	int sparkInterval = 20;
	
	public Combust(Player player) {
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		new BukkitRunnable() {
			@Override
			public void run() {
				if (!Methods.hasPermission(player, "trails", getName()) || !plugin.trails.containsKey(player) || !plugin.trails.get(player).getType().equals(Trails.COMBUST)) {
					this.cancel();
				}
				if (Manager.requireElement()) {
					if (bPlayer.hasElement(Element.FIRE) && bPlayer.hasSubElement(SubElement.COMBUSTION)) {
						progress(player);
					}
				} else {
					progress(player);
				}
			}
		}.runTaskTimer(plugin, 0, 0);
	}
	
	public static String getName() {
		return Methods.normalizeString(Trails.COMBUST.toString());
	}
	
	public void progress(Player player) {
		Random rand = new Random();
		Location location = player.getLocation();
		for (int i = 0; i < 2; i++) {
			currPoint += 360/180;
			if (currPoint == 360) {
				currPoint = 0;
			}
			double angle = currPoint * Math.PI / 180;
			double x = Math.cos(angle * 3);
			double y = Math.cos(angle) * 1.3;
			double z = Math.sin(angle * 3);
			location.add(x, y + 1.3, z);
			player.getWorld().spawnParticle(Particle.SMOKE_NORMAL, location, 3, 0.05, 0.05, 0.05, 0);
			if (rand.nextInt(sparkInterval) == 1) {
				player.getWorld().spawnParticle(Particle.FLAME, location, sparkAmount, 0, 0, 0, 0.05);
			}
			location.subtract(x, y + 1.3, z);
			
		}
		if (rand.nextInt(sparkInterval) == 1) {
			player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, player.getLocation().add(0, 1, 0), sparkAmount, 0, 0, 0, Math.random());
		}
	}
}
