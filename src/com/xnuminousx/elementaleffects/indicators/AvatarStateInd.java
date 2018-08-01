package com.xnuminousx.elementaleffects.indicators;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.avatar.AvatarState;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class AvatarStateInd {
	
	private Main plugin = Main.getInstance();
	private boolean playSpark;

	public AvatarStateInd(Player player) {
		playSpark = true;
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (plugin.avatarstate.isEmpty()) {
					this.cancel();
				}
				progress(player);
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public void progress(Player p) {
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(p);
		if (CoreAbility.hasAbility(p, AvatarState.class) && bPlayer.isAvatarState()) {
			ParticleEffect.MOB_SPELL_AMBIENT.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 5);
			if (new Random().nextInt(7) == 0) {
				ParticleEffect.FIREWORKS_SPARK.display(p.getLocation().add(0, 1, 0), 1, 1, 1, 0.1F, 1);
				ParticleEffect.MOB_SPELL.display(p.getLocation(), 1, 1, 1, 1, 2);
			}
			if (playSpark) {
				ParticleEffect.FIREWORKS_SPARK.display(p.getLocation().add(0, 1, 0), 0, 0, 0, 0.2F, 10);
				playSpark = false;
			}
		}
	}
}
