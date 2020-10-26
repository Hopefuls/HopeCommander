package me.hopedev.hopecommander.Bungeecord;

import me.hopedev.hopecommander.Bungeecord.Listeners.PluginMessage;
import me.hopedev.hopecommander.utils.UniversalUsage;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

public class Bungee extends UniversalUsage {

    private final Object pluginInstance;

    public Bungee(Object pluginInstance) {
        this.pluginInstance = pluginInstance;
    }

    @Override
    public void sendMessage(Object CommandSender, String message) {
        CommandSender sender = (CommandSender) CommandSender;
        sender.sendMessage();
    }

    @Override
    public void setup() {
        BungeeMain instance = (BungeeMain) this.pluginInstance;
        instance.getProxy().getPluginManager().registerListener(instance, new PluginMessage());
        instance.getProxy().registerChannel("hope:hopecmdsend");
        instance.getProxy().getPluginManager().registerCommand(instance, getCommand("hopecommander"));
        UniversalUsage.universalUsage = this;
    }

    @Override
    public UniversalUsage.UsageType getUsageType() {
        return UsageType.BUNGEE;
    }

    @Override
    public Object getPluginInstance() {
        return this.pluginInstance;
    }

    public static Command getCommand(String name) {
        return new Command(name) {
            @Override
            public void execute(CommandSender commandSender, String[] strings) {
                UniversalUsage.get().sendMessage(commandSender, "§6HopeCommander made by HopeDev");
                UniversalUsage.get().sendMessage(commandSender, "§9https://github.com/Hopefuls/HopeCommander/");

            }
        };
    }

}
