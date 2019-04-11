package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

//Plays with AirTrail
public class Cloud {
	
	Main plugin = Main.getInstance();
	boolean doCloud = Main.getInstance().getConfig().getBoolean("Trails.Air.CloudEffect");
	float speed = Main.getInstance().getConfig().getInt("Trails.Air.Particles.Speed");

	public Cloud(Player player) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (!Methods.hasPermission(player, "trails", AirTrail.getName()) || 
						!plugin.trails.containsKey(player) || 
						!plugin.trails.get(player).getType().equals(Trails.AIR) ||
						!player.isOnline()) {
					this.cancel();
				}
				if (doCloud) {
					BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
					if (Manager.requireElement()) {
						if (bPlayer.hasElement(Element.AIR)) {
							progress(player);
						}
					} else {
						progress(player);
					}
				}
			}
		}.runTaskTimerAsynchronously(Main.getInstance(), 0, 1);
	}
	
	public void progress(Player p) {
		Material getBlock = p.getLocation().add(0, -1, 0).getBlock().getType();
		
		if (getBlock.equals(Material.AIR)) {
			p.getWorld().spawnParticle(Particle.SPELL, p.getLocation(), 10, 0.5, 0, 0.5, speed);
			p.getWorld().spawnParticle(Particle.CLOUD, p.getLocation(), 5, 0.5F, 0.3F, 0.5F, 0);
		}
	}
}
