package me.mickyjou.plugins.gems.gemextras.shop.products;

import me.mickyjou.plugins.gems.gemextras.GemExtras;
import me.mickyjou.plugins.gems.gemextras.abilities.doublejump.DoubleJumpAbility;
import me.mickyjou.plugins.gems.gemextras.shop.ProductGroup;
import me.mickyjou.plugins.gems.gemextras.shop.SimpleProduct;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.time.Duration;
import java.util.Collections;

/**
 * The double jump products.
 */
public class DoubleJump extends ProductGroup {
    public DoubleJump() {
        super(ChatColor.GREEN + "Double Jump", new ItemStack(Material.SLIME_BLOCK), Collections.singletonList("Jump like a kangaroo!"));

        addItem(new SimpleProduct(Material.SLIME_BLOCK, ChatColor.GOLD + "1 hour", 2) {
            @Override
            public void onBought(Player player) {
                GemExtras.getPlugin(GemExtras.class).getAbilityManager().giveAbilityTo(DoubleJumpAbility.class, player, Duration.ofHours(1));
            }
        });
    }
}
