package me.mickyjou.plugins.gems.gemextras.shop;

import de.craften.plugins.mcguilib.Button;
import de.craften.plugins.mcguilib.ClickListener;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * A back button.
 */
public class BackButton extends Button {
    public BackButton(String title, ClickListener onClick) {
        super(Material.SKULL_ITEM, (byte) 3, title);
        setOnClick(onClick);
    }

    @Override
    public ItemStack createItem() {
        ItemStack item = super.createItem();
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner("MHF_ArrowLeft");
        item.setItemMeta(meta);
        return item;
    }
}
