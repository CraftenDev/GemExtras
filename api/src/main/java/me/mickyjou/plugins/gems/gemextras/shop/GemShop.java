package me.mickyjou.plugins.gems.gemextras.shop;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * A GemShop.
 */
public interface GemShop {
    /**
     * Get the {@link GemShop} that is registered in the current Bukkit instance.
     *
     * @return ability manager that is registered in the current Bukkit instance
     */
    static Optional<GemShop> getInstance() {
        if (Bukkit.getServicesManager().isProvidedFor(GemShop.class)) {
            return Optional.of(Bukkit.getServicesManager().getRegistration(GemShop.class).getProvider());
        }
        return Optional.empty();
    }

    void open(Player player);

    void addItem(ShopItem item);
}
