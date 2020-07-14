package me.hopedev.hopecommander.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.hopedev.hopecommander.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spigot_CMD_hc implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        Player p = (Player) sender;
        sendTestData(p);
        sender.sendMessage("Successfully sent!");

        return false;
    }

    public void sendTestData(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Testing");
        out.writeUTF("Testing");

        // If you don't care about the player
        // Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        // Else, specify them

        player.sendPluginMessage(Main.getPlugin(), "test:channel", out.toByteArray());
    }
}

