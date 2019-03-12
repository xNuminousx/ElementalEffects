package com.xnuminousx.elementaleffects.listeners;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Indicator;
import com.xnuminousx.elementaleffects.utils.Methods;

public class EntityDamage implements Listener {
	
	HashMap<Player, Indicator> inds = Main.plugin.inds;
	double maxHearts = Manager.maxDamage();

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Entity target = event.getEntity();
			Player player = (Player)event.getDamager();
			double amount = event.getDamage();
			if (inds.containsKey(player)) {
				if (event.getEntity() instanceof LivingEntity) {
					bloodEffect(target, amount);
					return;
				}
			} else {
				return;
			}
		}
	}
	
	public void bloodEffect(Entity target, double amount) {
		if (amount > maxHearts) {
			ItemStack item = new ItemStack(Material.NETHER_WART_BLOCK);
			target.getWorld().spawnParticle(Particle.ITEM_CRACK, target.getLocation().add(0, 1, 0), 20, 0.3, 0.5, 0.3, 0, item);
		} else {
			Methods.playColoredParticle(target.getLocation().add(0, 1, 0), 10, 0.3, 0.3, 0.3, 255, 0, 0);
		}
	}
}
