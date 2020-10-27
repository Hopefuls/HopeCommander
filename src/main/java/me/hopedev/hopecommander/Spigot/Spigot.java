package me.hopedev.hopecommander.Spigot;

import me.hopedev.hopecommander.utils.UniversalUsage;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Spigot extends UniversalUsage {

    /*
    Made by Aurel (Hope) on 27/10/2020
     */
    private final Object pluginInstance;

    public Spigot(Object pluginInstance) {
        this.pluginInstance = pluginInstance;
    }

    @Override
    public void sendMessage(Object CommandSender, String message) {
        CommandSender sender = (CommandSender) CommandSender;
        sender.sendMessage(message);
    }

    @Override
    public void setup() {
        SpigotMain spigotMain = (SpigotMain) this.pluginInstance;
        UniversalUsage.universalUsage = this;
        System.out.println("Starting HopeCommander...");
        System.out.println("Registering Commands..");
        spigotMain.getCommand("hopecommander").setExecutor(new SpigotCommand());
        System.out.append("Registering PluginChannels..");
        spigotMain.getServer().getMessenger().registerOutgoingPluginChannel(spigotMain, "hope:hopecmdsend");
        System.out.println("HopeCommander enabled successfully!");
    }

    @Override
    public UsageType getUsageType() {
        return UsageType.SPIGOT;
    }

    @Override
    public Object getPluginInstance() {
        return this.pluginInstance;
    }
}
