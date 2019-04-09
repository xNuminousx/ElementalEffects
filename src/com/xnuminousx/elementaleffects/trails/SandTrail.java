package com.xnuminousx.elementaleffects.trails;

import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.Element.SubElement;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class SandTrail {
	
	float speed = Main.getInstance().getConfig().getInt("Trails.Sand.Particles.Speed");
	int amount = Main.getInstance().getConfig().getInt("Trails.Sand.Particles.Amount");
	Main plugin = Main.getInstance();
	
	public SandTrail(Player player) {
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		if (Manager.requireElement()) {
			if (bPlayer.hasElement(Element.EARTH) && bPlayer.hasSubElement(SubElement.SAND)) {
				progress(player);
			}
		} else {
			progress(player);
		}
	}
	
	public static String getName() {
		return Methods.normalizeString(Trails.SANDCLOAK.toString());
	}
	
	public void progress(Player p) {
		Methods.playColoredParticle(p, p.getLocation(), 5, 1, 1, 1, 251, 255, 186);
	}
}
