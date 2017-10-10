package com.xnuminousx.elementaleffects.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class MoveEvent implements Listener {

	Main plugin = Main.getInstance();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = (Player)e.getPlayer();
		
		if (plugin.earth.contains(p)) {
			ParticleEffect.SMOKE.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
			return;
		} else if (plugin.fire.contains(p)) {
			ParticleEffect.SMOKE.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
			ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), 1, 1, 1, 0, 2);
			return;
		} else if (plugin.water.contains(p)) {
			ParticleEffect.SPLASH.display(p.getLocation().add(0, 1, 0), 1, 1, 1, 0, 10);
			ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 1);
		} else if (plugin.air.contains(p)) {
			ParticleEffect.CLOUD.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
		}
	}
}
