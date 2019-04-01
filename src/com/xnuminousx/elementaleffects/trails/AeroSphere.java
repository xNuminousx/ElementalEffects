package com.xnuminousx.elementaleffects.trails;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class AeroSphere {
	
	double yaw;
	public Random random = new Random("Numin goes poopy".hashCode());
	Main plugin = Main.getInstance();
	
	public AeroSphere(Player player) {
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (!Methods.hasPermission(player, "trails", getName()) || !plugin.trails.containsKey(player) || !plugin.trails.get(player).getType().equals(Trails.AEROSPHERE)) {
					this.cancel();
				}
				if (Manager.requireElement()) {
					if (bPlayer.hasElement(Element.AIR)) {
						progress(player);
					}
				} else {
					progress(player);
				}
				
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public static String getName() {
		return Methods.normalizeString(Trails.AEROSPHERE.toString());
	}
	
	public void progress(Player p) {
		double size = Main.getInstance().getConfig().getDouble("Trails.AeroSphere.Size");
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
	        	ParticleEffect.SPELL_MOB.display(tempLoc, 2, 255, 255, 255, 0.003);
	        } else {
	        	ParticleEffect.SPELL_MOB_AMBIENT.display(tempLoc, 2, 0.2F, 0.2F, 0.2F, 0);
	        }
	    }
	}

}
