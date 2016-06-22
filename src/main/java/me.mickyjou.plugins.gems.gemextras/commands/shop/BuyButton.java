package me.mickyjou.plugins.gems.gemextras.commands.shop;

import de.craften.plugins.mcguilib.Button;
import de.craften.plugins.mcguilib.ClickListener;
import de.craften.plugins.mcguilib.text.TextBuilder;
import me.mickyjou.plugins.gems.api.GemProvider;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * A button to buy things with Gems.
 */
public abstract class BuyButton extends Button {
    private final int cost;


    public BuyButton(final int cost, Material material, byte data, String title, String... description) {
        super(material, data, title, description);
        this.cost = cost;
        setOnClick(new ClickListener() {
            @Override
            public void clicked(InventoryClickEvent inventoryClickEvent) {
                if (inventoryClickEvent.getWhoClicked() instanceof Player) {
                    Player player = (Player) inventoryClickEvent.getWhoClicked();

                    if (Bukkit.getServicesManager().getRegistration(GemProvider.class).getProvider().removeGems(player, cost)) {
                        onBuyItem(player);
                    } else {
                        onBuyItemFailed(player);
                    }
                }
            }
        });
    }

    public BuyButton(int cost, Material material, String title, String... description) {
        this(cost, material, (byte) 0, title, description);
    }

    protected abstract void onBuyItem(Player player);

    protected void onBuyItemFailed(Player player) {
        int gems = Bukkit.getServicesManager().getRegistration(GemProvider.class).getProvider().getGems(player);
        TextBuilder.create("You don't have enough Gems to buy this. (").red()
                .append(cost + " Gems").gold()
                .append(" required, but you only have ").red()
                .append(gems + " Gems").gold()
                .append(")").red()
                .sendTo(player);
    }
}
