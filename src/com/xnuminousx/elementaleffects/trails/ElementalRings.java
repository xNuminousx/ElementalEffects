package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class ElementalRings {
	
	Main plugin = Main.getInstance();
	BlockData water = Material.WATER.createBlockData();
	BlockData dirt = Material.DIRT.createBlockData();
	
	public ElementalRings(Player player) {
		new BukkitRunnable() {
			int anglee;
			int point;
			int currPoint;
			Main plugin = Main.getInstance();
			public void run() {
				for (int i = 0; i < 1.2; ++i) {
					currPoint += 360 / 60;
					if (currPoint > 360) {
						currPoint = 0;
					}
					double angle = currPoint * Math.PI / 180;
					double x = 1.7 * Math.cos(angle);
					double z = 1.7 * Math.sin(angle);
					Location loc = player.getLocation().add(x, 1, z);
					player.getWorld().spawnParticle(Particle.FLAME, loc, 2, 0.1, 0.1, 0.1, 0.03);
				}
				
				anglee+=12;
				Location location = player.getLocation().add(0, 1, 0);
				double angle = (anglee * Math.PI / 180);
				double xRotation = Math.PI / 3 * 2.1;
				Vector v = new Vector(Math.cos(angle + point), Math.sin(angle), 0.0D).multiply(1.7);
				Vector negV = new Vector(-Math.cos(angle + point), -Math.sin(angle), 0.0D).multiply(1.7);
				Methods.rotateAroundAxisX(v, xRotation);
				Methods.rotateAroundAxisY(v, -((location.getYaw() * Math.PI / 180) - 1.575));
				Methods.rotateAroundAxisX(negV, -xRotation);
				Methods.rotateAroundAxisY(negV, -((location.getYaw() * Math.PI / 180) - 1.575));

				player.getWorld().spawnParticle(Particle.BLOCK_CRACK, location.clone().add(v), 10, 0, 0, 0, 0, water);
				player.getWorld().spawnParticle(Particle.BLOCK_CRACK, location.clone().add(negV), 10, 0, 0, 0, 0, dirt);
				if (anglee == 360) {
					anglee = 0;
				}
				if (!Methods.hasPermission(player, "trails", getName()) || 
						!plugin.trails.containsKey(player) || 
						!plugin.trails.get(player).getType().equals(Trails.ELEMENTALRINGS) ||
						!player.isOnline()) {
					this.cancel();
				}
			}
		}.runTaskTimerAsynchronously(Main.getInstance(), 0, 1);
	}
	
	public static String getName() {
		return Methods.normalizeString(Trails.ELEMENTALRINGS.toString());
	}
}
