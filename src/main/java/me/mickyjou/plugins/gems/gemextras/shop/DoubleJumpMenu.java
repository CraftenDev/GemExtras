package me.mickyjou.plugins.gems.gemextras.shop;

import de.craften.plugins.mcguilib.Button;
import de.craften.plugins.mcguilib.ClickListener;
import de.craften.plugins.mcguilib.SinglePageView;
import me.mickyjou.plugins.gems.gemextras.GemExtras;
import me.mickyjou.plugins.gems.gemextras.abilities.doublejump.DoubleJump;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.time.Duration;

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
                GemExtras.getPlugin(GemExtras.class).getAbilityManager().giveAbilityTo(DoubleJump.class, player, Duration.ofHours(1));
            }
        };
        addElement(oneHourJump);
    }
}
