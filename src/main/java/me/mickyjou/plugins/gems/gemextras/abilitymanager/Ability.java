package me.mickyjou.plugins.gems.gemextras.abilitymanager;

import org.bukkit.entity.Player;

/**
 * An ability that can be given to a player.
 */
public interface Ability {
    /**
     * Gets a unique identifier for this ability.
     *
     * @return a unique identifier for this ability
     */
    String getIdentifier();

    /**
     * Gets the display name of this ability.
     *
     * @return the display name of this ability
     */
    String getDisplayName();

    /**
     * Gives this ability to a player. This is also called after rejoining.
     *
     * @param player player
     */
    default void giveTo(Player player) {
    }

    /**
     * Removes this ability from a player. This is also called when the player leaves the server.
     *
     * @param player player
     */
    default void removeFrom(Player player) {
    }

    /**
     * Called when this ability was initially given to a player. Don't use this to give the ability
     * to the player but rather to do something special when a player gets it.
     *
     * @param player player
     */
    default void onActivated(Player player) {
    }

    /**
     * Called when this ability expires and was removed from a player that is currently online. Don't use this to
     * remove the ability from the player. This method is not guaranteed to be called.
     *
     * @param player player
     */
    default void onExpired(Player player) {
    }
}
