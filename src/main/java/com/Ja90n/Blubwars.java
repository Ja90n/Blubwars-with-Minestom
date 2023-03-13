package com.Ja90n;

import com.Ja90n.command.Test;
import com.Ja90n.events.EntityAttack;
import com.Ja90n.events.ItemDrop;
import com.Ja90n.events.PlayerDeath;
import com.Ja90n.events.PlayerJoin;
import com.Ja90n.instances.Arena;
import com.Ja90n.instances.World;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.event.GlobalEventHandler;

import java.util.logging.Logger;

public class Blubwars {

    private static ComponentLogger logger;
    private static World world;
    private static Arena arena;

    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();
        logger = MinecraftServer.LOGGER;

        world = new World();
        arena = new Arena(35567,"Temple",world);

        initiateEvents(MinecraftServer.getGlobalEventHandler());
        initiateCommands(MinecraftServer.getCommandManager());

        minecraftServer.start("0.0.0.0",25566);
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

        logger.info("Commands initiated!");
    }

}