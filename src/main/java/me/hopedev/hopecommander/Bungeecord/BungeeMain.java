package me.hopedev.hopecommander.Bungeecord;

import me.hopedev.hopecommander.universal.UNI_onStartup;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeMain extends Plugin {
    @Override
    public void onEnable() {
        new UNI_onStartup(UNI_onStartup.BACKEND.PROXY, this);
    }
}
