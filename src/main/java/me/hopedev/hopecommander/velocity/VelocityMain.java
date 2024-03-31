package me.hopedev.hopecommander.velocity;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.ChannelIdentifier;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import me.hopedev.hopecommander.velocity.listener.PlayerChatListener;
import org.slf4j.Logger;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Plugin(
        id = "hopecommander",
        name = "HopeCommander",
        version = "2.0.0",
        url = "https://cringe.dev/",
        description = "The easy to use Spigot to Bungeecord/Velocity Command bridge for your Pluginss",
        authors = {"hopedev"}
)
public class VelocityMain {

    private final Path pluginPath;
    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public VelocityMain(@DataDirectory Path pluginPath, ProxyServer server, Logger logger) {
        this.pluginPath = pluginPath;
        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialize(final ProxyInitializeEvent event) {

        // Plugin directory
        if (Files.notExists(pluginPath)) {
            try {
                Files.createDirectory(pluginPath);
            } catch (IOException e) {
                logger.error("Unable to create plugin directory", e);
            }
        }

        // Config directory
        Path configPath = pluginPath.resolve("config.yml");
        if (Files.notExists(configPath)) {
            try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.yml")) {
                Files.copy(in, configPath);
                logger.info("A config.yml has been created because it did not exist.");
            } catch (IOException e) {
                logger.error("Unable to create plugin configuration", e);
            }
        } else {
            logger.info("A config.yml has not been created because it already exists.");
        }

        // Configuration
        ConfigurationNode configurationNode = registerConfig();
        // IPs
        List<String> ip = getIp(configurationNode);

        // Channel
        ChannelIdentifier channelIdentifier = MinecraftChannelIdentifier.from("hope:hopecmdsend");
        server.getChannelRegistrar().register(channelIdentifier);

        // Listener
        server.getEventManager().register(this, new PlayerChatListener(channelIdentifier, ip, server, logger));

    }

    private ConfigurationNode registerConfig(){
        // Configuration
        YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
                .path(pluginPath.resolve("config.yml"))
                .build();

        try {
            return loader.load();
        } catch (IOException e) {
            logger.error("Unable to load configuration, disabling plugin...", e);
            server.shutdown();
            return null;
        }
    }

    private List<String> getIp(ConfigurationNode node){
        try {
            return node.node("whitelisted-ips").getList(String.class);
        } catch (SerializationException e) {
            logger.error("Unable to load configuration, disabling plugin...", e);
            server.shutdown();
            return null;
        }
    }

}
