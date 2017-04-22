package me.mickyjou.plugins.gems.gemextras.abilities;

import me.mickyjou.plugins.gems.gemextras.abilitymanager.Ability;
import org.bukkit.entity.Player;

public class SpeedWalkAbility implements Ability {
    @Override
    public String getIdentifier() {
        return "gemextras.SpeedWalk";
    }

    @Override
    public String getDisplayName() {
        return "Speed Walk";
    }

    @Override
    public void giveTo(Player player) {
        player.setWalkSpeed(0.3f);
    }

    @Override
    public void removeFrom(Player player) {
        player.setWalkSpeed(0.1f);
    }
}
