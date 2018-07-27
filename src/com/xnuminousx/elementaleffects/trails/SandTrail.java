package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.util.ParticleEffect;
import com.projectkorra.projectkorra.util.ParticleEffect.BlockData;
import com.xnuminousx.elementaleffects.Main;

public class SandTrail {
	
	float speed = Main.getInstance().getConfig().getInt("Trails.Sand.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Sand.Particles.Amount");
	Main plugin = Main.getInstance();
	
	public SandTrail(Player player) {
		progress(player);
	}
	
	public void progress(Player p) {
		ParticleEffect.BLOCK_DUST.display(new BlockData(Material.SAND, (byte) 0), 0.5F, 0.5F, 0.5F, speed, amount, p.getLocation().add(0, 1, 0), 257D);
	}

}
