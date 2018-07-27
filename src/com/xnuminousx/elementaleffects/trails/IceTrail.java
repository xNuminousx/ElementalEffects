package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.projectkorra.projectkorra.util.ParticleEffect.BlockData;
import com.xnuminousx.elementaleffects.Main;

public class IceTrail {
	
	float speed = Main.getInstance().getConfig().getInt("Trails.Ice.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Ice.Particles.Amount");
	
	public IceTrail(Player player) {
		progress(player);
	}
	
	public void progress(Player p) {
		ParticleEffect.BLOCK_CRACK.display(new BlockData(Material.PACKED_ICE, (byte) 0), 0.5F, 0.5F, 0.5F, speed, amount, p.getLocation().add(0, 1, 0), 257D);
		ParticleEffect.CLOUD.display(GeneralMethods.getRightSide(p.getLocation(), -.55).add(0, 0, 0), 0.2F, 0.2F, 0.2F, speed, amount);
		ParticleEffect.CLOUD.display(GeneralMethods.getLeftSide(p.getLocation(), -.55).add(0, 0, 0), 0.2F, 0.2F, 0.2F, speed, amount);
	}

}
