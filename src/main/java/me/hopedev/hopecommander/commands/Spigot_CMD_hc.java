package me.hopedev.hopecommander.commands;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.hopedev.hopecommander.Spigot.SpigotMain;
import me.hopedev.hopecommander.universal.UNIVERSAL;
import me.hopedev.hopecommander.universal.UNI_onStartup;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Spigot_CMD_hc implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        if (sender instanceof Player) {
            SpigotMain spigotMain = (SpigotMain) new UNIVERSAL(UNI_onStartup.BACKEND.SUBSERVER).getPlugin();
            if (spigotMain.getConfig().getBoolean("allowPlayersCommandUsage")) {
                if (sender.isOp() && spigotMain.getConfig().getBoolean("allowOperatorExecution")) {
                    // System.out.println("Player is OP and allowOperatorExecution");
                    // DO it lmao
                    StringBuilder sb = new StringBuilder();
                    if (args.length < 1) {
                        sender.sendMessage("§6HopeCommander made by HopeDev");
                        sender.sendMessage("§9https://github.com/Hopefuls/HopeCommander/");
                        return false;
                    }

                    sendCommandRequestToProxy(StringUtils.join(args, " ", 0, args.length));

                } else if (sender.hasPermission(spigotMain.getConfig().getString("PermissionNode"))) {

                    // System.out.println("Player has Permission, but not op");

                    StringBuilder sb = new StringBuilder();
                    if (args.length < 1) {
                        sender.sendMessage("§6HopeCommander made by HopeDev");
                        sender.sendMessage("§9https://github.com/Hopefuls/HopeCommander/");
                        return false;
                    }
                    sendCommandRequestToProxy(StringUtils.join(args, " ", 0, args.length));

                } else if (!sender.hasPermission(spigotMain.getConfig().getString("PermissionNode")) && sender.isOp()) {
                    // System.out.println("Player is op but doesn't have permission");
                    if (!spigotMain.getConfig().getBoolean("allowOperatorExecution")) {
                        sender.sendMessage("§6HopeCommander made by HopeDev");
                        sender.sendMessage("§9https://github.com/Hopefuls/HopeCommander/");
                        return false;
                    }
                    // System.out.println("Player has Permission, but not op");

                    StringBuilder sb = new StringBuilder();
                    if (args.length < 1) {
                        sender.sendMessage("§6HopeCommander made by HopeDev");
                        sender.sendMessage("§9https://github.com/Hopefuls/HopeCommander/");
                        return false;
                    }
                    sendCommandRequestToProxy(StringUtils.join(args, " ", 0, args.length));

                } else {
                    // System.out.println("Has nothing");
                    sender.sendMessage("§6HopeCommander made by HopeDev");
                    sender.sendMessage("§9https://github.com/Hopefuls/HopeCommander/");
                    return false;
                }


            } else {
                sender.sendMessage("§6HopeCommander made by HopeDev");
                sender.sendMessage("§9https://github.com/Hopefuls/HopeCommander/");
                return true;
            }
            return true;
        }

        StringBuilder sb = new StringBuilder();
        if (args.length < 1) {
            sender.sendMessage("§6HopeCommander made by HopeDev");
            sender.sendMessage("§9https://github.com/Hopefuls/HopeCommander/");
            return false;
        }
        sendCommandRequestToProxy(StringUtils.join(args, " ", 0, args.length));
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("send");

        }

        return true;
    }

    public void sendCommandRequestToProxy(String commandtorun) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(commandtorun);

        // If you don't care about the player
        // Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        // Else, specify them
        if (Bukkit.getServer().getOnlinePlayers().size() == 0) {
            Bukkit.getConsoleSender().sendMessage("§cThis command can only be run if at least one Player is on your server");
            return;
        }

        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        SpigotMain spigotMain = (SpigotMain) new UNIVERSAL(UNI_onStartup.BACKEND.SUBSERVER).getPlugin();
        player.sendPluginMessage(spigotMain, "hope:hopecmdsend", out.toByteArray());

    }
}

