package me.mickyjou.plugins.gems.gemextras.listeners;

import java.io.File;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import net.md_5.bungee.api.ChatColor;

public class WalkStopListener implements Listener {
    File walkspeed = new File("plugins/GemSystem", "walkspeed.yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(walkspeed);


    @EventHandler
    public void onStopSpeed(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        String walk = cfg.getString(UUID.getUUID(p.getName()) + ".walkspeed");
        if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
            if (e.getInventory().getName().equalsIgnoreCase("Buy speed!")) {
                if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(ChatColor.RED + "Disable speed!")) {
                    e.setCancelled(true);
                    if (!GemOptions.walk.contains(p.getName())) {
                        if (!GemOptions.permawalk.contains(p.getName())) {
                            if (walk.contains("true")) {
                                p.setWalkSpeed((float) 0.2);
                                p.sendMessage(ChatColor.GREEN + "[Gems]" + ChatColor.RED + " You disabled your speed!");
                                GemOptions.permawalk.add(p.getName());
                                p.closeInventory();

                            } else {
                                p.sendMessage(ChatColor.GREEN + "[Gems]" + ChatColor.RED + " You first have to buy permanently Speed!");
                                p.closeInventory();
                            }

                        }
                    } else {
                        p.sendMessage(ChatColor.GREEN + "[Gems]" + ChatColor.RED + " You already bought speed!");
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(ChatColor.GREEN + "Enable speed!")) {
                    e.setCancelled(true);
                    if (GemOptions.permawalk.contains(p.getName())) {
                        if (walk.contains("true")) {
                            p.setWalkSpeed(1);
                            p.sendMessage(ChatColor.GREEN + "[Gems]" + ChatColor.GREEN + " You enabled your speed!");
                            GemOptions.permawalk.remove(p.getName());
                            p.closeInventory();
                        } else {
                            p.sendMessage(ChatColor.GREEN + "[Gems]" + ChatColor.RED + " You first have to buy permanently Speed!");
                            p.closeInventory();
                        }
                    } else {
                        p.sendMessage(ChatColor.GREEN + "[Gems]" + ChatColor.RED + " You first have to buy permanently Speed!");
                        p.closeInventory();
                    }
                }

            }
        }
    }

}
