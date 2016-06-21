package me.mickyjou.plugins.gems.gemoptions.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import VirtualInventory.ItemStacks;
import net.md_5.bungee.api.ChatColor;

public class WalkSpeedListener implements Listener {
	File walkspeed = new File("plugins/GemSystem", "walkspeed.yml");
	FileConfiguration cfg = YamlConfiguration.loadConfiguration(walkspeed);
	private String Name;

	@EventHandler
	public void onWalkSpeed(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		String walk = cfg.getString(UUID.getUUID(p.getName()) + ".walkspeed");

		if (e.getInventory().getName().equalsIgnoreCase("Buy speed!")) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
				if (e.getCurrentItem().getType() == Material.GOLD_BOOTS) {
					e.setCancelled(true);
					if (!(walk == "true")) {
						if (!GemOptions.walk.contains(p.getName())) {
							if (10 <= GemCommands.getGems(p.getUniqueId())) {
								GemOptions.walk.add(p.getName());
								p.sendMessage(ChatColor.GREEN + "[Gems]" + ChatColor.GRAY
										+ " You are now much faster for " + ChatColor.GOLD + "one hour!"
										+ ChatColor.GRAY + " That costs " + ChatColor.GOLD + "10 Gems!");
								p.closeInventory();
								p.setWalkSpeed(1);
								GemCommands.removeGems(p.getName(), 10);

								Bukkit.getScheduler().scheduleSyncDelayedTask(GemOptions.getInstance(), new Runnable() {

									@Override
									public void run() {
										GemOptions.walk.remove(p.getName());

									}

								}, 60 * 60);

							} else {
								p.sendMessage(ChatColor.GREEN + "[Gems]" + ChatColor.RED
										+ " You don't have enough Gems for that!");
							}

						} else {
							p.closeInventory();
							p.sendMessage(ChatColor.GREEN + "[Gems]" + ChatColor.RED + " You already bought speed!");
						}
					} else {
						p.closeInventory();
						p.sendMessage(ChatColor.GREEN + "[Gems]" + ChatColor.RED + " You already bought speed!");
					}

				} else if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
					if (e.getCurrentItem().getType() == Material.DIAMOND_BOOTS) {
						if (!GemOptions.walk.contains(p.getName())) {
							if(!walk.contains("true")) {

							if (100 <= GemCommands.getGems(p.getUniqueId())) {
								cfg.set(UUID.getUUID(p.getName()) +".walkspeed", true);
								try {
									cfg.save(walkspeed);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								p.sendMessage(ChatColor.GREEN + "[Gems]" + ChatColor.GOLD
										+ " You are now much permanently faster!" + ChatColor.GOLD
										+ " That costs 100 Gems!!");
								p.setWalkSpeed(1);
								GemCommands.removeGems(p.getName(), 100);
								GemOptions.permawalk.add(p.getName());
								p.closeInventory();

							} else {
								p.sendMessage(ChatColor.GREEN + "[Gems]" + ChatColor.RED
										+ " You don't have enough Gems for that!");
								p.closeInventory();
							}
							} else {
								p.sendMessage(ChatColor.GREEN + "[Gems]" + ChatColor.RED + " You already bought speed!");
							}
						} else {
							p.closeInventory();
							p.sendMessage(ChatColor.GREEN + "[Gems]" + ChatColor.RED + " You already bought speed!");

						}
					}
				}

				if (e.getCurrentItem().getType() == Material.getMaterial(397)) {
					p.openInventory(ItemStacks.inventory);
				}
			}
		}
	}

	public static java.util.UUID getUUID(String name) {
		Player p = getPlayer(name);

		if (p != null) {
			return getPlayer(name).getUniqueId();
		}

		return null;
	}

	public static Player getPlayer(String playerName) {
		Object[] playerObjects = Bukkit.getServer().getOnlinePlayers().toArray();

		for (Object playerObject : playerObjects) {
			Player player = (Player) playerObject;
			if (player.getName().toLowerCase().equals(playerName.toLowerCase())) {
				return player;
			}
		}

		return null;
	}

}
