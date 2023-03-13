package com.Ja90n;

import com.Ja90n.command.Gamemode;
import com.Ja90n.command.Restart;
import com.Ja90n.command.Test;
import com.Ja90n.events.EntityAttack;
import com.Ja90n.events.ItemDrop;
import com.Ja90n.events.PlayerDeath;
import com.Ja90n.events.PlayerJoin;
import com.Ja90n.instances.Arena;
import com.Ja90n.instances.World;
import com.Ja90n.managers.ConfigManager;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.extras.velocity.VelocityProxy;
import net.minestom.server.message.Messenger;
import net.minestom.server.permission.PermissionHandler;

import java.io.IOException;


public class Blubwars {

    private static ComponentLogger logger;
    private static ConfigManager configManager;
    private static World world;
    private static Arena arena;

    public static void main(String[] args) throws IOException {
        MinecraftServer minecraftServer = MinecraftServer.init();
        logger = MinecraftServer.LOGGER;

        configManager = new ConfigManager(logger);

        world = new World();
        arena = new Arena(configManager.getPort(),"Temple",world);

        initiateEvents(MinecraftServer.getGlobalEventHandler());
        initiateCommands(MinecraftServer.getCommandManager());

        if (configManager.getVelocity()){
            VelocityProxy.enable(configManager.getForwardingSecret());
        }

        minecraftServer.start(configManager.getHost(),configManager.getPort());
        logger.info("Server stated!");
    }

    private static void initiateEvents(GlobalEventHandler globalEventHandler){
        new EntityAttack(globalEventHandler);
        new ItemDrop(globalEventHandler);
        new PlayerJoin(globalEventHandler, world.getInstance());
        new PlayerDeath(globalEventHandler);

        logger.info("Events initiated!");
    }

    private static void initiateCommands(CommandManager commandManager){
        commandManager.register(new Test(arena));
        commandManager.register(new Restart());
        commandManager.register(new Gamemode());
        commandManager.setUnknownCommandCallback((sender, c) -> sender.sendMessage("Command not found."));

        logger.info("Commands initiated!");
    }

}