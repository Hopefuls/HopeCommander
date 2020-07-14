package me.hopedev.hopecommander.universal;

import me.hopedev.hopecommander.Bungeecord.BungeeMain;
import me.hopedev.hopecommander.Bungeecord.BungeeTestCommand;
import me.hopedev.hopecommander.Spigot.SpigotMain;

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
                BungeeMain bungeeMain = (BungeeMain) new UNIVERSAL(BACKEND.PROXY).getPlugin();
                bungeeMain.getProxy().getPluginManager().registerCommand(bungeeMain, new BungeeTestCommand("testcommand123"));
                //  bungeeMain.getProxy().getPluginManager().registerListener(bungeeMain, new PluginMSGListener());
                bungeeMain.getProxy().registerChannel("hope:hopecommandersender");
                break;

            case SUBSERVER:

                break;
        }
    }

    public enum BACKEND {
        PROXY,
        SUBSERVER
    }
}
