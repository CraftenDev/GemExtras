package me.mickyjou.plugins.gems.gemextras.listeners;

import me.mickyjou.plugins.gems.api.commands.GemCommands;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class FireworkListener implements Listener {

    @EventHandler
    public void onFireworkClick(InventoryClickEvent e) {
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Buy one Firework")) {
            if (10 <= GemCommands.getGems(p.getUniqueId())) {
                ItemStack rockets = new ItemStack(Material.FIREWORK, 10);
                p.getInventory().addItem(rockets);

                GemCommands.removeGems(p.getName(), 10);
                p.sendMessage(ChatColor.GREEN + "[Gems] " + ChatColor.GRAY + "You got " + ChatColor.GOLD + "10 Fireworks " + ChatColor.GRAY + "for "
                        + ChatColor.GOLD + "10 " + ChatColor.GRAY + "gems!");
                p.closeInventory();

            } else {
                p.sendMessage(ChatColor.GREEN + "[Gems] " + ChatColor.RED + "You don't have enough Gems for that!");
                p.closeInventory();
            }
        }
    }

}