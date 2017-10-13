package com.xnuminousx.elementaleffects.events;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;

public class EntityDamageEvent implements Listener {
	
	Main plugin = Main.getInstance();
	double maxHearts = Manager.maxDamage();

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		Entity target = event.getEntity();
		Player p = (Player)event.getDamager();
		double amount = event.getDamage();
		
		if (plugin.hit.contains(p)) {
			if (event.getEntity() instanceof LivingEntity) {
				bloodEffect(target, amount);
				return;
			}
		} else {
			return;
		}

	}
	
	public void bloodEffect(Entity target, double amount) {
		byte blockByte = 1;
		Material blockType = Material.REDSTONE_BLOCK;
		
		if (amount > maxHearts) {
			ParticleEffect.BLOCK_CRACK.display((ParticleEffect.ParticleData) 
					new ParticleEffect.BlockData(blockType, blockByte), 0, 0, 0, 0, 30, target.getLocation().add(0, 1, 0), 500);
		} else {
			ParticleEffect.RED_DUST.display(target.getLocation().add(0, 1, 0), 0.3F, 0.5F, 0.3F, 0, 20);
		}
	}
}
