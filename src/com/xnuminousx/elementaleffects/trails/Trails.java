package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.ability.AirAbility;
import com.projectkorra.projectkorra.ability.EarthAbility;
import com.projectkorra.projectkorra.util.ParticleEffect;

public class Trails {
	
	public static void earthTrail(Player p) {
		byte blockByte = 0;
		Block getBlock;
		Material getMat;
		getBlock = p.getLocation().add(0, -1, 0).getBlock();
		getMat = getBlock.getType();
		
		if (!EarthAbility.isEarthbendable(p, getBlock)) {
			getMat = null;
		} else {
			ParticleEffect.BLOCK_CRACK.display((ParticleEffect.ParticleData) 
					new ParticleEffect.BlockData(getMat, blockByte), 0.5F, 1, 0.5F, 0, 5, p.getLocation(), 500);
			return;
		}
	}
	public static void fireTrail(Player p) {
		Material getBlock = p.getLocation().add(0, 1, 0).getBlock().getType();
		
		if (getBlock.equals(Material.STATIONARY_WATER)) {
			ParticleEffect.BUBBLE.display(p.getLocation().add(0, 1, 0), 0.5F, 1, 0.5F, 0.5F, 2);
			return;
		} else {
			ParticleEffect.SMOKE.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
			ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
			ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), 2, 2, 2, 0, 1);
			return;
		}
	}
	
	public static void waterTrail(Player p) {
		ParticleEffect.SPLASH.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0.5F, 2);
		ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 1, 0), 0.2F, 0.2F, 0.2F, 0, 2);
	}
	
	public static void airTrail(Player p) {
		Material getBlock = p.getLocation().add(0, 1, 0).getBlock().getType();
		
		if (getBlock.equals(Material.STATIONARY_WATER)) {
			return;
		} else {
			AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
			return;
		}
	}
	
	public static void chiTrail(Player p) {
		ParticleEffect.CRIT.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
	}
	
	public static void avatarTrail(Player p) {
		ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 1);
		
		ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 1);
		
		AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 1);
		
		byte blockByte = 0;
		Block getBlock;
		Material getMat;
		getBlock = p.getLocation().add(0, -1, 0).getBlock();
		getMat = getBlock.getType();
		
		if (!EarthAbility.isEarthbendable(p, getBlock)) {
			getMat = null;
		} else {
			ParticleEffect.BLOCK_CRACK.display((ParticleEffect.ParticleData) 
					new ParticleEffect.BlockData(getMat, blockByte), 1, 1, 1, 0, 3, p.getLocation(), 500);
			return;
		}
	}

}
