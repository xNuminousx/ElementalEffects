package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class Combust {
	
	Main plugin = Main.getInstance();
	
	public Combust(Player player) {
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		new BukkitRunnable() {
			@Override
			public void run() {
				if (!Methods.hasPermission(player, getName()) || !plugin.trails.containsKey(player) || !plugin.trails.get(player).getType().equals(Trails.COMBUST)) {
					this.cancel();
				}
				if (Manager.requireElement()) {
					if (bPlayer.hasElement(Element.FIRE)) {
						progress(player);
					}
				} else {
					progress(player);
				}
			}
		}.runTaskTimer(plugin, 0, 1);
	}
	
	public void progress(Player player) {
		player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 1, 0, 0, 0, 0.03);
	}
	
	public static String getName() {
		return Methods.normalizeString(Trails.COMBUST.toString());
	}
}
