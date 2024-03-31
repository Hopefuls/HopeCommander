package me.hopedev.hopecommander.bungeecord;

import net.md_5.bungee.api.plugin.Plugin;

public class BungeeMain extends Plugin {

    /*
Made by Aurel (Hope) on 27/10/2020
 */

    @Override
    public void onEnable() {
        new Bungee(this).setup();
    }
}
