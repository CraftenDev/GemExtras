package me.mickyjou.plugins.gems.gemextras;

import de.craften.plugins.mcguilib.ClickListener;
import de.craften.plugins.mcguilib.SinglePageView;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * The walk speed menu.
 */
public class SpeedWalkMenu extends SinglePageView {
    public SpeedWalkMenu(Player player) {
        super("Speed Walk", 9);

        addElement(new BackButton("Back", new ClickListener() {
            @Override
            public void clicked(InventoryClickEvent inventoryClickEvent) {
                getViewManager().showView(getViewer(), new GemShop(getViewer()));
            }
        }));

        addElement(new BuyButton(5, Material.IRON_BOOTS, "1 hour") {
            @Override
            protected void onBuyItem(Player player) {
                //TODO
            }
        });

        addElement(new BuyButton(8, Material.GOLD_BOOTS, "2 hours") {
            @Override
            protected void onBuyItem(Player player) {
                //TODO
            }
        });

        addElement(new BuyButton(20, Material.DIAMOND_BOOTS, "6 hours") {
            @Override
            protected void onBuyItem(Player player) {
                //TODO
            }
        });

        addElement(new BuyButton(42, Material.CHAINMAIL_BOOTS, "24 hours") {
            @Override
            protected void onBuyItem(Player player) {
                //TODO
            }
        });
    }
}
