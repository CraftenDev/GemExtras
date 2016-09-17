package me.mickyjou.plugins.gems.gemextras.shop.products;

import me.mickyjou.plugins.gems.api.GemProvider;
import me.mickyjou.plugins.gems.gemextras.shop.Product;
import me.mickyjou.plugins.gems.gemextras.shop.ProductGroup;
import me.mickyjou.plugins.gems.gemextras.shop.SimpleProduct;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

/**
 * Products to buy gems with diamonds.
 */
public class Gems extends ProductGroup {
    public Gems() {
        super(ChatColor.DARK_AQUA + "Buy Gems with diamonds", new ItemStack(Material.DIAMOND), Collections.singletonList("Get those neat Gems!"));

        addItem(createGemsItem(1, 8));
        addItem(createGemsItem(2, 16));
        addItem(createGemsItem(5, 32));
        addItem(createGemsItem(10, 64));
    }

    private Product createGemsItem(int gems, int diamonds) {
        return new SimpleProduct(Material.EMERALD, ChatColor.GOLD + "" + gems + " Gems", 0, Collections.singletonList(diamonds + " diamonds")) {
            @Override
            public void onBought(Player player) {
                if (player.getInventory().contains(Material.DIAMOND, diamonds)) {
                    Bukkit.getServicesManager().getRegistration(GemProvider.class).getProvider().addGems(player, gems);
                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, diamonds));
                    player.sendMessage(ChatColor.GREEN + "You just bought " + ChatColor.GOLD + gems + " Gems" + ChatColor.GREEN + ".");
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have enough diamonds to buy " + gems + " Gems.");
                }
            }
        };
    }
}
