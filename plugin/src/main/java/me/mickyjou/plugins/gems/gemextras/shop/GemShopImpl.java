package me.mickyjou.plugins.gems.gemextras.shop;

import de.craften.plugins.mcguilib.ViewManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * The main view of the gem shop.
 */
public class GemShopImpl implements GemShop {
    private final ProductGroup products;
    private final ViewManager viewManager;

    public GemShopImpl(ViewManager viewManager) {
        this.products = new ProductGroup("Gem Shop", new ItemStack(Material.AIR));
        this.viewManager = viewManager;
    }

    public void open(Player player) {
        viewManager.showView(player, new ProductGroupView(products, null));
    }

    public void addItem(ShopItem item) {
        products.addItem(item);
    }
}
