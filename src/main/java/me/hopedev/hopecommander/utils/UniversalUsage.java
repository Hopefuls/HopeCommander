package me.hopedev.hopecommander.utils;

public abstract class UniversalUsage {
    /*
    Made by Aurel (Hope) on 27/10/2020
     */
    public static UniversalUsage universalUsage;


    public abstract void sendMessage(Object CommandSender, String message);

    public abstract void setup();

    public abstract UsageType getUsageType();

    public abstract Object getPluginInstance();





    public enum UsageType{
        BUNGEE,
        SPIGOT
    }

    public static UniversalUsage get() {
        return universalUsage;
    }
}
