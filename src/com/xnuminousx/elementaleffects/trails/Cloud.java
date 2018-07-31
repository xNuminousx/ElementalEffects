package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class Cloud {
	
	Main plugin = Main.getInstance();
	boolean doCloud = Main.getInstance().getConfig().getBoolean("Trails.Air.CloudEffect");
	float speed = Main.getInstance().getConfig().getInt("Trails.Air.Particles.Speed");

	public Cloud(Player player) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (plugin.air.isEmpty()) {
					this.cancel();
				}
				if (doCloud) {
					progress(player);
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public void progress(Player p) {
		Material getBlock = p.getLocation().add(0, -1, 0).getBlock().getType();
		
		if (getBlock.equals(Material.AIR)) {
			ParticleEffect.SPELL.display(p.getLocation(), (float) 0.5, 0, (float) 0.5, (float) speed, 10);
			ParticleEffect.CLOUD.display(p.getLocation(), 0.5F, 0.3F, 0.5F, 0, 5);
		}
	}
}
