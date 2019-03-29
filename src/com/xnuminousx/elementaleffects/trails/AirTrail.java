package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.ability.AirAbility;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class AirTrail {
	
	float speed = Main.getInstance().getConfig().getInt("Trails.Air.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Air.Particles.Amount");
	boolean vanishInWater = Main.getInstance().getConfig().getBoolean("Trails.Air.DisappearInWater");
	
	public AirTrail(Player player) {
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		if (Manager.requireElement()) {
			if (bPlayer.hasElement(Element.AIR)) {
				progress(player);
			}
		} else {
			progress(player);
		}
	}
	
	public static String getName() {
		return Methods.normalizeString(Trails.AIR.toString());
	}
	
	public void progress(Player p) {
		Material getBlock = p.getLocation().add(0, -1, 0).getBlock().getType();
		
		if (vanishInWater) {
			
			if (getBlock.equals(Material.WATER)) {
				return;
			} else {
				AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), amount, 0.5, 0.5, 0.5, speed);
				return;
			}
		} else {
			AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), amount, 0.5, 0.5, 0.5, speed);
			return;
		}
		
	}

}
