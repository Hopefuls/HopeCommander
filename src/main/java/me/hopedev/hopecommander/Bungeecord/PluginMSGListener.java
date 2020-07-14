package me.hopedev.hopecommander.Bungeecord;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import me.hopedev.hopecommander.universal.UNIVERSAL;
import me.hopedev.hopecommander.universal.UNI_onStartup;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PluginMSGListener implements Listener {

    @EventHandler
    public void onMessageReceive(PluginMessageEvent event) {
        if (event.getTag().equalsIgnoreCase("hope:hopecmdsend")) {
            BungeeMain bungeeMain = (BungeeMain) new UNIVERSAL(UNI_onStartup.BACKEND.PROXY).getPlugin();
            ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
            String commandToRun = in.readUTF();
            bungeeMain.getProxy().getConsole().sendMessage(new ComponentBuilder("[HopeCommander] Command received from " + event.getSender().getAddress().getAddress().getHostAddress() + ":" + event.getSender().getAddress().getPort() + "» " + commandToRun).create());
            bungeeMain.getProxy().getConsole().sendMessage(new ComponentBuilder("[HopeCommander] executing...").create());
            //send
            bungeeMain.getProxy().getPluginManager().dispatchCommand(bungeeMain.getProxy().getConsole(), commandToRun);

        }
    }
}
