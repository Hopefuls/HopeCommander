package me.hopedev.hopecommander.commands;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.hopedev.hopecommander.Spigot.SpigotMain;
import me.hopedev.hopecommander.universal.UNIVERSAL;
import me.hopedev.hopecommander.universal.UNI_onStartup;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spigot_CMD_hc implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage("§6HopeCommander made by HopeDev");
            sender.sendMessage("§9https://github.com/Hopefuls/HopeCommander/");
            return true;
        }
        StringBuilder sb = new StringBuilder();
        if (args.length < 1)
            return false;

        for (int i = 0; i < args.length; i++) {
            sb.append(args[i] + " ");
        }
        sendCommandRequestToProxy(sb.toString());
        sender.sendMessage("send");

        return false;
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

