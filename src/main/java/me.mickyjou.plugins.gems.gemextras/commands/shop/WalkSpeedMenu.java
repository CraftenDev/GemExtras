package me.mickyjou.plugins.gems.gemextras.commands.shop;

import de.craften.plugins.mcguilib.SinglePageView;
import de.craften.plugins.mcguilib.View;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * The walk speed menu.
 */
public class WalkSpeedMenu extends SinglePageView {
    public WalkSpeedMenu(Player player) {
        super("Walk Speed", 9);

        addElement(new BuyButton(5, Material.IRON_BOOTS, "1 hour Fast Walk") {
            @Override
            protected void onBuyItem(Player player) {
                //TODO
            }
        });

        addElement(new BuyButton(8, Material.GOLD_BOOTS, "2 hours Fast Walk") {
            @Override
            protected void onBuyItem(Player player) {
                //TODO
            }
        });

        addElement(new BuyButton(20, Material.DIAMOND_BOOTS, "6 hours Fast Walk") {
            @Override
            protected void onBuyItem(Player player) {
                //TODO
            }
        });

        addElement(new BuyButton(42, Material.CHAINMAIL_BOOTS, "24 hours Fast Walk") {
            @Override
            protected void onBuyItem(Player player) {
                //TODO
            }
        });
    }
}
