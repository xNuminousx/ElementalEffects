package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.Element.SubElement;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class LavaTrail {
	
	double t = 0;
	Main plugin = Main.getInstance();
	int amount = Main.getInstance().getConfig().getInt("Trails.Eruption.Particles.Amount");
	
	public LavaTrail(Player player) {
		new BukkitRunnable() {

			@Override
			public void run() {
				if (!Methods.hasPermission(player, getName()) || !plugin.trails.containsKey(player) || !plugin.trails.get(player).getType().equals(Trails.ERUPTION)) {
					this.cancel();
				}
				BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
				if (Manager.requireElement()) {
					if (bPlayer.hasElement(Element.EARTH) && bPlayer.hasSubElement(SubElement.LAVA)) {
						progress(player);
					}
				} else {
					progress(player);
				}
			}
			
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public static String getName() {
		return Methods.normalizeString(Trails.ERUPTION.toString());
	}
	
	public void progress(Player p) {
		t += Math.PI / 16;
		Location loc = p.getLocation();
		for (double phi = 0; phi <= 2 * Math.PI; phi += Math.PI / 5) {
			double x = 0.3 * (4 * Math.PI - t) * Math.cos(t + phi);
			double y = 0.2 * t;
			double z = 0.3 * (4 * Math.PI - t) * Math.sin(t + phi);
			loc.add(x, y, z);
			BlockData block = Material.LAVA.createBlockData();
			p.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, amount, 0, 0, 0, 0, block);
			Methods.playColoredParticle(loc, amount, 0.1, 0.1, 0.1, 244, 113, 66);
			loc.subtract(x, y, z);
		
			if (t >= 4 * Math.PI) {
				ParticleEffect.LAVA.display(p.getLocation().add(0, 3, 0), 20, 0F, 0F, 0F, 0);
				t = 0;
			} else if (t >= 3 * Math.PI) {
				amount = 1;
			}
		}
	}

}
