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
						new ParticleEffect.BlockData(getMat, blockByte), 0.5F, 1, 0.5F, 0, 5, p.getLocation(), 500);
				return;
			}
		} else {
			getMat = Material.GRASS;
			ParticleEffect.BLOCK_CRACK.display((ParticleEffect.ParticleData) 
					new ParticleEffect.BlockData(getMat, blockByte), 0.5F, 1, 0.5F, 0, 5, p.getLocation(), 500);
		}
	}
	public static void fireTrail(Player p) {
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
				ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
				ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), 2, 2, 2, 0, 1);
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
			ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
			ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), 2, 2, 2, 0, 1);
		}
	}
	
	public static void waterTrail(Player p) {
		ParticleEffect.SPLASH.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0.5F, 2);
		ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 1, 0), 0.2F, 0.2F, 0.2F, 0, 2);
	}
	
	public static void airTrail(Player p) {
		boolean vanishInWater = Main.getInstance().getConfig().getBoolean("Trails.Air.DisappearInWater");
		
		if (vanishInWater) {
			Material getBlock = p.getLocation().add(0, 1, 0).getBlock().getType();
			
			if (getBlock.equals(Material.STATIONARY_WATER)) {
				return;
			} else {
				AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
				return;
			}
		} else {
			AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
		}
	}
	
	public static void chiTrail(Player p) {
		ParticleEffect.CRIT.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
	}
	
	public static void avatarTrail(Player p) {
		boolean aReqEarthBlock = Main.getInstance().getConfig().getBoolean("Trails.Avatar.Earth.RequireEarthBlock");
		boolean fireVanishInWater = Main.getInstance().getConfig().getBoolean("Trails.Avatar.Fire.DisappearInWater");
		boolean doBoilEffect = Main.getInstance().getConfig().getBoolean("Trails.Avatar.Fire.BoilEffect");
		boolean airVanishInWater = Main.getInstance().getConfig().getBoolean("Trails.Avatar.Air.DisappearInWater");
		
		//Fire
		Material fireGetBlock = p.getLocation().add(0, 1, 0).getBlock().getType();
		
		if (fireVanishInWater) {
			if (fireGetBlock.equals(Material.STATIONARY_WATER)) {
				if (doBoilEffect) {
					ParticleEffect.BUBBLE.display(p.getLocation().add(0, 1, 0), 0.5F, 1, 0.5F, 0.5F, 2);
				}
			} else {
				ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
			}
		} else {
			if (doBoilEffect) {
				if (fireGetBlock.equals(Material.STATIONARY_WATER)) {
					ParticleEffect.BUBBLE.display(p.getLocation().add(0, 1, 0), 0.5F, 1, 0.5F, 0.5F, 2);
				}
			}
			ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
		}
		
		//Water
		ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 1);
		
		//Air
		if (airVanishInWater) {
			Material airGetBlock = p.getLocation().add(0, 1, 0).getBlock().getType();
			
			if (airGetBlock.equals(Material.STATIONARY_WATER)) {
			} else {
				AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
			}
		} else {
			AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
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
						new ParticleEffect.BlockData(getMat, blockByte), 0.5F, 0.5F, 0.5F, 0, 5, p.getLocation(), 500);
				return;
			}
		} else {
			getMat = Material.GRASS;
			ParticleEffect.BLOCK_CRACK.display((ParticleEffect.ParticleData) 
					new ParticleEffect.BlockData(getMat, blockByte), 0.5F, 0.5F, 0.5F, 0, 5, p.getLocation(), 500);
		}
	}

}
