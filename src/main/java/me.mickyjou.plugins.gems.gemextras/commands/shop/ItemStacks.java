package me.mickyjou.plugins.gems.gemextras.commands.shop;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.mickyjou.main.GemCommands;
import me.mickyjou.main.GemOptions;
import net.md_5.bungee.api.ChatColor;

public class ItemStacks implements CommandExecutor, Listener {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            ItemStack walkspeed = new ItemStack(Material.DIAMOND_BOOTS);
            ItemMeta walkspeedmeta = walkspeed.getItemMeta();
            walkspeedmeta.setDisplayName(ChatColor.GOLD + "Buy speed!");
            walkspeed.setItemMeta(walkspeedmeta);

            ItemStack diamond = new ItemStack(Material.DIAMOND);
            ItemMeta diamondmeta = diamond.getItemMeta();
            diamondmeta.setDisplayName(ChatColor.GOLD + "Change diamonds in Gems!");
            diamond.setItemMeta(diamondmeta);

            ItemStack firework = new ItemStack(Material.FIREWORK);
            ItemMeta fireworkmeta = firework.getItemMeta();
            fireworkmeta.setDisplayName(ChatColor.GOLD + "Buy Fireworks!");
            firework.setItemMeta(fireworkmeta);

            inventory.setItem(9, walkspeed);
            inventory.setItem(13, diamond);
            inventory.setItem(15, firework);
            p.openInventory(inventory);

        } else {
            System.out.println("You have to be a Player to execute this command!");
        }
        return false;
    }


}
