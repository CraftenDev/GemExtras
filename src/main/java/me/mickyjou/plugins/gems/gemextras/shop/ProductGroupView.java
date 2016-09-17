package me.mickyjou.plugins.gems.gemextras.shop;

import de.craften.plugins.mcguilib.Button;
import de.craften.plugins.mcguilib.GuiElement;
import de.craften.plugins.mcguilib.MultiPageView;
import me.mickyjou.plugins.gems.api.GemProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * A view for a product group.
 *
 * @see ProductGroup
 */
public class ProductGroupView extends MultiPageView {
    private final ProductGroupView previous;

    public ProductGroupView(ProductGroup productGroup, ProductGroupView previous) {
        super(productGroup.getDisplayName(), (int) Math.ceil(productGroup.getItems().size() / 9.0) * 9);
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
                    meta.setLore(item.getDescription());
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
        // TODO
    }

    private GuiElement createGemIcon() {
        int gems = Bukkit.getServicesManager().getRegistration(GemProvider.class).getProvider().getGems(getViewer());
        return new Button(
                Material.EMERALD,
                ChatColor.GREEN + "You've got " + ChatColor.GOLD + gems + ChatColor.GREEN + " Gems."
        );
    }
}
