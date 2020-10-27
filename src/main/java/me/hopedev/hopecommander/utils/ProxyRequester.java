package me.hopedev.hopecommander.utils;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.hopedev.hopecommander.Spigot.SpigotMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ProxyRequester {

    /*
    Made by Aurel (Hope) on 27/10/2020
     */

    public static void sendPluginMessage(String var1) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(var1);

        if (Bukkit.getServer().getOnlinePlayers().size() == 0) {
            Bukkit.getConsoleSender().sendMessage("§cThis command can only be run if at least one Player is on your server");
        } else {
            Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
            player.sendPluginMessage((SpigotMain) UniversalUsage.get().getPluginInstance(), "hope:hopecmdsend", out.toByteArray());
        }

    }
}
