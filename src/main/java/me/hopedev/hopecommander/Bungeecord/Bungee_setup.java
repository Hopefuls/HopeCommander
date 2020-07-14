package me.hopedev.hopecommander.Bungeecord;

import me.hopedev.hopecommander.universal.UNIVERSAL;
import me.hopedev.hopecommander.universal.UNI_onStartup;

public class Bungee_setup {
    public static void setup() {
        BungeeMain bungeeMain = (BungeeMain) new UNIVERSAL(UNI_onStartup.BACKEND.PROXY).getPlugin();
        bungeeMain.getProxy().getPluginManager().registerCommand(bungeeMain, new BungeeTestCommand("testcommand123"));
        bungeeMain.getProxy().getPluginManager().registerListener(bungeeMain, new PluginMSGListener());
        bungeeMain.getProxy().registerChannel("hope:hopecommandersender");
    }
}
