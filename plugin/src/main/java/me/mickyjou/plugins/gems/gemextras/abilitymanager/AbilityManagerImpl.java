package me.mickyjou.plugins.gems.gemextras.abilitymanager;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import de.craften.plugins.playerdatastore.api.PlayerDataStore;
import de.craften.plugins.playerdatastore.api.PlayerDataStoreService;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

public class AbilityManagerImpl implements AbilityManager, Listener {
    private final Plugin plugin;
    private final BukkitTask checkerTaskId;
    private final Map<Class<? extends Ability>, Ability> abilities = new HashMap<>();
    private final Multimap<Player, Class<? extends Ability>> playerAbilities = MultimapBuilder.hashKeys().arrayListValues(3).build();
    private final Multimap<Player, Class<? extends Ability>> pausedAbilities = MultimapBuilder.hashKeys().arrayListValues(3).build();

    public AbilityManagerImpl(Plugin plugin) {
        this.plugin = plugin;

        checkerTaskId = Bukkit.getServer().getScheduler().runTaskTimer(plugin, () -> {
            final long now = new Date().getTime();
            new ArrayList<>(playerAbilities.keys()).forEach((player) -> {
                PlayerDataStore store = getStore(player);
                new ArrayList<>(playerAbilities.get(player)).forEach((abilityClass) -> {
                    Ability ability = abilities.get(abilityClass);
                    try {
                        if (getEndTime(store, ability).get() < now) {
                            ability.removeFrom(player);
                            ability.onExpired(player);
                            playerAbilities.get(player).remove(abilityClass);
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        // ignore
                    }
                });
            });
        }, 20, 10 * 20);
    }

    @Override
    public void registerAbility(Ability ability) {
        abilities.put(ability.getClass(), ability);
    }

    @Override
    public void giveAbilityTo(Class<? extends Ability> abilityClass, Player player, final long durationSeconds) {
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

        if (!pausedAbilities.containsEntry(player, abilityClass)) {
            if (!playerAbilities.containsEntry(player, abilityClass)) {
                playerAbilities.put(player, abilityClass);
                ability.giveTo(player);
            }

            if (!renewed.get()) {
                ability.onActivated(player);
            }
        }
    }

    @Override
    public void giveAbilityTo(Class<? extends Ability> abilityClass, Player player, Duration duration) {
        giveAbilityTo(abilityClass, player, duration.getSeconds());
    }

    @Override
    public boolean hasAbility(Class<? extends Ability> abilityClass, Player player) {
        return playerAbilities.containsEntry(player, abilityClass);
    }

    @Override
    public void pauseAbility(Class<? extends Ability> abilityClass, Player player) {
        pausedAbilities.put(player, abilityClass);
        if (playerAbilities.get(player).contains(abilityClass)) {
            Ability ability = abilities.get(abilityClass);
            ability.removeFrom(player);
            playerAbilities.get(player).remove(abilityClass);
        }
    }

    @Override
    public void pauseAllAbilities(Player player) {
        abilities.keySet().forEach(ability -> pauseAbility(ability, player));
    }

    @Override
    public void unpauseAbility(Class<? extends Ability> abilityClass, Player player) {
        pausedAbilities.remove(player, abilityClass);
        if (!playerAbilities.get(player).contains(abilityClass)) {
            final long now = new Date().getTime();
            PlayerDataStore store = getStore(player);
            Ability ability = abilities.get(abilityClass);
            try {
                if (getEndTime(store, ability).get() > now) {
                    ability.giveTo(player);
                    playerAbilities.get(player).add(abilityClass);
                }
            } catch (InterruptedException | ExecutionException e) {
                // ignore
            }
        }
    }

    @Override
    public void unpauseAllAbilities(Player player) {
        pausedAbilities.removeAll(player).forEach(ability -> unpauseAbility(ability, player));
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
        pausedAbilities.removeAll(event.getPlayer());
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

    private static PlayerDataStore getStore(OfflinePlayer player) {
        return Bukkit.getServicesManager().getRegistration(PlayerDataStoreService.class).getProvider().getStore(player);
    }
}
