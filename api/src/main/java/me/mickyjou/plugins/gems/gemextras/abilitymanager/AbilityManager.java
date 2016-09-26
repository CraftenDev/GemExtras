package me.mickyjou.plugins.gems.gemextras.abilitymanager;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Optional;

/**
 * A manager for abilities that can be given to players for a certain time.
 */
public interface AbilityManager {
    /**
     * Get the {@link AbilityManager} that is registered in the current Bukkit instance.
     *
     * @return ability manager that is registered in the current Bukkit instance
     */
    static Optional<AbilityManager> getInstance() {
        if (Bukkit.getServicesManager().isProvidedFor(AbilityManager.class)) {
            return Optional.of(Bukkit.getServicesManager().getRegistration(AbilityManager.class).getProvider());
        }
        return Optional.empty();
    }

    /**
     * Registers an ability.
     *
     * @param ability ability
     */
    void registerAbility(Ability ability);

    /**
     * Gives an ability to a player. The ability needs to be registered with {@link #registerAbility(Ability)} before.
     *
     * @param abilityClass    class of the ability to give to a player
     * @param player          player
     * @param durationSeconds duration, in seconds, that this ability should last
     */
    @Deprecated
    void giveAbilityTo(Class<? extends Ability> abilityClass, Player player, long durationSeconds);

    /**
     * Gives an ability to a player. The ability needs to be registered with {@link #registerAbility(Ability)} before.
     *
     * @param abilityClass class of the ability to give to a player
     * @param player       player
     * @param duration     duration that this ability should last
     */
    void giveAbilityTo(Class<? extends Ability> abilityClass, Player player, Duration duration);

    /**
     * Checks if a player has an ability. The ability needs to be registered with {@link #registerAbility(Ability)} before.
     *
     * @param abilityClass class of the ability to check
     * @param player       player
     * @return true if the player has the specified ability, false if not
     */
    boolean hasAbility(Class<? extends Ability> abilityClass, Player player);

    /**
     * Removes the given ability from the player until they rejoin or change the world or
     * {@link #unpauseAbility(Class, Player)} is called. If the player doesn't have that ability, this method does
     * nothing.
     *
     * @param abilityClass class of the ability to pause
     * @param player       player
     */
    void pauseAbility(Class<? extends Ability> abilityClass, Player player);

    /**
     * Removes all abilities from the player until they rejoin or change the world or
     * {@link #unpauseAbility(Class, Player)} is called. If the player doesn't have that ability, this method does
     * nothing.
     *
     * @param player player
     */
    void pauseAllAbilities(Player player);

    /**
     * Adds the given ability to the player if they already had it and it was removed with
     * {@link #pauseAbility(Class, Player)}. If the player doesn't have the ability, this method does nothing.
     *
     * @param abilityClass class of the ability to unpause
     * @param player       player
     */
    void unpauseAbility(Class<? extends Ability> abilityClass, Player player);

    /**
     * Unpauses all previously paused abilities.
     *
     * @param player player
     */
    void unpauseAllAbilities(Player player);
}
