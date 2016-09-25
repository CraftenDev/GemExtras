package me.mickyjou.plugins.gems.gemextras.shop.products;

import me.mickyjou.plugins.gems.gemextras.shop.SimpleProduct;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 * Fireworks (only one right now).
 */
public class Fireworks extends SimpleProduct {
    public Fireworks() {
        super(Material.FIREWORK, ChatColor.YELLOW + "Fireworks", 10);
    }

    @Override
    public void onBought(Player player) {
        ItemStack rockets = new ItemStack(Material.FIREWORK, 5);
        FireworkMeta meta = (FireworkMeta) rockets.getItemMeta();
        meta.clearEffects();
        meta.addEffect(FireworkEffect.builder()
                .withColor(Color.RED)
                .withFade(Color.YELLOW)
                .withFlicker()
                .build());
        rockets.setItemMeta(meta);
        player.getInventory().addItem(rockets);
    }

    @Override
    public ItemStack createDisplayItem() {
        ItemStack displayItem = super.createDisplayItem();
        displayItem.setAmount(5);
        return displayItem;
    }
}
