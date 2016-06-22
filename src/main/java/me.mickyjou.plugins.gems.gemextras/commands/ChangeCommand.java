package me.mickyjou.plugins.gems.gemextras.commands;

import me.mickyjou.plugins.gems.api.GemProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChangeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final GemProvider gemProvider = Bukkit.getServicesManager().getRegistration(GemProvider.class).getProvider();
        Player p = (Player) sender;

        if (args.length == 0) {

            if (p.getInventory().contains(Material.DIAMOND, 1)) {
                ItemStack diamonds = new ItemStack(Material.DIAMOND, 64);
                p.getInventory().remove(diamonds);

                gemProvider.addGems(p, 100);
                p.sendMessage(ChatColor.GRAY + "You got " + ChatColor.GOLD + 100 + ChatColor.GRAY + "gems!");
            } else {
                p.sendMessage(ChatColor.RED + "You don't have enough diamonds for that!");
            }

        } else {
            p.sendMessage(ChatColor.RED + "Please use /change!");
        }
        return false;
    }
}
