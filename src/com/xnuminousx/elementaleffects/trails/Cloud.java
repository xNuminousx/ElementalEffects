package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class Cloud {
	
	Main plugin = Main.getInstance();
	boolean doCloud = Main.getInstance().getConfig().getBoolean("Trails.Air.CloudEffect");
	float speed = Main.getInstance().getConfig().getInt("Trails.Air.Particles.Speed");

	public Cloud(Player player) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (!plugin.trails.containsKey(player) || !plugin.trails.get(player).getType().equals(Trails.AIR)) {
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
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public void progress(Player p) {
		Material getBlock = p.getLocation().add(0, -1, 0).getBlock().getType();
		
		if (getBlock.equals(Material.AIR)) {
			ParticleEffect.SPELL.display(p.getLocation(), 10, 0.5, 0, 0.5, speed);
			ParticleEffect.CLOUD.display(p.getLocation(), 5, 0.5F, 0.3F, 0.5F, 0);
		}
	}
}
