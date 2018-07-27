package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.ability.EarthAbility;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class EarthTrail {
	
	float speed = Main.getInstance().getConfig().getInt("Trails.Earth.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Earth.Particles.Amount");
	boolean reqEarthBlock = Main.getInstance().getConfig().getBoolean("Trails.Earth.RequireEarthBlock");
	
	byte blockByte = 0;
	Material getMat;
	
	public EarthTrail(Player player) {
		progress(player);
	}
	
	public void progress(Player p) {
		
		Block getBlock = p.getLocation().add(0, -1, 0).getBlock();
		getMat = getBlock.getType();
		
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

}
