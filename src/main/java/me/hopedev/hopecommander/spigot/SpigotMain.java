package me.hopedev.hopecommander.spigot;

import org.bukkit.plugin.java.JavaPlugin;

public class SpigotMain extends JavaPlugin {
    /*
    Made by Aurel (Hope) on 27/10/2020
     */
    @Override
    public void onEnable() {
        new Spigot(this).setup();
    }


}

