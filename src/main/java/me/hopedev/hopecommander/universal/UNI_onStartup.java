package me.hopedev.hopecommander.universal;

import me.hopedev.hopecommander.Bungeecord.BungeeMain;
import me.hopedev.hopecommander.Bungeecord.Bungee_setup;
import me.hopedev.hopecommander.Spigot.SpigotMain;
import me.hopedev.hopecommander.Spigot.Spigot_setup;

public class UNI_onStartup {
    public static BACKEND backendresult;
    public static BungeeMain bungeeMain;
    public static SpigotMain spigotMain;

    public UNI_onStartup(BACKEND backend, Object Plugin) {
        switch (backend) {
            case PROXY:
                System.out.println("Proxy detected, using as receiver");
                System.out.println("Thank you for using HopeCommander by Hope#5050!");
                backendresult = BACKEND.PROXY;
                bungeeMain = (BungeeMain) Plugin;
                setup(BACKEND.PROXY);

                break;

            case SUBSERVER:
                System.out.println("Subserver detected, using as sender");
                System.out.println("Thank you for using HopeCommander by Hope#5050!");
                backendresult = BACKEND.SUBSERVER;
                spigotMain = (SpigotMain) Plugin;
                setup(BACKEND.SUBSERVER);
                break;

            default:
                System.err.println("Error occured at onStartup");
                break;

        }

    }

    public static void setup(BACKEND backend) {
        switch (backend) {
            case PROXY:
                Bungee_setup.setup();
                break;

            case SUBSERVER:
                Spigot_setup.setup();
                break;
        }
    }

    public enum BACKEND {
        PROXY,
        SUBSERVER
    }
}
