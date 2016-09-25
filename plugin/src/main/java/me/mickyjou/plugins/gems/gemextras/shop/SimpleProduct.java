package me.mickyjou.plugins.gems.gemextras.shop;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * A simple product.
 */
public abstract class SimpleProduct implements Product {
    private final Material material;
    private final String displayName;
    private final int cost;
    private final List<String> description;

    protected SimpleProduct(Material material, String displayName, int cost, List<String> description) {
        this.material = material;
        this.displayName = displayName;
        this.cost = cost;
        this.description = description;
    }

    protected SimpleProduct(Material material, String displayName, int cost) {
        this(material, displayName, cost, null);
    }


    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public List<String> getDescription() {
        return description;
    }

    @Override
    public ItemStack createDisplayItem() {
        return new ItemStack(material);
    }
}
