package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.GeneralMethods;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

//Plays with the FireTrail
public class FlameArms {
	
	boolean enabled = Main.getInstance().getConfig().getBoolean("Trails.Fire.FlameArms.Enabled");
	int amount = Main.getInstance().getConfig().getInt("Trails.Fire.FlameArms.Particles.Amount");
	float speed = Main.getInstance().getConfig().getInt("Trails.Fire.FlameArms.Particles.Speed");
	boolean vanishInWater = Main.getInstance().getConfig().getBoolean("Trails.Fire.DisappearInWater");
	boolean boilEffect = Main.getInstance().getConfig().getBoolean("Trails.Fire.BoilEffect");
	int anglee;
	int anglee2;
	int point;
	int point2;
	Main plugin = Main.getInstance();
	
	public FlameArms(Player player) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (!Methods.hasPermission(player, "trails", FireTrail.getName()) || 
						!plugin.trails.containsKey(player) || 
						!plugin.trails.get(player).getType().equals(Trails.FIRE) ||
						!player.isOnline()) {
					this.cancel();
				}
				BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
				if (Manager.requireElement()) {
					if (bPlayer.hasElement(Element.FIRE)) {
						progress(player);
					}
				} else {
					progress(player);
				}
			}
		}.runTaskTimerAsynchronously(Main.getInstance(), 0, 1);
	}
	
	public void progress(Player p) {
		Material getBlock = p.getLocation().add(0, 1, 0).getBlock().getType();
		if (vanishInWater) {
			if (getBlock.equals(Material.WATER)) {
				if (boilEffect) {
					p.getWorld().spawnParticle(Particle.WATER_BUBBLE, p.getLocation().add(0, 1, 0), 2, 0.5F, 1, 0.5F, 0.5);
				}
			} else {
				animate(p);
			}
		} else {
			animate(p);
		}
	}
	
	public void animate(Player p) {
		p.getWorld().spawnParticle(Particle.FLAME, GeneralMethods.getRightSide(p.getLocation(), .55).add(0, 1.2, 0), amount, 0.15F, 0.15F, 0.15F, speed);
		p.getWorld().spawnParticle(Particle.FLAME, GeneralMethods.getLeftSide(p.getLocation(), .55).add(0, 1.2, 0), amount, 0.15F, 0.15F, 0.15F, speed);
		
		anglee+=10;
		Location location = GeneralMethods.getLeftSide(p.getLocation(), .55).add(0, 1.2, 0);
		double angle = (anglee * Math.PI / 180);
		double xRotation = 3.141592653589793D / 3 * 2.1;
		Vector v = new Vector(Math.cos(angle + point), Math.sin(angle), 0.0D).multiply(1);
		Vector v1 = v.clone();
		Methods.rotateAroundAxisX(v, xRotation);
		Methods.rotateAroundAxisY(v, -((location.getYaw() * Math.PI / 180) - 1.575));
		Methods.rotateAroundAxisX(v1, -xRotation);
		Methods.rotateAroundAxisY(v1, -((location.getYaw() * Math.PI / 180) - 1.575));
		p.getWorld().spawnParticle(Particle.CRIT, location.clone().add(v), 1, 0, 0, 0, 0);
		p.getWorld().spawnParticle(Particle.CRIT, location.clone().add(v1), 1, 0, 0, 0, 0);
		Methods.playColoredParticle(p, location.clone().add(v), 1, 0, 0, 0, 216, 104, 39);
		Methods.playColoredParticle(p, location.clone().add(v1), 1, 0, 0, 0, 216, 104, 39);
		if (anglee == 360) {
			anglee = 0;
		}
		
		anglee2+=10;
		Location locationRight = GeneralMethods.getRightSide(p.getLocation(), .55).add(0, 1.2, 0);
		double angleRight = (anglee * Math.PI / 180);
		double xRotationRight = 3.141592653589793D / 3 * 2.1;
		Vector vRight = new Vector(Math.cos(angleRight + point2), Math.sin(angleRight), 0.0D).multiply(1);
		Vector v1Right = vRight.clone();
		Methods.rotateAroundAxisX(vRight, xRotationRight);
		Methods.rotateAroundAxisY(vRight, -((locationRight.getYaw() * Math.PI / 180) - 1.575));
		Methods.rotateAroundAxisX(v1Right, -xRotationRight);
		Methods.rotateAroundAxisY(v1Right, -((locationRight.getYaw() * Math.PI / 180) - 1.575));

		p.getWorld().spawnParticle(Particle.CRIT, location.clone().add(vRight), 1, 0, 0, 0, 0);
		p.getWorld().spawnParticle(Particle.CRIT, location.clone().add(v1Right), 1, 0, 0, 0, 0);
		Methods.playColoredParticle(p, locationRight.clone().add(vRight), 1, 0, 0, 0, 216, 104, 39);
		Methods.playColoredParticle(p, locationRight.clone().add(v1Right), 1, 0, 0, 0, 216, 104, 39);
		if (anglee2 == 360) {
			anglee2 = 0;
		}
		
		if (!p.isOnGround()) {
			p.getWorld().spawnParticle(Particle.FLAME, GeneralMethods.getRightSide(p.getLocation(), .55).add(0, 1.2, 0), 4, 0F, 0F, 0F, 0.1F);
			p.getWorld().spawnParticle(Particle.FLAME, GeneralMethods.getLeftSide(p.getLocation(), .55).add(0, 1.2, 0), 4, 0F, 0F, 0F, 0.1F);
		}
	}

}
