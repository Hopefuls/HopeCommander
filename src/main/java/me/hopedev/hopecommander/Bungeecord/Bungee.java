package me.hopedev.hopecommander.Bungeecord;

import me.hopedev.hopecommander.Bungeecord.Listeners.PluginMessage;
import me.hopedev.hopecommander.utils.UniversalUsage;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;

public class Bungee extends UniversalUsage {
    /*
    Made by Aurel (Hope) on 27/10/2020
     */
    private final Object pluginInstance;
    private Configuration configuration;

    public Bungee(Object pluginInstance) {
        this.pluginInstance = pluginInstance;
    }

    @Override
    public void sendMessage(Object CommandSender, String message) {
        CommandSender sender = (CommandSender) CommandSender;
        sender.sendMessage(message);
    }

    @Override
    public void setup() {
        BungeeMain instance = (BungeeMain) this.pluginInstance;
        instance.getProxy().getPluginManager().registerListener(instance, new PluginMessage());
        instance.getProxy().registerChannel("hope:hopecmdsend");
        instance.getProxy().getPluginManager().registerCommand(instance, getCommand("hopecommander"));
        UniversalUsage.universalUsage = this;
        try {
            if (!instance.getDataFolder().exists())
                instance.getDataFolder().mkdir();

            File file = new File(instance.getDataFolder(), "config.yml");


            if (!file.exists()) {
                try (InputStream in = instance.getResourceAsStream("config.yml")) {
                    Files.copy(in, file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(instance.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UniversalUsage.UsageType getUsageType() {
        return UsageType.BUNGEE;
    }

    public Configuration getConfiguration() {
        return this.configuration;
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
                UniversalUsage.get().sendMessage(commandSender, "§7Serveradmins: §7§oYou are not supposed to execute this Command in-game ;) Please reread the Documentation again");

            }
        };
    }

}
