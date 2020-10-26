package me.hopedev.hopecommander.Spigot;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.hopedev.hopecommander.utils.ProxyRequester;
import me.hopedev.hopecommander.utils.UniversalUsage;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpigotCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String cmd, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage("§6HopeCommander made by HopeDev");
            sender.sendMessage("§9https://github.com/Hopefuls/HopeCommander/");
            return true;
        }
        if (args.length < 1)
            return false;

        ProxyRequester.sendPluginMessage(StringUtils.join(args, " "));
        sender.sendMessage("Request sent successfully");

        return false;
    }
}

