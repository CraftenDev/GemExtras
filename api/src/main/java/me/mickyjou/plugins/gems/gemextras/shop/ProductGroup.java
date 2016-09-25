package me.mickyjou.plugins.gems.gemextras.shop;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A group of shop items.
 *
 * @see Product
 */
public class ProductGroup implements ShopItem {
    private List<ShopItem> items = new ArrayList<>();
    private String displayName;
    private ItemStack item;
    private List<String> description;

    public ProductGroup(String displayName, ItemStack item, List<String> description) {
        this.displayName = displayName;
        this.item = item;
        this.description = description;
    }

    public ProductGroup(String displayName, ItemStack item) {
        this(displayName, item, null);
    }

    public void addItem(ShopItem item) {
        items.add(item);
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the title of this product group's menu. Defaults to {@link #getDisplayName()}.
     *
     * @return the title of this product group's menu
     */
    public String getTitle() {
        return getDisplayName();
    }

    @Override
    public List<String> getDescription() {
        return description;
    }

    @Override
    public ItemStack createDisplayItem() {
        return item.clone();
    }

    public List<ShopItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}
