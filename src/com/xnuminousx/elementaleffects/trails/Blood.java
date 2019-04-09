package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class Blood {

	Main plugin = Main.getInstance();
	BendingPlayer bPlayer;
	int currPoint;
	BlockData block = Material.REDSTONE_BLOCK.createBlockData();
	
	public Blood(Player player) {
		bPlayer = BendingPlayer.getBendingPlayer(player);
		player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation().add(0, 1, 0), 20, 0.2, 0.2, 0.2, 0, block);
		new BukkitRunnable() {
			@Override
			public void run() {
				if (!Methods.hasPermission(player, "trails", getName()) || !plugin.trails.containsKey(player) || !plugin.trails.get(player).getType().equals(Trails.BLOOD)) {
					this.cancel();
				}
				if (Manager.requireElement()) {
					if (bPlayer.hasElement(Element.WATER) && bPlayer.hasElement(Element.BLOOD)) {
						progress(player);
					}
				} else {
					progress(player);
				}
			}
		}.runTaskTimer(Main.plugin, 0, 0);
	}
	
	public static String getName() {
		return Methods.normalizeString(Trails.BLOOD.toString());
	}
	
	public void progress(Player player) {
		Location location = player.getLocation();
		for (int i = 0; i < 1; i++) {
			currPoint += 360/180;
			if (currPoint == 180 || currPoint == 360) {
				player.getWorld().spawnParticle(Particle.BLOCK_CRACK, location.add(0, 1, 0), 20, 0.1, 0.1, 0.1, 0, block);
			}
			if (currPoint == 360) {
				currPoint = 0;
			}
			double angle = currPoint * Math.PI / 180;
			double x = Math.cos(angle) * Math.sin(angle) * 2;
			double y = 1 + (Math.sin(angle)) * (Math.cos(angle));
			double z = Math.sin(angle);
			location.add(x, y, z);
			Methods.playColoredParticle(player, location, 1, 0.05, 0.05, 0.05, 150, 0, 0);
			location.subtract(x, y, z);
		}
		for (int i = 0; i < 1; i++) {
			currPoint += 360/180;
			if (currPoint == 180 || currPoint == 360) {
				player.getWorld().spawnParticle(Particle.BLOCK_CRACK, location.add(0, 1, 0), 20, 0.1, 0.1, 0.1, 0, block);
			}
			if (currPoint == 360) {
				currPoint = 0;
			}
			double angle = currPoint * Math.PI / 180;
			double x = Math.cos(angle) * Math.sin(angle) * 2;
			double y = 1 + (Math.sin(angle)) * (Math.cos(angle));
			double z = Math.sin(angle);
			location.add(-x, y, -z);
			Methods.playColoredParticle(player, location, 1, 0.05, 0.05, 0.05, 150, 0, 0);
			location.subtract(-x, y, -z);
		}
	}
}
