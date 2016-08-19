package me.mickyjou.plugins.gems.gemextras;

import de.craften.plugins.mcguilib.Button;
import de.craften.plugins.mcguilib.ClickListener;
import de.craften.plugins.mcguilib.SinglePageView;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * The double jump menu.
 */
public class DoubleJumpMenu extends SinglePageView {
    public DoubleJumpMenu(Player player) {
        super("Double Jump", 9);

        addElement(new BackButton("Back", new ClickListener() {
            @Override
            public void clicked(InventoryClickEvent inventoryClickEvent) {
                getViewManager().showView(getViewer(), new GemShop(getViewer()));
            }
        }));

        Button oneHourJump = new BuyButton(2, Material.SLIME_BLOCK, ChatColor.GOLD + "1 hour") {
            @Override
            protected void onBuyItem(Player player) {
                //TODO
            }
        };
        addElement(oneHourJump);
    }
}
