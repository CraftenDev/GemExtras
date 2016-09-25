package me.mickyjou.plugins.gems.gemextras.abilities.doublejump;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
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
            if (!jumping.contains(player)) {
                player.setVelocity(player.getVelocity().multiply(2.5D).setY(1.0));
                jumping.add(player);
            }
        }
    }

    @EventHandler
    public void onLand(PlayerMoveEvent event) {
        if (jumping.contains(event.getPlayer()) &&
                event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
            jumping.remove(event.getPlayer());
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

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        jumping.remove(event.getPlayer());
    }
}
