package me.mickyjou.plugins.gems.gemextras.abilities.doublejump;

import me.mickyjou.plugins.gems.gemextras.GemExtras;
import me.mickyjou.plugins.gems.gemextras.abilitymanager.Ability;
import org.bukkit.entity.Player;

public class DoubleJumpAbility implements Ability {
    @Override
    public String getIdentifier() {
        return "gemextras.DoubleJump";
    }

    @Override
    public String getDisplayName() {
        return "Double Jump";
    }

    static boolean hasAbility(Player player) {
        return GemExtras.getPlugin(GemExtras.class).getAbilityManager().hasAbility(DoubleJumpAbility.class, player);
    }
}
