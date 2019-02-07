package com.xnuminousx.elementaleffects.trails;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.ability.EarthAbility;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.utils.Methods;

public class EarthTrail {
	
	float speed = Main.getInstance().getConfig().getInt("Trails.Earth.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Earth.Particles.Amount");
	boolean reqEarthBlock = Main.getInstance().getConfig().getBoolean("Trails.Earth.RequireEarthBlock");
	
	public EarthTrail(Player player) {
		progress(player);
	}
	
	public void progress(Player p) {
		
		Block getBlock = p.getLocation().add(0, -1, 0).getBlock();
		
		if (reqEarthBlock) {
			if (!EarthAbility.isEarthbendable(p, getBlock)) {
				return;
			} else {
				Methods.playColoredParticle(p.getLocation(), 5, 1, 1, 1, 111, 63, 0);
				Methods.playColoredParticle(p.getLocation(), 5, 1, 1, 1, 128, 128, 128);
				return;
			}
		} else {
			Methods.playColoredParticle(p.getLocation(), 5, 1, 1, 1, 111, 63, 0);
			Methods.playColoredParticle(p.getLocation(), 5, 1, 1, 1, 128, 128, 128);
		}
	}

}
