package me.mickyjou.plugins.gems.gemoptions.commands;

import me.mickyjou.plugins.gems.api.GemProvider;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WalkSpeedCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final GemProvider gemProvider = Bukkit.getServicesManager().getRegistration(GemProvider.class).getProvider();
        Player p = (Player) sender;
        if (args.length == 0) {
            if (!GemOptions.walk.contains(p.getName())) {
                if (10 <= GemCommands.getGems(p.getUniqueId())) {
                    GemOptions.walk.add(p.getName());
                    p.sendMessage(ChatColor.GRAY + "You are now much faster for one hour! That costs " + ChatColor.GOLD + "10" + ChatColor.GRAY + " Gems!");
                    p.setWalkSpeed((float) 1);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(GemOptions.getInstance(), new Runnable() {

                        @Override
                        public void run() {
                            GemOptions.walk.remove(p.getName());
                            p.sendMessage(ChatColor.GOLD + "The one hour is over! Your speed is away!");
                            p.setWalkSpeed((float) 0.2);

                        }

                    }, 5 * 20);
                } else {
                    p.sendMessage(ChatColor.RED + "You don't have enough Gems for that!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "You already bought Speed!");
            }

        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("stop")) {

            } else {
                p.sendMessage(ChatColor.RED + "Wrong usage!");
            }
        }
        return false;
    }
}