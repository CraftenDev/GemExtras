package me.mickyjou.plugins.gems.gemextras;

import de.craften.plugins.mcguilib.ViewManager;
import me.mickyjou.plugins.gems.gemextras.abilities.SpeedWalkAbility;
import me.mickyjou.plugins.gems.gemextras.abilities.doublejump.DoubleJumpAbility;
import me.mickyjou.plugins.gems.gemextras.abilities.doublejump.DoubleJumpListener;
import me.mickyjou.plugins.gems.gemextras.abilitymanager.AbilityManager;
import me.mickyjou.plugins.gems.gemextras.abilitymanager.AbilityManagerImpl;
import me.mickyjou.plugins.gems.gemextras.shop.GemShop;
import me.mickyjou.plugins.gems.gemextras.shop.GemShopImpl;
import me.mickyjou.plugins.gems.gemextras.shop.products.Boats;
import me.mickyjou.plugins.gems.gemextras.shop.products.DoubleJump;
import me.mickyjou.plugins.gems.gemextras.shop.products.Fireworks;
import me.mickyjou.plugins.gems.gemextras.shop.products.SpeedWalk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class GemExtras extends JavaPlugin {
    private ViewManager viewManager;
    private AbilityManagerImpl abilityManager;
    private GemShopImpl gemShop;

    public void onEnable() {
        saveDefaultConfig();
        viewManager = new ViewManager(this);
        abilityManager = new AbilityManagerImpl(this);
        getServer().getServicesManager().register(AbilityManager.class, abilityManager, this, ServicePriority.Normal);
        getServer().getPluginManager().registerEvents(abilityManager, this);

        abilityManager.registerAbility(new DoubleJumpAbility());
        getServer().getPluginManager().registerEvents(new DoubleJumpListener(), this);

        abilityManager.registerAbility(new SpeedWalkAbility());

        gemShop = new GemShopImpl(viewManager);
        getServer().getServicesManager().register(GemShop.class, gemShop, this, ServicePriority.Normal);
        gemShop.addItem(new Boats());
        gemShop.addItem(new SpeedWalk());
        gemShop.addItem(new DoubleJump());
        gemShop.addItem(new Fireworks());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("gemshop") && sender instanceof Player) {
            gemShop.open((Player) sender);
            return true;
        }
        return false;
    }

    /**
     * Gets the ability manager instance.
     *
     * @return the ability manager
     * @deprecated use {@link AbilityManager#getInstance()} instead
     */
    @Deprecated
    public AbilityManager getAbilityManager() {
        return abilityManager;
    }

    /**
     * Gets the gem shop instance.
     *
     * @return the gem shop
     * @deprecated use {@link GemShop#getInstance()} instead
     */
    @Deprecated
    public GemShop getGemShop() {
        return gemShop;
    }
}
