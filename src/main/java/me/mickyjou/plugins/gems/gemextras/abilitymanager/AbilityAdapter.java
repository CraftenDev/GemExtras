package me.mickyjou.plugins.gems.gemextras.abilitymanager;

import org.bukkit.entity.Player;

/**
 * Abstract adapter for {@link Ability} that implements all methods with no-ops, for convenience.
 */
public abstract class AbilityAdapter implements Ability {
    @Override
    public void giveTo(Player player) {
        // no-op
    }

    @Override
    public void removeFrom(Player player) {
        // no-op
    }

    @Override
    public void onActivated(Player player) {
        // no-op
    }

    @Override
    public void onExpired(Player player) {
        // no-op
    }
}
