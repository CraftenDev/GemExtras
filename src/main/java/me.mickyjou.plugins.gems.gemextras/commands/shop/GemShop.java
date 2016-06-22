package me.mickyjou.plugins.gems.gemextras.commands.shop;

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

/**
 * The main view of the gem shop.
 */
public class GemShop extends SinglePageView {
    private final Player player;

    public GemShop(Player player) {
        super(ChatColor.GOLD + "Gem Shop", 9);
        this.player = player;

        addElement(createGemIcon());
        addElement(createWalkSpeedButton());
        addElement(createBoatButton());
    }

    private GuiElement createGemIcon() {
        int gems = Bukkit.getServicesManager().getRegistration(GemProvider.class).getProvider().getGems(player);
        return new Button(
                Material.EMERALD,
                ChatColor.GREEN + "You've got " + ChatColor.GOLD + gems + ChatColor.GREEN + " Gems."
        );

    }

    private GuiElement createWalkSpeedButton() {
        Button button = new Button(
                Material.DIAMOND_BOOTS,
                ChatColor.GOLD + "Double Jump",
                "Jump like a kangaroo!"
        );
        button.setOnClick(new ClickListener() {
            @Override
            public void clicked(InventoryClickEvent inventoryClickEvent) {
                getViewManager().showView(player, new DoubleJumpMenu(player));
            }
        });
        return button;
    }

    private GuiElement createBoatButton() {
        Button button = new Button(
                Material.BOAT,
                ChatColor.BLUE + "Boats",
                "Aye, pirate, get a boat and sail the sea!"
        );
        button.setOnClick(new ClickListener() {
            @Override
            public void clicked(InventoryClickEvent inventoryClickEvent) {
                getViewManager().showView(player, new BoatMenu(player));
            }
        });
        return button;
    }
}
