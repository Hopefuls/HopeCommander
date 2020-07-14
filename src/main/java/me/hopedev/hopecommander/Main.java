package me.hopedev.hopecommander;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("HopeCommander enabled! Thanks for using :)");
    }

    @Override
    public void onDisable() {
        System.out.println("HopeCommander disabled! Goodbye.");
    }
}
