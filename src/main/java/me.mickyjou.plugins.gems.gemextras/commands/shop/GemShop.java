package me.mickyjou.plugins.gems.gemextras.commands.shop;

import de.craften.plugins.mcguilib.Button;
import de.craften.plugins.mcguilib.ClickListener;
import de.craften.plugins.mcguilib.GuiElement;
import de.craften.plugins.mcguilib.SinglePageView;
import me.mickyjou.plugins.gems.api.GemProvider;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 * The main view of the gem shop.
 */
public class GemShop extends SinglePageView {
    private final Player player;

    public GemShop(Player player) {
        super("★ Gem Shop ★", 9);
        this.player = player;

        addElement(createWalkSpeedButton());
        addElement(createDoubleJumpButton());
        addElement(createBoatButton());
        addElement(createFireworksButton());

        insertElement(8, createGemIcon());
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
                ChatColor.GOLD + "Speed Walk",
                "Run like a cheetah!"
        );
        button.setOnClick(new ClickListener() {
            @Override
            public void clicked(InventoryClickEvent inventoryClickEvent) {
                getViewManager().showView(player, new SpeedWalkMenu(player));
            }
        });
        return button;
    }

    private GuiElement createDoubleJumpButton() {
        Button button = new Button(
                Material.SLIME_BLOCK,
                ChatColor.GREEN + "Double Jump",
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

    private GuiElement createFireworksButton() {
        // TODO show a fireworks menu with different fireworks
        Button button = new BuyButton(10, Material.FIREWORK, ChatColor.YELLOW + "Buy fireworks") {
            @Override
            protected void onBuyItem(Player player) {
                ItemStack rockets = new ItemStack(Material.FIREWORK, 5);
                FireworkMeta meta = (FireworkMeta) rockets.getItemMeta();
                meta.clearEffects();
                meta.addEffect(FireworkEffect.builder()
                        .withColor(Color.RED)
                        .withFade(Color.YELLOW)
                        .withFlicker()
                        .build());
                rockets.setItemMeta(meta);
                player.getInventory().addItem(rockets);
            }
        };
        button.setNumber(5);
        return button;
    }
}
