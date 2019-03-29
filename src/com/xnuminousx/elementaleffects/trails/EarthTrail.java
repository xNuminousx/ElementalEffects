package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.ability.EarthAbility;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class EarthTrail {
	
	float speed = Main.getInstance().getConfig().getInt("Trails.Earth.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Earth.Particles.Amount");
	boolean reqEarthBlock = Main.getInstance().getConfig().getBoolean("Trails.Earth.RequireEarthBlock");
	
	Material mat;
	
	public EarthTrail(Player player) {
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		if (Manager.requireElement()) {
			if (bPlayer.hasElement(Element.EARTH)) {
				progress(player);
			}
		} else {
			progress(player);
		}
	}
	
	public static String getName() {
		return Methods.normalizeString(Trails.EARTH.toString());
	}
	
	public void progress(Player p) {
		
		Block getBlock = p.getLocation().add(0, -1, 0).getBlock();
		mat = getBlock.getType();
		
		if (reqEarthBlock) {
			if (!EarthAbility.isEarthbendable(p, getBlock)) {
				return;
			} else {
				playParticle(p, mat);
			}
		} else {
			playParticle(p, Material.GRASS);
		}
	}
	public void playParticle(Player player, Material mat) {
		BlockData block = mat.createBlockData();
		player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation().add(0, 1, 0), 5, 0.5, 0.5, 0.5, 0, block);
	}

}
