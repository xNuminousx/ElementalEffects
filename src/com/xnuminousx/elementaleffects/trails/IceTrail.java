package com.xnuminousx.elementaleffects.trails;

import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.utils.Methods;

public class IceTrail {
	
	float speed = Main.getInstance().getConfig().getInt("Trails.Ice.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Ice.Particles.Amount");
	
	public IceTrail(Player player) {
		progress(player);
	}
	
	public void progress(Player p) {
		Methods.playColoredParticle(GeneralMethods.getRightSide(p.getLocation(), -.55).add(0, 0, 0), 5, 0.3, 0.3, 0.3, 183, 237, 255);
		Methods.playColoredParticle(GeneralMethods.getLeftSide(p.getLocation(), -.55).add(0, 0, 0), 5, 0.3, 0.3, 0.3, 183, 237, 255);
		ParticleEffect.CLOUD.display(GeneralMethods.getRightSide(p.getLocation(), -.55).add(0, 0, 0), amount, 0.2F, 0.2F, 0.2F, speed);
		ParticleEffect.CLOUD.display(GeneralMethods.getLeftSide(p.getLocation(), -.55).add(0, 0, 0), amount, 0.2F, 0.2F, 0.2F, speed);
	}

}
