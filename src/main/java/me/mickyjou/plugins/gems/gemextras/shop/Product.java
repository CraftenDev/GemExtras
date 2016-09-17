package me.mickyjou.plugins.gems.gemextras.shop;

import org.bukkit.entity.Player;

/**
 * A product that is sold in the Gem shop.
 */
public interface Product extends ShopItem {
    /**
     * Gets the cost of this product.
     *
     * @return the cost of this product
     */
    int getCost();

    /**
     * Callback that is invoked after a player bought this product. When this method is invoked, the gems are already
     * payed.
     *
     * @param player player that bought this product
     */
    void onBought(Player player);
}
