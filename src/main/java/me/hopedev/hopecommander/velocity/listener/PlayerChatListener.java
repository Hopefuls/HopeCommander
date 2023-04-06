package me.hopedev.hopecommander.velocity.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.ConsoleCommandSource;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.messages.ChannelIdentifier;
import org.slf4j.Logger;

import java.util.List;

public class PlayerChatListener {

    private final ChannelIdentifier channelIdentifier;
    private final List<String> ip;
    private final ConsoleCommandSource consoleCommandSource;
    private final CommandManager manager;
    private final Logger logger;

    public PlayerChatListener(ChannelIdentifier channelIdentifier, List<String> ip, ProxyServer proxyServer, Logger logger) {
        this.channelIdentifier = channelIdentifier;
        this.ip = ip;
        this.consoleCommandSource = proxyServer.getConsoleCommandSource();
        this.manager = proxyServer.getCommandManager();
        this.logger = logger;
    }

    @Subscribe
    public void onMessageReceive(PluginMessageEvent event) {
        if (!event.getIdentifier().equals(channelIdentifier)) return;

        ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
        String commandToRun = in.readUTF();

        String hostAddress = ((ServerConnection) event.getSource()).getServerInfo().getAddress().getHostString();
        String port = String.valueOf(((ServerConnection) event.getSource()).getServerInfo().getAddress().getPort());
        logger.info("Command received from " + hostAddress + ":" + port + " » " + commandToRun);

        if (!ip.contains(hostAddress)) {
            logger.warn("Request IP " + hostAddress + " IS NOT ON whitelisted-ips. Not Executing command!");
            return;
        }
        logger.info("Executing command...");

        manager.executeAsync(consoleCommandSource, commandToRun);

    }

}
