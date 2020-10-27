package me.hopedev.hopecommander.Spigot;

import me.hopedev.hopecommander.utils.ProxyRequester;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpigotCommand implements CommandExecutor {

    /*
    Made by Aurel (Hope) on 27/10/2020
     */

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

