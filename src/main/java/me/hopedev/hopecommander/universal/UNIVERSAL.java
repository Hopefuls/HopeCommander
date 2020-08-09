package me.hopedev.hopecommander.universal;



public class UNIVERSAL {
    private static UNI_onStartup.BACKEND selbackend;

    public UNIVERSAL(UNI_onStartup.BACKEND backend) {
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
