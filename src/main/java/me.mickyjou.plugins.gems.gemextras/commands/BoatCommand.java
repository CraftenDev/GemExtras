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

public class BoatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final GemProvider gemProvider = Bukkit.getServicesManager().getRegistration(GemProvider.class).getProvider();
        Player p = (Player) sender;

        if (args.length == 0) {
            if (100 <= gemProvider.getGems(p)) {
                ItemStack boat = new ItemStack(Material.BOAT);
                p.getInventory().addItem(boat);
                gemProvider.removeGems(p, 100);
                p.sendMessage(ChatColor.GRAY + "You got a " + ChatColor.GOLD + "boat" + ChatColor.GRAY + " for 100 gems!");
            } else {
                p.sendMessage(ChatColor.RED + "You don't have enough Gems for that!");
            }

        } else {
            p.sendMessage(ChatColor.RED + "Please use /change!");
        }

        return false;
    }
}
