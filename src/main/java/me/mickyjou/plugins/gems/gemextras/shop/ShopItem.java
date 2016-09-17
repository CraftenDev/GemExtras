package me.mickyjou.plugins.gems.gemextras.shop;

import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

/**
 * An item that can be displayed in a shop.
 */
interface ShopItem {
    /**
     * Gets the display name of this product.
     *
     * @return the display name of this product
     */
    String getDisplayName();

    /**
     * Gets the description of this product.
     *
     * @return the description of this product
     */
    default List<String> getDescription() {
        return Collections.emptyList();
    }

    /**
     * Gets the item that is displayed in the shop.
     *
     * @return the item that is displayed in the shop
     */
    ItemStack createDisplayItem();
}
