package me.hopedev.hopecommander.Bungeecord;

import me.hopedev.hopecommander.universal.UNIVERSAL;
import me.hopedev.hopecommander.universal.UNI_onStartup;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PluginMSGListener implements Listener {

    @EventHandler
    public void onMessageReceive(PluginMessageEvent event) {
        if (event.getTag().equalsIgnoreCase("hope:hopecommandersender")) {
            BungeeMain bungeeMain = (BungeeMain) new UNIVERSAL(UNI_onStartup.BACKEND.PROXY).getPlugin();
            bungeeMain.getProxy().broadcast(new ComponentBuilder("§cMessage received!").create());
        }
    }
}
