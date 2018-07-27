package com.xnuminousx.elementaleffects.trails;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class AeroSphere {
	
	double yaw;
	public Random random = new Random("Numin goes poopy".hashCode());
	Main plugin = Main.getInstance();
	
	public AeroSphere(Player player) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (plugin.aero.isEmpty()) {
					this.cancel();
				}
				progress(player);
				
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public void progress(Player p) {
		double size = Main.getInstance().getConfig().getDouble("Trails.Air2.Sphere.Size");
		Location baseLoc = p.getLocation().clone().add(0, size / 2, 0);
	    Location fakeLoc = baseLoc.clone();
	    fakeLoc.setPitch(0.0F);
	    fakeLoc.setYaw((float)(yaw += 40.0D));
	    Vector direction = fakeLoc.getDirection();
	    for (double j = -180.0D; j <= 180.0D; j += 45.0D) {
	        Location tempLoc = fakeLoc.clone();
	        Vector newDir = direction.clone().multiply(size * Math.cos(Math.toRadians(j)));
	        tempLoc.add(newDir);
	        tempLoc.setY(tempLoc.getY() + size * Math.sin(Math.toRadians(j)));
	        if (random.nextInt(30) == 0) {
	        	ParticleEffect.MOB_SPELL.display(255.0F, 255.0F, 255.0F, 0.003F, 0, tempLoc, 257.0D);
	        } else {
	        	ParticleEffect.MOB_SPELL_AMBIENT.display(tempLoc, 0.2F, 0.2F, 0.2F, 0.0F, 2);
	        }
	    }
	}

}
