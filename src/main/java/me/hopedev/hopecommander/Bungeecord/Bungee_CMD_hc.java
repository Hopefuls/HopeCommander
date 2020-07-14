package me.hopedev.hopecommander.Bungeecord;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

public class Bungee_CMD_hc extends Command {
    public Bungee_CMD_hc(String name) {
        super(name);
    }

    public void execute(CommandSender commandSender, String[] strings) {
        commandSender.sendMessage(new ComponentBuilder("").create());
    }
}
