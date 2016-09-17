package me.mickyjou.plugins.gems.gemextras.shop;

import de.craften.plugins.mcguilib.Button;
import de.craften.plugins.mcguilib.GuiElement;
import de.craften.plugins.mcguilib.MultiPageView;
import de.craften.plugins.mcguilib.text.TextBuilder;
import me.mickyjou.plugins.gems.api.GemProvider;
import me.mickyjou.plugins.gems.gemextras.GemExtras;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

/**
 * A view for a product group.
 *
 * @see ProductGroup
 */
public class ProductGroupView extends MultiPageView {
    private final ProductGroupView previous;

    public ProductGroupView(ProductGroup productGroup, ProductGroupView previous) {
        super(productGroup.getTitle(), (int) Math.ceil(productGroup.getItems().size() / 9.0) * 9);
        this.previous = previous;

        for (ShopItem item : productGroup.getItems()) {
            addElement(createButton(item));
        }
    }

    @Override
    protected void onCreateInventory() {
        super.onCreateInventory();
        insertElement(getSize() + 4, createGemIcon());

        if (getPage() == 0 && previous != null) {
            insertElement(getSize(), new BackButton("Back", (e) -> getViewManager().showView(getViewer(), previous)));
        }
    }

    private GuiElement createButton(ShopItem item) {
        GuiElement button = new GuiElement() {
            @Override
            public ItemStack createItem() {
                ItemStack itemStack = item.createDisplayItem();
                ItemMeta meta = itemStack.getItemMeta();
                if (item.getDisplayName() != null) {
                    meta.setDisplayName(item.getDisplayName());
                }
                if (item.getDescription() != null) {
                    if (item instanceof Product) {
                        List<String> lore = Collections.singletonList(ChatColor.GOLD + "" + ((Product) item).getCost() + " Gems");
                        lore.addAll(item.getDescription());
                        meta.setLore(lore);
                    } else {
                        meta.setLore(item.getDescription());
                    }
                } else if (item instanceof Product) {
                    meta.setLore(Collections.singletonList(ChatColor.GOLD + "" + ((Product) item).getCost() + " Gems"));
                }
                itemStack.setItemMeta(meta);
                return itemStack;
            }
        };
        button.setOnClick((e) -> {
            if (e.getWhoClicked() instanceof Player) {
                if (item instanceof ProductGroup) {
                    getViewManager().showView(getViewer(), new ProductGroupView((ProductGroup) item, ProductGroupView.this));
                } else if (item instanceof Product) {
                    buyProduct((Product) item);
                }
            }
        });
        return button;
    }

    private void buyProduct(Product product) {
        if (Bukkit.getServicesManager().getRegistration(GemProvider.class).getProvider().removeGems(getViewer(), product.getCost())) {
            try {
                product.onBought(getViewer());
                TextBuilder.create("You bought ").green()
                        .append(ChatColor.stripColor(product.getDisplayName())).gold()
                        .append(" for ").green()
                        .append(product.getCost() + " Gems").gold()
                        .append(".").green()
                        .sendTo(getViewer());
            } catch (Exception e) {
                GemExtras.getPlugin(GemExtras.class).getLogger().log(Level.WARNING, "Buying " + ChatColor.stripColor(product.getDisplayName()) + " failed", e);
                Bukkit.getServicesManager().getRegistration(GemProvider.class).getProvider().addGems(getViewer(), product.getCost());
                TextBuilder.create("An error occurred while buying ").red()
                        .append(ChatColor.stripColor(product.getDisplayName())).gold()
                        .append(". Your Gems were refunded. Please report this problem to the server operators.").red()
                        .sendTo(getViewer());
            }
        } else {
            int gems = Bukkit.getServicesManager().getRegistration(GemProvider.class).getProvider().getGems(getViewer());
            TextBuilder.create("You don't have enough Gems to buy ").red()
                    .append(ChatColor.stripColor(product.getDisplayName())).gold()
                    .append(" (").red()
                    .append(product.getCost() + " Gems").gold()
                    .append(" required, you only have ").red()
                    .append(gems + "").gold()
                    .append(").").red()
                    .sendTo(getViewer());
        }
    }

    private GuiElement createGemIcon() {
        int gems = Bukkit.getServicesManager().getRegistration(GemProvider.class).getProvider().getGems(getViewer());
        Button gemButton = new Button(
                Material.EMERALD,
                ChatColor.GREEN + "You've got " + ChatColor.GOLD + gems + ChatColor.GREEN + " Gems.",
                "Click to buy more gems."
        );
        gemButton.setOnClick((e) -> getViewManager().showView(getViewer(), new BuyGemsMenu(this)));
        return gemButton;
    }
}
