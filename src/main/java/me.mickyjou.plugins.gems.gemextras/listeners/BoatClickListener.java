package me.mickyjou.plugins.gems.gemextras.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import VirtualInventory.ItemStacks;
import net.md_5.bungee.api.ChatColor;

public class BoatClickListener implements Listener {

	@EventHandler
	public void invBoatClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		if (e.getInventory().getName().equalsIgnoreCase("Buy boats!")) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Buy one boat!")) {
					e.setCancelled(true);
					if (10 <= GemCommands.getGems(p.getUniqueId())) {
						ItemStack boat = new ItemStack(Material.BOAT);
						p.getInventory().addItem(boat);
						GemCommands.removeGems(p.getName(), 10);
						p.sendMessage(ChatColor.GRAY + "You got a " + ChatColor.GOLD + "boat" + ChatColor.GRAY
								+ " for 10 gems!");
						p.closeInventory();
					} else {
						p.sendMessage(ChatColor.RED + "You don't have enough Gems for that!");
						p.closeInventory();
					}
				} else {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Buy five boats!")) {
						if (100 <= GemCommands.getGems(p.getUniqueId())) {
							ItemStack boats = new ItemStack(Material.BOAT);
							p.getInventory().addItem(boats);
							p.getInventory().addItem(boats);
							p.getInventory().addItem(boats);
							p.getInventory().addItem(boats);
							p.getInventory().addItem(boats);
							GemCommands.removeGems(p.getName(), 50);
							p.sendMessage(ChatColor.GRAY + "You got " + ChatColor.GOLD + "five boats" + ChatColor.GRAY
									+ " for 50 gems!");
							p.closeInventory();
						} else {
							p.sendMessage(ChatColor.RED + "You don't have enough Gems for that!");
							p.closeInventory();
						}
					}else {
						if (e.getCurrentItem().getType() == Material.getMaterial(397)) {
							p.openInventory(ItemStacks.inventory);
						}
				}
			} 
			}

		}

	}
}
