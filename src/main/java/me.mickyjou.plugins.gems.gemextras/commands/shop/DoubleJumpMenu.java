package me.mickyjou.plugins.gems.gemextras.commands.shop;

import de.craften.plugins.mcguilib.Button;
import de.craften.plugins.mcguilib.SinglePageView;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * The double jump menu.
 */
public class DoubleJumpMenu extends SinglePageView {
    public DoubleJumpMenu(Player player) {
        super("Double Jump", 9);

        Button oneHourJump = new BuyButton(2, Material.SLIME_BLOCK, ChatColor.GOLD + "1 hour") {
            @Override
            protected void onBuyItem(Player player) {
                //TODO
            }
        };
        addElement(oneHourJump);
    }
}
