package me.hopedev.hopecommander.Spigot;

import me.hopedev.hopecommander.universal.UNI_onStartup;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotMain extends JavaPlugin {
    @Override
    public void onEnable() {
        new UNI_onStartup(UNI_onStartup.BACKEND.SUBSERVER, this);
    }
}
