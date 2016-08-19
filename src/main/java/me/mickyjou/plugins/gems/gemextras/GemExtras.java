package me.mickyjou.plugins.gems.gemextras;

import de.craften.plugins.mcguilib.ViewManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GemExtras extends JavaPlugin {
    private ViewManager viewManager;

    public void onEnable() {
        saveDefaultConfig();
        viewManager = new ViewManager(this);
        getServer().getPluginManager().registerEvents(new DoubleJumpListener(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("gemshop") && sender instanceof Player) {
            viewManager.showView((Player) sender, new GemShop((Player) sender));
            return true;
        }
        return false;
    }
}
