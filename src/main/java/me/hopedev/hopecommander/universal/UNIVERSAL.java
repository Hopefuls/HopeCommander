package me.hopedev.hopecommander.universal;


import com.sun.istack.internal.NotNull;

public class UNIVERSAL {
    private static UNI_onStartup.BACKEND selbackend;

    public UNIVERSAL(@NotNull UNI_onStartup.BACKEND backend) {
        selbackend = backend;
    }

    public static Object getPlugin() {

        switch (selbackend) {
            case PROXY:
                return UNI_onStartup.bungeeMain;

            case SUBSERVER:
                return UNI_onStartup.spigotMain;

        }
        return null;
    }


}
