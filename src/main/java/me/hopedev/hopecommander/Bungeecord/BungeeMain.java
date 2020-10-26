package me.hopedev.hopecommander.Bungeecord;

import net.md_5.bungee.api.plugin.Plugin;

public class BungeeMain extends Plugin {
    @Override
    public void onEnable() {
        new Bungee(this);
    }
}
