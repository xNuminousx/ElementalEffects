package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.ability.AirAbility;
import com.projectkorra.projectkorra.ability.EarthAbility;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class Move implements Listener {
	
	public static void stillEffect(Player p) {
		new BukkitRunnable() {

			private int currPoint;

			@Override
			public void run() {
				Location location = p.getLocation();
				for (int i = 0; i < 6; i++) {
					currPoint += 360;
					if (currPoint > 360) {
						currPoint = 0;
					}
					double radians = Math.toRadians(currPoint);
					double x = Math.cos(radians);
					double z = Math.sin(radians);
					location.add(x, 0, z);
					ParticleEffect.FLAME.display(location, 0, 0, 0, 0, 1);
					location.subtract(x, 0, z);
				}
			}
			
		}.runTaskTimer(Main.plugin, 0, 3);
	}
	
	public static void earthTrail(Player p) {
		float speed = Main.getInstance().getConfig().getInt("Trails.Earth.Particles.Speed");
		int amount = Main.getInstance().getConfig().getInt("Trails.Earth.Particles.Amount");
		
		byte blockByte = 0;
		Block getBlock;
		Material getMat;
		getBlock = p.getLocation().add(0, -1, 0).getBlock();
		getMat = getBlock.getType();
		boolean reqEarthBlock = Main.getInstance().getConfig().getBoolean("Trails.Earth.RequireEarthBlock");
		
		if (reqEarthBlock) {
			if (!EarthAbility.isEarthbendable(p, getBlock)) {
				getMat = null;
			} else {
				ParticleEffect.BLOCK_CRACK.display((ParticleEffect.ParticleData) 
						new ParticleEffect.BlockData(getMat, blockByte), (float) 0.5, (float) 1, (float) 0.5, (float) speed, amount, p.getLocation(), 500);
				return;
			}
		} else {
			getMat = Material.GRASS;
			ParticleEffect.BLOCK_CRACK.display((ParticleEffect.ParticleData) 
					new ParticleEffect.BlockData(getMat, blockByte), (float) 0.5, (float) 1, (float) 0.5, (float) speed, amount, p.getLocation(), 500);
		}
	}
	public static void fireTrail(Player p) {
		float speed = Main.getInstance().getConfig().getInt("Trails.Fire.Particles.Speed");
		int amount = Main.getInstance().getConfig().getInt("Trails.Fire.Particles.Amount");
		
		boolean vanishInWater = Main.getInstance().getConfig().getBoolean("Trails.Fire.DisappearInWater");
		boolean boilEffect = Main.getInstance().getConfig().getBoolean("Trails.Fire.BoilEffect");
		Material getBlock = p.getLocation().add(0, 1, 0).getBlock().getType();
		
		if (vanishInWater) {
			if (getBlock.equals(Material.STATIONARY_WATER)) {
				if (boilEffect) {
					ParticleEffect.BUBBLE.display(p.getLocation().add(0, 1, 0), 0.5F, 1, 0.5F, 0.5F, 2);
				}
				return;
			} else {
				ParticleEffect.SMOKE.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
				ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
				return;
			}
		} else {
			if (boilEffect) {
				if (getBlock.equals(Material.STATIONARY_WATER)) {
					ParticleEffect.BUBBLE.display(p.getLocation().add(0, 1, 0), 0.5F, 1, 0.5F, 0.5F, 2);
					return;
				}
			}
			ParticleEffect.SMOKE.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
			ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
		}
	}
	
	public static void waterTrail(Player p) {
		float speed = Main.getInstance().getConfig().getInt("Trails.Water.Particles.Speed");
		int amount = Main.getInstance().getConfig().getInt("Trails.Water.Particles.Amount");
		
		ParticleEffect.SPLASH.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0.5F, 2);
		ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 1, 0), (float) 0.3, (float) 0.3, (float) 0.3, (float) speed, amount);
	}
	
	public static void airTrail(Player p) {
		float speed = Main.getInstance().getConfig().getInt("Trails.Air.Particles.Speed");
		int amount = Main.getInstance().getConfig().getInt("Trails.Air.Particles.Amount");
		boolean doCloud = Main.getInstance().getConfig().getBoolean("Trails.Air.CloudEffect");
		boolean vanishInWater = Main.getInstance().getConfig().getBoolean("Trails.Air.DisappearInWater");
		Material getBlock = p.getLocation().add(0, -1, 0).getBlock().getType();
		
		if (vanishInWater) {
			
			if (getBlock.equals(Material.STATIONARY_WATER)) {
				return;
			} else {
				if (doCloud) {
					if (getBlock.equals(Material.AIR)) {
						AirAbility.getAirbendingParticles().display(p.getLocation(), (float) 0.5, 0, (float) 0.5, (float) speed, 5);
						return;
					} else {
						AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
						return;
					}
				} else {
					AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
					return;
				}
			}
		} else {
			if (doCloud) {
				if (getBlock.equals(Material.AIR)) {
					AirAbility.getAirbendingParticles().display(p.getLocation(), (float) 0.5, 0, (float) 0.5, (float) speed, 5);
					return;
				} else {
					AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
					return;
				}
			} else {
				AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
				return;
			}
		}
	}
	
	public static void chiTrail(Player p) {
		int amount = Main.getInstance().getConfig().getInt("Trails.Chi.Particles.Amount");
		float speed = Main.getInstance().getConfig().getInt("Trails.Chi.Particles.Speed");
		boolean doMagic = Main.getInstance().getConfig().getBoolean("Trails.Chi.Particles.DoMagicCrit"); 
		
		ParticleEffect.CRIT.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, (float) speed, amount);
		if (doMagic) {
			ParticleEffect.MAGIC_CRIT.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, (float) speed, amount);
		}
		
	}
	
	public static void avatarTrail(Player p) {
		//Fire
		fireTrail(p);
		//Water
		waterTrail(p);
		//Air
		airTrail(p);
		//Earth
		earthTrail(p);
	}

}
