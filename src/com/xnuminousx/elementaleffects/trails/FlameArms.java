package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.utils.Methods;

public class FlameArms {
	
	int amount = Main.getInstance().getConfig().getInt("Trails.FlameArms.Particles.Amount");
	float speed = Main.getInstance().getConfig().getInt("Trails.FlameArms.Particles.Speed");
	boolean enablerings = Main.getInstance().getConfig().getBoolean("Trails.FlameArms.Rings.Enabled");
	int anglee;
	int anglee2;
	int point;
	int point2;
	Main plugin = Main.getInstance();
	
	public FlameArms(Player player) {
		new BukkitRunnable() {

			@Override
			public void run() {
				if (plugin.flamearms.isEmpty()) {
					this.cancel();
				}
				
				progress(player);
			}
			
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public void progress(Player p) {
		ParticleEffect.FLAME.display(GeneralMethods.getRightSide(p.getLocation(), .55).add(0, 1.2, 0), amount, 0.15F, 0.15F, 0.15F, speed);
		ParticleEffect.FLAME.display(GeneralMethods.getLeftSide(p.getLocation(), .55).add(0, 1.2, 0), amount, 0.15F, 0.15F, 0.15F, speed);
		
		if (enablerings) {
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

			ParticleEffect.CRIT.display(location.clone().add(v), 1, 0, 0, 0, 0);
			ParticleEffect.CRIT.display(location.clone().add(v1), 1, 0, 0, 0, 0);
			Methods.playColoredParticle(location.clone().add(v), 1, 0, 0, 0, 216, 104, 39);
			Methods.playColoredParticle(location.clone().add(v1), 1, 0, 0, 0, 216, 104, 39);
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

			ParticleEffect.CRIT.display(locationRight.clone().add(vRight), 1, 0, 0, 0, 0);
			ParticleEffect.CRIT.display(locationRight.clone().add(v1Right), 1, 0, 0, 0, 0);
			Methods.playColoredParticle(locationRight.clone().add(vRight), 1, 0, 0, 0, 216, 104, 39);
			Methods.playColoredParticle(locationRight.clone().add(v1Right), 1, 0, 0, 0, 216, 104, 39);
			if (anglee2 == 360) {
				anglee2 = 0;
			}
		}
		
		if (!p.isOnGround()) {
			ParticleEffect.FLAME.display(GeneralMethods.getRightSide(p.getLocation(), .55).add(0, 1.2, 0), 4, 0F, 0F, 0F, 0.1F);
			ParticleEffect.FLAME.display(GeneralMethods.getLeftSide(p.getLocation(), .55).add(0, 1.2, 0), 4, 0F, 0F, 0F, 0.1F);
		}
	}

}
