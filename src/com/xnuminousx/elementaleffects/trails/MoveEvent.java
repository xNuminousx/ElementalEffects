package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.projectkorra.projectkorra.ability.AirAbility;
import com.projectkorra.projectkorra.ability.EarthAbility;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class MoveEvent implements Listener {

	Main plugin = Main.getInstance();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = (Player)e.getPlayer();
		
		//Earth Trail
		if (plugin.earth.contains(p)) {
			byte blockByte = 0;
			Block getBlock;
			Material getMat;
			getBlock = p.getLocation().add(0, -1, 0).getBlock();
			getMat = getBlock.getType();
			
			if (!EarthAbility.isEarthbendable(p, getBlock)) {
				getMat = null;
			} else {
				ParticleEffect.BLOCK_CRACK.display((ParticleEffect.ParticleData) 
						new ParticleEffect.BlockData(getMat, blockByte), 0.5F, 0.5F, 0.5F, 0, 5, p.getLocation(), 500);
				return;
			}
		
		//Fire Trail
		} else if (plugin.fire.contains(p)) {
			Material getBlock = p.getLocation().add(0, 1, 0).getBlock().getType();
			
			if (getBlock.equals(Material.STATIONARY_WATER)) {
				ParticleEffect.BUBBLE.display(p.getLocation().add(0, 1, 0), 0.5F, 1, 0.5F, 0.5F, 2);
				return;
			} else {
				ParticleEffect.SMOKE.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
				ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), 1, 1, 1, 0, 2);
				return;
			}
		
		//Water Trail
		} else if (plugin.water.contains(p)) {
			ParticleEffect.SPLASH.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0.5F, 2);
			ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 1, 0), 0.2F, 0.2F, 0.2F, 0, 2);
		
		//Air Trail	
		} else if (plugin.air.contains(p)) {
			Material getBlock = p.getLocation().add(0, 1, 0).getBlock().getType();
			
			if (getBlock.equals(Material.STATIONARY_WATER)) {
				return;
			} else {
				AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
				return;
			}
			
		//Chi Trail	
		} else if (plugin.chi.contains(p)) {
			ParticleEffect.CRIT.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
		
		//Avatar Trail	
		} else if (plugin.avatar.contains(p)) {
			
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
	
}
