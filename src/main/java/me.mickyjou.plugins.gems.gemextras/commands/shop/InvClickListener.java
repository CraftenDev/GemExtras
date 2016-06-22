package me.mickyjou.plugins.gems.gemextras.commands.shop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class InvClickListener implements Listener {
    @EventHandler
    public void invClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        ItemStack arrowLeft = new ItemStack(Material.getMaterial(397), 1, (short) 3);
        SkullMeta skullmeta = (SkullMeta) arrowLeft.getItemMeta();

        if (e.getInventory().getName().equalsIgnoreCase("Gem-Shop")) {
            e.setCancelled(true);

            skullmeta.setDisplayName(ChatColor.GOLD + "Back");
            skullmeta.setOwner("MHF_ArrowLeft");
            arrowLeft.setItemMeta(skullmeta);
            if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
                if (e.getCurrentItem().getType() == Material.DIAMOND) {
                    Inventory diamondinv = p.getServer().createInventory(null, 9, "Change diamonds in Gems!");
                    ItemStack diamond = new ItemStack(Material.DIAMOND);
                    ItemMeta diamondmeta = diamond.getItemMeta();
                    diamondmeta.setDisplayName(ChatColor.GOLD + "Change 64 Diamonds in 10 Gems!");
                    diamond.setItemMeta(diamondmeta);

                    diamondinv.setItem(0, arrowLeft);
                    diamondinv.setItem(4, diamond);
                    p.openInventory(diamondinv);

                }
            }
        }
    }
}
