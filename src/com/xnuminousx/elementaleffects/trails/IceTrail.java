package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;

public class IceTrail {
	
	float speed = Main.getInstance().getConfig().getInt("Trails.Ice.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Ice.Particles.Amount");
	
	public IceTrail(Player player) {
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		if (Manager.requireElement()) {
			if (bPlayer.hasElement(Element.WATER)) {
				progress(player);
			}
		} else {
			progress(player);
		}
	}
	
	public void progress(Player p) {
		BlockData block = Material.PACKED_ICE.createBlockData();
		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation().add(0, 1, 0), amount, 0.5, 0.5, 0.5, 0, block);
		ParticleEffect.CLOUD.display(GeneralMethods.getRightSide(p.getLocation(), -.55).add(0, 0, 0), amount, 0.2F, 0.2F, 0.2F, speed);
		ParticleEffect.CLOUD.display(GeneralMethods.getLeftSide(p.getLocation(), -.55).add(0, 0, 0), amount, 0.2F, 0.2F, 0.2F, speed);
	}

}
