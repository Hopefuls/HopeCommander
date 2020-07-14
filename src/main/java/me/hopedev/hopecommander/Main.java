package me.hopedev.hopecommander;

import me.hopedev.hopecommander.commands.CMD_hc;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
    private static Main Main;

    public static Main getPlugin() {
        return Main;
    }

    @Override
    public void onDisable() {
        System.out.println("HopeCommander disabled! Goodbye.");
    }

    @Override
    public void onEnable() {
        Main = this;
        System.out.println("Starting HopeCommander...");
        System.out.println("Registering Commands..");
        this.getCommand("hc").setExecutor(new CMD_hc());
        System.out.append("Registering PluginChannels");
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "test:channel");
        System.out.println("HopeCommander enabled! Thanks for using :)");
    }
}
