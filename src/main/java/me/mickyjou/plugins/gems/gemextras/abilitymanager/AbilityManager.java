package me.mickyjou.plugins.gems.gemextras.abilitymanager;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import de.craften.plugins.playerdatastore.api.PlayerDataStore;
import de.craften.plugins.playerdatastore.api.PlayerDataStoreService;
import me.mickyjou.plugins.gems.api.GemProvider;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A manager for abilities that can be given to players for a certain time.
 */
public class AbilityManager implements Listener {
    private final Plugin plugin;
    private final BukkitTask checkerTaskId;
    private final Map<Class<? extends Ability>, Ability> abilities = new HashMap<>();
    private final Multimap<Player, Class<? extends Ability>> playerAbilities = MultimapBuilder.hashKeys().arrayListValues(3).build();

    public AbilityManager(Plugin plugin) {
        this.plugin = plugin;

        checkerTaskId = Bukkit.getServer().getScheduler().runTaskTimer(plugin, () -> {
            final long now = new Date().getTime();
            playerAbilities.keys().forEach((player) -> {
                PlayerDataStore store = getStore(player);
                playerAbilities.get(player).forEach((abilityClass) -> {
                    Ability ability = abilities.get(abilityClass);
                    try {
                        if (getEndTime(store, ability).get() < now) {
                            ability.removeFrom(player);
                            ability.onExpired(player);
                            playerAbilities.remove(player, abilityClass);
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        // ignore
                    }
                });
            });
        }, 20, 10 * 20);
    }

    public void registerAbility(Ability ability) {
        abilities.put(ability.getClass(), ability);
    }

    public void giveAbilityTo(Class<Ability> abilityClass, Player player, final long durationSeconds) {
        Ability ability = abilities.get(abilityClass);
        if (ability == null) {
            throw new IllegalStateException("Tried to give unregistered ability " + abilityClass + " to a player");
        }

        final long now = new Date().getTime();
        AtomicBoolean renewed = new AtomicBoolean(false);
        getStore(player).update(ability.getIdentifier(), (oldEndTimeString) -> {
            if (oldEndTimeString == null) {
                return Long.toString(now + durationSeconds * 1000);
            } else {
                try {
                    Long oldEndTime = Long.parseLong(oldEndTimeString);
                    if (oldEndTime < now) {
                        return Long.toString(now + durationSeconds * 1000);
                    } else {
                        renewed.set(true);
                        return Long.toString(oldEndTime + durationSeconds * 1000);
                    }
                } catch (NumberFormatException e) {
                    return Long.toString(now + durationSeconds * 1000);
                }
            }
        });

        if (!playerAbilities.containsEntry(player, abilityClass)) {
            ability.giveTo(player);
        }

        if (!renewed.get()) {
            ability.onActivated(player);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final long now = new Date().getTime();
        PlayerDataStore store = getStore(event.getPlayer());
        abilities.values().parallelStream()
                .forEach((ability) -> getEndTime(store, ability)
                        .thenAccept((endTime) -> {
                            if (endTime > now) {
                                Bukkit.getScheduler().runTask(plugin, () -> {
                                    if (event.getPlayer().isOnline()) {
                                        playerAbilities.put(event.getPlayer(), ability.getClass());
                                        ability.giveTo(event.getPlayer());
                                    }
                                });
                            }
                        }));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        playerAbilities.removeAll(event.getPlayer()).stream()
                .map(abilities::get)
                .forEach((ability -> ability.removeFrom(event.getPlayer())));
    }

    // TODO handle world changes (some abilities may only be allowed in certain worlds)

    private static CompletableFuture<Long> getEndTime(PlayerDataStore store, Ability ability) {
        return store.getAsync(ability.getIdentifier())
                .thenApply((oldEndTimeString) -> {
                    try {
                        return oldEndTimeString == null ? 0 : Long.parseLong(oldEndTimeString);
                    } catch (NumberFormatException e) {
                        return 0L;
                    }
                });
    }

    private static GemProvider getDataStore(OfflinePlayer player) {
        return Bukkit.getServicesManager().getRegistration(GemProvider.class).getProvider();
    }

    private static PlayerDataStore getStore(OfflinePlayer player) {
        return Bukkit.getServicesManager().getRegistration(PlayerDataStoreService.class).getProvider().getStore(player);
    }
}
