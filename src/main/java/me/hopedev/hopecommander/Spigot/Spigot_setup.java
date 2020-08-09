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

        // Spigot exclusive | added in v 1.2
        spigotMain.saveDefaultConfig();
        if (spigotMain.getConfig().getBoolean("allowPlayersCommandUsage")) {
            System.out.println("§c§l================HopeCommander================");
            System.out.println("§cWarning! Your Players are able to execute /hcspigot when one applies:");
            System.out.println("- §cThey have the Permission " + spigotMain.getConfig().getString("PermissionNode"));
            if (spigotMain.getConfig().getBoolean("allowOperatorExecution")) {
                System.out.println("- §cThey are opped");
            }
            System.out.println("§c§l================HopeCommander================");
        }
        System.out.println("HopeCommander enabled successfully!");

    }
}
