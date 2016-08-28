package me.mickyjou.plugins.gems.gemextras.abilitymanager;


import org.bukkit.entity.Player;

/**
 * A manager for abilities that can be given to players for a certain time.
 */
public interface AbilityManager {
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
    void giveAbilityTo(Class<Ability> abilityClass, Player player, long durationSeconds);
}
