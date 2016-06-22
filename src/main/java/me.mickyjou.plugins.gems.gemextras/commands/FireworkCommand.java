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

public class FireworkCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final GemProvider gemProvider = Bukkit.getServicesManager().getRegistration(GemProvider.class).getProvider();
        Player p = (Player) sender;

        if (args.length == 0) {
            if (10 <= gemProvider.getGems(p)) {
                ItemStack rockets = new ItemStack(Material.FIREWORK, 10);
                p.getInventory().addItem(rockets);

                gemProvider.removeGems(p, 10);
                p.sendMessage(ChatColor.GRAY + "You got " + ChatColor.GOLD + "10 Fireworks " + ChatColor.GRAY + "for "
                        + ChatColor.GOLD + "10 " + ChatColor.GRAY + "gems!");

            } else {
                p.sendMessage(ChatColor.RED + "You don't have enough Gems for that!");
            }

        } else {
            p.sendMessage(ChatColor.RED + "Please use /firework!");
        }
        return false;
    }
}
