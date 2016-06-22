package me.mickyjou.plugins.gems.gemextras;

import me.mickyjou.plugins.gems.gemextras.commands.shop.ItemStacks;
import me.mickyjou.plugins.gems.gemextras.listeners.BoatClickListener;
import me.mickyjou.plugins.gems.gemextras.listeners.ChangeListener;
import me.mickyjou.plugins.gems.gemextras.listeners.WalkSpeedListener;
import me.mickyjou.plugins.gems.gemextras.listeners.WalkStopListener;
import org.bukkit.plugin.java.JavaPlugin;

public class GemExtras extends JavaPlugin {
    public void onEnable() {
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new BoatClickListener(), (this));
        getServer().getPluginManager().registerEvents(new WalkSpeedListener(), (this));
        getServer().getPluginManager().registerEvents(new ChangeListener(), (this));
        getServer().getPluginManager().registerEvents(new WalkStopListener(), (this));

        getCommand("gemshop").setExecutor(new ItemStacks());
    }
}
