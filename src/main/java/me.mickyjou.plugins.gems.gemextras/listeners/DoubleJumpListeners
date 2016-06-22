package me.mickyjou.plugins.gems.gemextras;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class DoubleJumpListeners implements Listener {
	HashSet<String> doublejump = new HashSet<String>();

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.getGameMode().equals(GameMode.SURVIVAL)) {
			if (p.getLocation().add(0, -1, 0).getBlock().getType() != Material.AIR) {
				p.setAllowFlight(true);
			}
		}
	}

	@EventHandler
	public void onFlight(PlayerToggleFlightEvent e) {
		Player p = e.getPlayer();
		if (p.getGameMode().equals(GameMode.SURVIVAL)) {
			e.setCancelled(true);
			p.setAllowFlight(false);
			p.setFlying(false);
			p.setVelocity(p.getLocation().getDirection().multiply(1.5D).add(new Vector(0, 0.8, 0)));
			doublejump.add(p.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(GemExtras.getInstance(), new Runnable() {

				@Override
				public void run() {
					doublejump.remove(p.getName());

				}

			}, 4 * 20);
		}
	}

	@EventHandler
	public void onFall(PlayerItemDamageEvent e) {
		Player p = e.getPlayer();
		if (doublejump.contains(p.getName())) {
			if (p.getLastDamageCause().equals(DamageCause.FALL)) {
				e.setCancelled(true);

			}
		}
	}

}
