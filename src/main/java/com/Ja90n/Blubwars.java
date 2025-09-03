package com.Ja90n;


import com.Ja90n.command.Gamemode;
import com.Ja90n.command.Restart;
import com.Ja90n.command.Test;
import com.Ja90n.events.*;
import com.Ja90n.instances.Arena;
import com.Ja90n.instances.World;
import com.Ja90n.managers.ConfigManager;
import io.github.togar2.pvp.MinestomPvP;
import io.github.togar2.pvp.feature.CombatFeatureSet;
import io.github.togar2.pvp.feature.CombatFeatures;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.minestom.server.Auth;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.event.GlobalEventHandler;

import java.io.IOException;


public class Blubwars {

    private static ComponentLogger logger;
    private static ConfigManager configManager;
    private static World world;
    private static Arena arena;

    public static void main(String[] args) throws IOException {

        MinecraftServer minecraftServer = MinecraftServer.init(new Auth.Online());

        logger = MinecraftServer.LOGGER;

        configManager = new ConfigManager(logger);

        world = new World();
        arena = new Arena(configManager.getPort(),"Temple",world);

        initiateEvents(MinecraftServer.getGlobalEventHandler());
        initiateCommands(MinecraftServer.getCommandManager());

        MinestomPvP.init();

        CombatFeatureSet modernVanilla = CombatFeatures.modernVanilla();
        MinecraftServer.getGlobalEventHandler().addChild(modernVanilla.createNode());

        minecraftServer.start(configManager.getHost(),configManager.getPort());
        logger.info("Server stated!");
    }

    private static void initiateEvents(GlobalEventHandler globalEventHandler){
        new EntityAttack(globalEventHandler, arena);
        new ItemEvents(globalEventHandler);
        new PlayerJoin(globalEventHandler, world.getInstance(), arena);
        new PlayerDeath(globalEventHandler, arena);
        new PlayerBlockEvents(globalEventHandler,arena);
        new InteractEntity(globalEventHandler,arena);
        new EntityDeath(globalEventHandler,arena);
        new PlayerInteract(globalEventHandler,arena);

        logger.info("Events initiated!");
    }

    private static void initiateCommands(CommandManager commandManager){
        commandManager.register(new Test(arena));
        commandManager.register(new Restart());
        commandManager.register(new Gamemode());
        commandManager.setUnknownCommandCallback((sender, c) -> sender.sendMessage(Component.text("Command not found.", NamedTextColor.RED)));

        logger.info("Commands initiated!");
    }

    public static World getWorld() {
        return world;
    }

    public static Arena getArena() {
        return arena;
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }
}