package me.mickyjou.plugins.gems.gemextras.shop;

import de.craften.plugins.mcguilib.Button;
import de.craften.plugins.mcguilib.SinglePageView;
import de.craften.plugins.mcguilib.View;
import me.mickyjou.plugins.gems.api.GemProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Products to buy gems with diamonds.
 */
public class BuyGemsMenu extends SinglePageView {
    public BuyGemsMenu(View previous) {
        super(ChatColor.DARK_AQUA + "Buy Gems", 9);

        addElement(new BackButton("Back", (e) -> getViewManager().showView(getViewer(), previous)));
        addElement(createGemsItem(1, 8));
        addElement(createGemsItem(2, 16));
        addElement(createGemsItem(5, 32));
        addElement(createGemsItem(10, 64));
    }

    private Button createGemsItem(int gems, int diamonds) {
        Button button = new Button(Material.EMERALD, ChatColor.GOLD + "" + gems + " Gems", diamonds + " diamonds");
        button.setOnClick((e) -> {
            Player player = getViewer();
            if (player.getInventory().contains(Material.DIAMOND, diamonds)) {
                Bukkit.getServicesManager().getRegistration(GemProvider.class).getProvider().addGems(player, gems);
                player.getInventory().removeItem(new ItemStack(Material.DIAMOND, diamonds));
                player.sendMessage(ChatColor.GREEN + "You just bought " + ChatColor.GOLD + gems + " Gems" + ChatColor.GREEN + ".");
            } else {
                player.sendMessage(ChatColor.RED + "You don't have enough diamonds to buy " + gems + " Gems.");
            }
        });
        button.setNumber(gems);
        return button;
    }
}
