package me.mickyjou.plugins.gems.gemextras.abilities.doublejump;

import me.mickyjou.plugins.gems.gemextras.abilitymanager.Ability;
import me.mickyjou.plugins.gems.gemextras.abilitymanager.AbilityManager;
import org.bukkit.entity.Player;

import java.util.Optional;

public class DoubleJumpAbility implements Ability {
    @Override
    public String getIdentifier() {
        return "gemextras.DoubleJump";
    }

    @Override
    public String getDisplayName() {
        return "Double Jump";
    }

    @Override
    public void removeFrom(Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
    }

    @Override
    public void giveTo(Player player) {
        player.setAllowFlight(true);
    }

    static boolean hasAbility(Player player) {
        Optional<AbilityManager> abilityManager = AbilityManager.getInstance();
        return abilityManager.isPresent() && abilityManager.get().hasAbility(DoubleJumpAbility.class, player);
    }
}
