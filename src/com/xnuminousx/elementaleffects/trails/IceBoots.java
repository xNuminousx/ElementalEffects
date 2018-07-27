package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.projectkorra.projectkorra.util.ParticleEffect.BlockData;
import com.xnuminousx.elementaleffects.Main;

public class IceBoots {
	
	int amount = Main.getInstance().getConfig().getInt("Trails.Ice.Boots.Amount");
	float speed = Main.getInstance().getConfig().getInt("Trails.Ice.Boots.Speed");
	Main plugin = Main.getInstance();
	
	public IceBoots(Player player) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (plugin.ice.isEmpty()) {
					this.cancel();
				}
				progress(player);
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public void progress(Player p) {
		ParticleEffect.BLOCK_CRACK.display(new BlockData(Material.ICE, (byte) 0), 0.5F, 0.5F, 0.5F, speed, amount, GeneralMethods.getRightSide(p.getLocation(), -.55).add(0, 0, 0), 257D);
		ParticleEffect.BLOCK_CRACK.display(new BlockData(Material.ICE, (byte) 0), 0.5F, 0.5F, 0.5F, speed, amount, GeneralMethods.getLeftSide(p.getLocation(), -.55).add(0, 0, 0), 257D);
	}

}
