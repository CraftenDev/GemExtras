package me.mickyjou.plugins.gems.gemextras.shop;

import de.craften.plugins.mcguilib.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * A menu to buy boats.
 */
public class BoatMenu extends SinglePageView {
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

    public BoatMenu(Player player) {
        super("Buy boats", 9);

        addElement(new BackButton("Back", new ClickListener() {
            @Override
            public void clicked(InventoryClickEvent inventoryClickEvent) {
                getViewManager().showView(getViewer(), new GemShop(getViewer()));
            }
        }));

        for (final Material material : BOATS) {
            Button button = new BuyButton(5, material, null) {
                @Override
                protected void onBuyItem(Player player) {
                    player.getInventory().addItem(new ItemStack(material));
                }
            };
            addElement(button);
        }
    }
}
