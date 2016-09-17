package me.mickyjou.plugins.gems.gemextras.abilities.doublejump;

import me.mickyjou.plugins.gems.gemextras.GemExtras;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.HashSet;
import java.util.Set;

public class DoubleJumpListener implements Listener {
    private final Set<Player> jumping = new HashSet<>();

    @EventHandler
    public void onFlight(PlayerToggleFlightEvent event) {
        final Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.SURVIVAL) && DoubleJumpAbility.hasAbility(player)) {
            event.setCancelled(true);
            player.setFlying(false);
            player.setVelocity(player.getVelocity().multiply(2.5D).setY(1.0));
            jumping.add(player);
            Bukkit.getScheduler().scheduleSyncDelayedTask(GemExtras.getPlugin(GemExtras.class), new Runnable() {
                @Override
                public void run() {
                    jumping.remove(player);
                }
            }, 4 * 20);
        }
    }

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            if (jumping.contains(player)) {
                if (event.getCause() == DamageCause.FALL) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
