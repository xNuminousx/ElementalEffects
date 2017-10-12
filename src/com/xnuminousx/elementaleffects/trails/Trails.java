package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.projectkorra.projectkorra.ability.AirAbility;
import com.projectkorra.projectkorra.ability.EarthAbility;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class Trails implements Listener {
	
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
		
		boolean vanishInWater = Main.getInstance().getConfig().getBoolean("Trails.Air.DisappearInWater");
		
		if (vanishInWater) {
			Material getBlock = p.getLocation().add(0, 1, 0).getBlock().getType();
			
			if (getBlock.equals(Material.STATIONARY_WATER)) {
				return;
			} else {
				AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
				return;
			}
		} else {
			AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
		}
	}
	
	public static void chiTrail(Player p) {
		float speed = Main.getInstance().getConfig().getInt("Trails.Chi.Particles.Speed");
		int amount = Main.getInstance().getConfig().getInt("Trails.Chi.Particles.Amount");
		
		ParticleEffect.CRIT.display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
	}
	
	public static void avatarTrail(Player p) {
		float speed = Main.getInstance().getConfig().getInt("Trails.Avatar.Particles.Speed");
		int amount = Main.getInstance().getConfig().getInt("Trails.Avatar.Particles.Amount");
		
		boolean aReqEarthBlock = Main.getInstance().getConfig().getBoolean("Trails.Avatar.Earth.RequireEarthBlock");
		boolean fireVanishInWater = Main.getInstance().getConfig().getBoolean("Trails.Avatar.Fire.DisappearInWater");
		boolean doBoilEffect = Main.getInstance().getConfig().getBoolean("Trails.Avatar.Fire.BoilEffect");
		boolean airVanishInWater = Main.getInstance().getConfig().getBoolean("Trails.Avatar.Air.DisappearInWater");
		
		//Fire
		Material fireGetBlock = p.getLocation().add(0, 1, 0).getBlock().getType();
		
		if (fireVanishInWater) {
			if (fireGetBlock.equals(Material.STATIONARY_WATER)) {
				if (doBoilEffect) {
					ParticleEffect.BUBBLE.display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
				}
			} else {
				ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
			}
		} else {
			if (doBoilEffect) {
				if (fireGetBlock.equals(Material.STATIONARY_WATER)) {
					ParticleEffect.BUBBLE.display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
				}
			}
			ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
		}
		
		//Water
		ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
		
		//Air
		if (airVanishInWater) {
			Material airGetBlock = p.getLocation().add(0, 1, 0).getBlock().getType();
			
			if (airGetBlock.equals(Material.STATIONARY_WATER)) {
			} else {
				AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
			}
		} else {
			AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
		}
		
		//Earth
		byte blockByte = 0;
		Block earthGetBlock;
		Material getMat;
		earthGetBlock = p.getLocation().add(0, -1, 0).getBlock();
		getMat = earthGetBlock.getType();
		
		if (aReqEarthBlock) {
			if (!EarthAbility.isEarthbendable(p, earthGetBlock)) {
				getMat = null;
			} else {
				ParticleEffect.BLOCK_CRACK.display((ParticleEffect.ParticleData) 
						new ParticleEffect.BlockData(getMat, blockByte), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount, p.getLocation(), 500);
				return;
			}
		} else {
			getMat = Material.GRASS;
			ParticleEffect.BLOCK_CRACK.display((ParticleEffect.ParticleData) 
					new ParticleEffect.BlockData(getMat, blockByte), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount, p.getLocation(), 500);
		}
	}

}
