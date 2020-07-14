package me.hopedev.hopecommander.Bungeecord;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

public class BungeeTestCommand extends Command {
    public BungeeTestCommand(String name) {
        super(name);
    }

    public void execute(CommandSender commandSender, String[] strings) {
        commandSender.sendMessage(new ComponentBuilder("test").create());
    }
}
