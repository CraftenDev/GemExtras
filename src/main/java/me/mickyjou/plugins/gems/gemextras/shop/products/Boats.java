package me.mickyjou.plugins.gems.gemextras.shop.products;

import me.mickyjou.plugins.gems.gemextras.shop.ProductGroup;
import me.mickyjou.plugins.gems.gemextras.shop.SimpleProduct;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

/**
 * Buy multiple boats.
 */
public class Boats extends ProductGroup {
    private static final Material[] BOATS;

    static {
        if (Material.getMaterial("BOAT_SPRUCE") != null) {
            BOATS = new Material[]{
                    Material.BOAT,
                    Material.getMaterial("BOAT_SPRUCE"),
                    Material.getMaterial("BOAT_BIRCH"),
                    Material.getMaterial("BOAT_JUNGLE"),
                    Material.getMaterial("BOAT_ACACIA"),
                    Material.getMaterial("BOAT_DARK_OAK"),
            };
        } else {
            BOATS = new Material[]{Material.BOAT};
        }
    }

    public Boats() {
        super("Buy boats", new ItemStack(Material.BOAT), Collections.singletonList("Aye, pirate, get a boat and sail the sea!"));

        for (final Material material : BOATS) {
            addItem(new SimpleProduct(material, "Boat", 5) {
                @Override
                public void onBought(Player player) {
                    player.getInventory().addItem(new ItemStack(material));
                }
            });
        }
    }
}
