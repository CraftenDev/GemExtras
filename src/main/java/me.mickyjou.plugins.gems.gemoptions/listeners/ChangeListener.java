package me.mickyjou.plugins.gems.gemoptions.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import VirtualInventory.ItemStacks;
import net.md_5.bungee.api.ChatColor;

public class ChangeListener implements Listener {

	@EventHandler
	public void onCangeClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase("Change Diamonds in Gems!")) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
				if (e.getCurrentItem().getType() == Material.DIAMOND) {
					if (p.getInventory().contains(Material.DIAMOND, 1)) {
						ItemStack diamonds = new ItemStack(Material.DIAMOND, 64);
						p.getInventory().remove(diamonds);
						p.closeInventory();

						GemCommands.addGems(p.getName(), 100);
						p.sendMessage(ChatColor.GRAY + "You got " + ChatColor.GOLD + 100 + ChatColor.GRAY + "gems!");
					} else {
						p.sendMessage(ChatColor.RED + "You don't have enough diamonds for that!");
						p.closeInventory();
					}
				}

			}
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
				if (e.getCurrentItem().getType() == Material.getMaterial(397)) {

					p.openInventory(ItemStacks.inventory);
				}
			}
		}
	}

}
