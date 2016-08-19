package me.mickyjou.plugins.gems.gemextras.listeners;

import me.mickyjou.plugins.gems.gemextras.GemExtras;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class DoubleJumpListener implements Listener {
    private final Set<Player> doublejump = new HashSet<>();

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.SURVIVAL)) {
            if (player.getLocation().add(0, -1, 0).getBlock().getType().isSolid()) {
                player.setAllowFlight(true);
            }
        }
    }

    @EventHandler
    public void onFlight(PlayerToggleFlightEvent event) {
        final Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.SURVIVAL)) {
            event.setCancelled(true);
            player.setAllowFlight(false);
            player.setFlying(false);
            player.setVelocity(player.getVelocity().multiply(2.5D).setY(1.0));
            doublejump.add(player);
            Bukkit.getScheduler().scheduleSyncDelayedTask(GemExtras.getPlugin(GemExtras.class), new Runnable() {

                @Override
                public void run() {
                    doublejump.remove(player);
                }

            }, 4 * 20);
        }
    }

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            if (doublejump.contains(player)) {
                if (event.getCause() == DamageCause.FALL) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
