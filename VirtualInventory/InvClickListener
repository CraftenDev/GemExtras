package VirtualInventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.mickyjou.main.GemOptions;
import net.md_5.bungee.api.ChatColor;

public class InvClickListener implements Listener {

	@EventHandler
	public void invClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		ItemStack arrowLeft = new ItemStack(Material.getMaterial(397), 1, (short) 3);
		SkullMeta skullmeta = (SkullMeta) arrowLeft.getItemMeta();

		if (e.getInventory().getName().equalsIgnoreCase("Gem-Shop")) {
			e.setCancelled(true);

			skullmeta.setDisplayName(ChatColor.GOLD + "Back");
			skullmeta.setOwner("MHF_ArrowLeft");
			arrowLeft.setItemMeta(skullmeta);
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {

				if (e.getCurrentItem().getType() == Material.DIAMOND_BOOTS) {
					Inventory walkspeedinv = p.getServer().createInventory(null, 9, "Buy speed!");
					ItemStack onewalkspeed = new ItemStack(Material.GOLD_BOOTS);
					ItemMeta onewalkspeedmeta = onewalkspeed.getItemMeta();
					onewalkspeedmeta.setDisplayName(ChatColor.GOLD + "Buy one hour speed!");
					onewalkspeed.setItemMeta(onewalkspeedmeta);

					ItemStack stop = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
					ItemMeta stopmeta = stop.getItemMeta();
					stopmeta.setDisplayName(ChatColor.RED + "Disable speed!");
					stop.setItemMeta(stopmeta);
					
					ItemStack walkcontinue = new ItemStack(Material.STAINED_CLAY, 1, (short) 13 );
					ItemMeta walkcontinuemeta = walkcontinue.getItemMeta();
					walkcontinuemeta.setDisplayName(ChatColor.GREEN + "Enable speed!");
					walkcontinue.setItemMeta(walkcontinuemeta);

					ItemStack walkspeed = new ItemStack(Material.DIAMOND_BOOTS);
					ItemMeta walkspeedmeta = walkspeed.getItemMeta();
					walkspeedmeta.setDisplayName(ChatColor.GOLD + "Buy permanent speed!");
					walkspeed.setItemMeta(walkspeedmeta);
					
					if(!GemOptions.permawalk.contains(p.getName())) {
						walkspeedinv.setItem(4, stop);
					}else {
						walkspeedinv.setItem(4, walkcontinue);
					}

					walkspeedinv.setItem(1, onewalkspeed);
					walkspeedinv.setItem(7, walkspeed);
					walkspeedinv.setItem(0, arrowLeft);
					p.openInventory(walkspeedinv);

				} else if (e.getCurrentItem().getType() == Material.DIAMOND) {
					Inventory diamondinv = p.getServer().createInventory(null, 9, "Change diamonds in Gems!");
					ItemStack diamond = new ItemStack(Material.DIAMOND);
					ItemMeta diamondmeta = diamond.getItemMeta();
					diamondmeta.setDisplayName(ChatColor.GOLD + "Change 64 Diamonds in 10 Gems!");
					diamond.setItemMeta(diamondmeta);

					diamondinv.setItem(0, arrowLeft);
					diamondinv.setItem(4, diamond);
					p.openInventory(diamondinv);

				} else if (e.getCurrentItem().getType() == Material.BOAT) {
					Inventory boatinv = p.getServer().createInventory(null, 9, "Buy boats!");
					ItemStack boat = new ItemStack(Material.BOAT);
					ItemMeta boatmeta = boat.getItemMeta();
					boatmeta.setDisplayName(ChatColor.GOLD + "Buy one boat!");
					

					ItemStack fiveboats = new ItemStack(Material.BOAT);
					ItemMeta fiveboatsmeta = fiveboats.getItemMeta();
					fiveboatsmeta.setDisplayName(ChatColor.GOLD + "Buy five boats!");
					fiveboats.setItemMeta(fiveboatsmeta);
					boat.setItemMeta(boatmeta);
					boatinv.setItem(0, arrowLeft);
					boatinv.setItem(1, boat);
					boatinv.setItem(7, fiveboats);
					p.openInventory(boatinv);

				}
			}
		}
	}
}
