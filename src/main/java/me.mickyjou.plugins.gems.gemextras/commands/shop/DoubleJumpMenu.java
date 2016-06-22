package me.mickyjou.plugins.gems.gemextras.commands.shop;

import de.craften.plugins.mcguilib.Button;
import de.craften.plugins.mcguilib.ClickListener;
import de.craften.plugins.mcguilib.SinglePageView;
import de.craften.plugins.mcguilib.View;
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

        Button oneHourJump = new Button(Material.GOLD_BOOTS, ChatColor.GOLD + "1 hour");
        oneHourJump.setOnClick(new ClickListener() {
            @Override
            public void clicked(InventoryClickEvent inventoryClickEvent) {
                //TODO
            }
        });
        addElement(oneHourJump);
    }
}
