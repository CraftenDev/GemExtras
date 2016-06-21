package me.mickyjou.plugins.gems.gemoptions;

import VirtualInventory.InvClickListener;
import VirtualInventory.ItemStacks;
import me.mickyjou.plugins.gems.gemoptions.commands.ChangeCommand;
import me.mickyjou.plugins.gems.gemoptions.listeners.BoatClickListener;
import me.mickyjou.plugins.gems.gemoptions.listeners.ChangeListener;
import me.mickyjou.plugins.gems.gemoptions.listeners.WalkSpeedListener;
import me.mickyjou.plugins.gems.gemoptions.listeners.WalkStopListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class GemOptions extends JavaPlugin {
    public int changeAmount = getConfig().getInt("Config.changeAmount");
    public int changeGems = getConfig().getInt("Config.changeGems");
    public String baum = getConfig().getString("baum.baum");
    public static GemOptions instance;
    public static ArrayList<String> walk = new ArrayList<>();
    public static ArrayList<String> doublejump = new ArrayList<>();
    public static String changeDiamonds;
    public static ArrayList<String> permawalk = new ArrayList<>();

    public void onEnable() {
        registerCommands();
        instance = this;
        loadConfig();
        getServer().getPluginManager().registerEvents(new InvClickListener(), (this));
        getServer().getPluginManager().registerEvents(new BoatClickListener(), (this));
        getServer().getPluginManager().registerEvents(new WalkSpeedListener(), (this));
        getServer().getPluginManager().registerEvents(new ChangeListener(), (this));
        getServer().getPluginManager().registerEvents(new WalkStopListener(), (this));
        getServer().getPluginManager().registerEvents(new ItemStacks(), (this));
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();

    }

    public void onDisable() {

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return false;

    }

    public void registerCommands() {
        ChangeCommand cchange = new ChangeCommand(this);
        getCommand("change").setExecutor(cchange);
        BoatCommand cboat = new BoatCommand(this);
        getCommand("boat").setExecutor(cboat);
        ItemStacks citem = new ItemStacks(this);
        getCommand("shop").setExecutor(citem);
        WalkSpeedCommand cwalk = new WalkSpeedCommand(this);
        getCommand("walk").setExecutor(cwalk);
        FireworkCommand cfirework = new FireworkCommand(this);
        getCommand("firework").setExecutor(cfirework);


    }

    public static GemOptions getInstance() {
        return instance;
    }
}
