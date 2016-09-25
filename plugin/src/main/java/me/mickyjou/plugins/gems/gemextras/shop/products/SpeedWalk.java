package me.mickyjou.plugins.gems.gemextras.shop.products;

import me.mickyjou.plugins.gems.gemextras.GemExtras;
import me.mickyjou.plugins.gems.gemextras.abilities.SpeedWalkAbility;
import me.mickyjou.plugins.gems.gemextras.shop.ProductGroup;
import me.mickyjou.plugins.gems.gemextras.shop.SimpleProduct;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.time.Duration;
import java.util.Collections;

/**
 * The speed walk products.
 */
public class SpeedWalk extends ProductGroup {
    public SpeedWalk() {
        super(ChatColor.GOLD + "Speed Walk", new ItemStack(Material.DIAMOND_BOOTS), Collections.singletonList("Run like a cheetah!"));

        addItem(new SimpleProduct(Material.IRON_BOOTS, "Speed Walk (1 hour)", 5) {
            @Override
            public void onBought(Player player) {
                GemExtras.getPlugin(GemExtras.class).getAbilityManager().giveAbilityTo(SpeedWalkAbility.class, player, Duration.ofHours(1));
            }
        });

        addItem(new SimpleProduct(Material.GOLD_BOOTS, "Speed Walk (2 hours)", 8) {
            @Override
            public void onBought(Player player) {
                GemExtras.getPlugin(GemExtras.class).getAbilityManager().giveAbilityTo(SpeedWalkAbility.class, player, Duration.ofHours(2));
            }
        });

        addItem(new SimpleProduct(Material.DIAMOND_BOOTS, "Speed Walk (6 hours)", 20) {
            @Override
            public void onBought(Player player) {
                GemExtras.getPlugin(GemExtras.class).getAbilityManager().giveAbilityTo(SpeedWalkAbility.class, player, Duration.ofHours(6));
            }
        });

        addItem(new SimpleProduct(Material.CHAINMAIL_BOOTS, "Speed Walk (24 hours)", 42) {
            @Override
            public void onBought(Player player) {
                GemExtras.getPlugin(GemExtras.class).getAbilityManager().giveAbilityTo(SpeedWalkAbility.class, player, Duration.ofHours(24));
            }
        });
    }
}
