package me.mickyjou.plugins.gems.gemextras.shop;

import de.craften.plugins.mcguilib.SinglePageView;
import me.mickyjou.plugins.gems.gemextras.GemExtras;
import me.mickyjou.plugins.gems.gemextras.abilities.SpeedWalk;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.time.Duration;

/**
 * The walk speed menu.
 */
public class SpeedWalkMenu extends SinglePageView {
    public SpeedWalkMenu(Player player) {
        super("Speed Walk", 9);

        addElement(new BackButton("Back", e -> {
            getViewManager().showView(getViewer(), new GemShop(getViewer()));
        }));

        addElement(new BuyButton(5, Material.IRON_BOOTS, "1 hour") {
            @Override
            protected void onBuyItem(Player player) {
                GemExtras.getPlugin(GemExtras.class).getAbilityManager().giveAbilityTo(SpeedWalk.class, player, Duration.ofHours(1));
            }
        });

        addElement(new BuyButton(8, Material.GOLD_BOOTS, "2 hours") {
            @Override
            protected void onBuyItem(Player player) {
                GemExtras.getPlugin(GemExtras.class).getAbilityManager().giveAbilityTo(SpeedWalk.class, player, Duration.ofHours(2));
            }
        });

        addElement(new BuyButton(20, Material.DIAMOND_BOOTS, "6 hours") {
            @Override
            protected void onBuyItem(Player player) {
                GemExtras.getPlugin(GemExtras.class).getAbilityManager().giveAbilityTo(SpeedWalk.class, player, Duration.ofHours(6));
            }
        });

        addElement(new BuyButton(42, Material.CHAINMAIL_BOOTS, "24 hours") {
            @Override
            protected void onBuyItem(Player player) {
                GemExtras.getPlugin(GemExtras.class).getAbilityManager().giveAbilityTo(SpeedWalk.class, player, Duration.ofHours(24));
            }
        });
    }
}
