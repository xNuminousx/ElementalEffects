package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.utils.Methods;

public class SandCloak {

	PlayerMoveEvent event;
	double cloakRadius = Main.getInstance().getConfig().getDouble("Trails.Sand.Cloak.Radius");
	boolean reqSand = Main.getInstance().getConfig().getBoolean("Trails.Sand.Cloak.RequireSand");
	Main plugin = Main.getInstance();
	
	public SandCloak(Player player) {
		new BukkitRunnable() {

			@Override
			public void run() {
				Block getBlock = player.getLocation().add(0, -1.5, 0).getBlock();
				if (plugin.sand.isEmpty()) {
					cancel();
				} else {
					if (reqSand) {
						if (getBlock.getType().equals(Material.SAND)) {
							progress(player);
						}
					} else {
						progress(player);
					}
				}
			}
			
		}.runTaskTimer(Main.getInstance(), 0, 2);
	}
	
	public void progress(Player p) {
		if (!p.isSprinting()) {
			for (int i = -180; i < 180; i += 10) {
				Location loc = p.getLocation().clone().add(0, cloakRadius, 0);
				Location locUp = p.getLocation().clone().add(0, cloakRadius, 0);
				Location locDown = p.getLocation().clone().add(0, cloakRadius, 0);
				double x = Math.cos(Math.toRadians(i)) * cloakRadius;
				double z = Math.sin(Math.toRadians(i)) * cloakRadius;
				
				Methods.playColoredParticle(locUp.add(x, 0.5, z), 1, 0, 0, 0, 251, 255, 186);
				Methods.playColoredParticle(loc.add(x, 0.1, z), 1, 0, 0, 0, 251, 255, 186);
				Methods.playColoredParticle(locDown.add(x, -0.3, z), 1, 0, 0, 0, 251, 255, 186);
			}
		}
	}
}
