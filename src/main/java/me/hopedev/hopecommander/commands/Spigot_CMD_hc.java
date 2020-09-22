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

    //Code cleanup done on 09/08/2020
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        SpigotMain spigotMain = (SpigotMain) new UNIVERSAL(UNI_onStartup.BACKEND.SUBSERVER).getPlugin();

        assert spigotMain != null;
        boolean allowPlayersCommandUsage = spigotMain.getConfig().getBoolean("allowPlayersCommandUsage", false);
        boolean allowOperatorExecution = spigotMain.getConfig().getBoolean("allowOperatorExecution", false);

        String PermissionNode = spigotMain.getConfig().getString("PermissionNode", "hopecommander.useCommand");
        assert PermissionNode != null;

        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Successfully_Sent");

            sendCommandRequestToProxy(StringUtils.join(args, " ", 0, args.length));
            return true;

        }
        Player p = (Player) sender;

        if (!allowPlayersCommandUsage) {
            sendResult(p);
            return true;
        }
        if (p.isOp()) {
            if (!p.hasPermission(PermissionNode)) {
                sendResult(p);
                return true;
            }
        } else if (!p.isOp()) {

            if (!p.hasPermission(PermissionNode)) {
                sendResult(p);
                return true;
            }
        }

        sendCommandRequestToProxy(StringUtils.join(args, " ", 0, args.length));

        return true;
    }

    public void sendResult(Player sender) {
        sender.sendMessage("§6HopeCommander made by HopeDev");
        sender.sendMessage("§9https://github.com/Hopefuls/HopeCommander/");
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
        assert player != null;
        assert spigotMain != null;
        player.sendPluginMessage(spigotMain, "hope:hopecmdsend", out.toByteArray());

    }
}

