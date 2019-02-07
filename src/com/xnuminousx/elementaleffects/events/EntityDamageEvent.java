package com.xnuminousx.elementaleffects.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;

public class EntityDamageEvent implements Listener {
	
	Main plugin = Main.getInstance();
	double maxHearts = Manager.maxDamage();

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
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

	}
	
	public void bloodEffect(Entity target, double amount) {
		
		if (amount > maxHearts) {
			Methods.playColoredParticle(target.getLocation().add(0, 1, 0), 20, 0.5, 0.5, 0.5, 120, 0, 0);
		} else {
			Methods.playColoredParticle(target.getLocation().add(0, 1, 0), 10, 0.3, 0.3, 0.3, 255, 0, 0);
		}
	}
}
