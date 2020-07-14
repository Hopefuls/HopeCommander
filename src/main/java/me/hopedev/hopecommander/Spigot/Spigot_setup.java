package me.hopedev.hopecommander.Spigot;

import me.hopedev.hopecommander.commands.Spigot_CMD_hc;
import me.hopedev.hopecommander.universal.UNIVERSAL;
import me.hopedev.hopecommander.universal.UNI_onStartup;

public class Spigot_setup {
    public static void setup() {

        SpigotMain spigotMain = (SpigotMain) new UNIVERSAL(UNI_onStartup.BACKEND.SUBSERVER).getPlugin();
        System.out.println("Starting HopeCommander...");
        System.out.println("Registering Commands..");
        spigotMain.getCommand("hcspigot").setExecutor(new Spigot_CMD_hc());
        System.out.append("Registering PluginChannels..");
        spigotMain.getServer().getMessenger().registerOutgoingPluginChannel(spigotMain, "hope:hopecmdsend");
        System.out.println("HopeCommander enabled successfully!");

    }
}
