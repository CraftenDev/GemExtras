package me.mickyjou.plugins.gems.gemextras.shop;

import de.craften.plugins.mcguilib.Button;
import de.craften.plugins.mcguilib.ClickListener;
import de.craften.plugins.mcguilib.GuiElement;
import de.craften.plugins.mcguilib.SinglePageView;
import me.mickyjou.plugins.gems.api.GemProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * A menu for buying gems.
 */
public class BuyGemsMenu extends SinglePageView {
    public BuyGemsMenu(Player player) {
        super("Double Jump", 9);

        addElement(new BackButton("Back", new ClickListener() {
            @Override
            public void clicked(InventoryClickEvent inventoryClickEvent) {
                getViewManager().showView(getViewer(), new GemShop(getViewer()));
            }
        }));

        addElement(createBuyButton(1, 8)); // 1 Gem for 8 diamonds
        addElement(createBuyButton(2, 16));
        addElement(createBuyButton(5, 32));
        addElement(createBuyButton(10, 64));
    }

    private GuiElement createBuyButton(final int gems, final int diamonds) {
        Button button = new Button(Material.EMERALD, ChatColor.GOLD + "" + gems + " Gems", diamonds + " diamonds");
        button.setNumber(gems);
        if (gems == 1) {
            button.setTitle(ChatColor.GOLD + "1 Gem");
        }
        button.setOnClick(new ClickListener() {
            @Override
            public void clicked(InventoryClickEvent inventoryClickEvent) {
                if (inventoryClickEvent.getWhoClicked() instanceof Player) {
                    Player player = (Player) inventoryClickEvent.getWhoClicked();
                    if (player.getInventory().contains(Material.DIAMOND, diamonds)) {
                        Bukkit.getServicesManager().getRegistration(GemProvider.class).getProvider().addGems(player, gems);
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, diamonds));
                        player.sendMessage(ChatColor.GREEN + "You just bought " + ChatColor.GOLD + gems + " Gems" + ChatColor.GREEN + ".");
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have enough diamonds to buy " + gems + " Gems.");
                    }
                }
            }
        });
        return button;
    }
}
