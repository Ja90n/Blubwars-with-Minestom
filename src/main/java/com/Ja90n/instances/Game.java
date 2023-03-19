package com.Ja90n.instances;

import com.Ja90n.enums.GameState;
import com.Ja90n.enums.TeamType;
import com.Ja90n.managers.BlockManager;
import com.Ja90n.managers.ConfigManager;
import com.Ja90n.managers.TeamManager;
import com.Ja90n.runnables.ResetCountdown;
import net.kyori.adventure.text.Component;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

public class Game {

    /*
    The game class is where the actual game data is being held. I.e. the teams and the droppers etc.
    */

    private final Instance world;
    private final ConfigManager configManager;
    private final BlockManager blockManager;
    private final TeamManager teamManager;
    private final Arena arena;

    public Game(Instance world, ConfigManager configManager, Arena arena){
        blockManager = new BlockManager();
        teamManager = new TeamManager();
        this.arena = arena;
        this.world = world;
        this.configManager = configManager;
    }

    public void start() {
        Cuboid cuboid = new Cuboid(new Pos(12,110,12),new Pos(-12,100,-12),world);
        for (Iterator<Pos> it = cuboid.posIterator(); it.hasNext(); ) {
            Pos block = it.next();
            world.setBlock(block, Block.AIR);
        }

        for (UUID uuid : arena.getPlayers()){
            Player player = MinecraftServer.getConnectionManager().getPlayer(uuid);
            player.teleport(configManager.getTeamSpawn(teamManager.getTeam(player)));
        }

        arena.setGameState(GameState.LIVE);
    }

    public void end() {
        new ResetCountdown();
    }

    public void addPlayer(Player player){

        teamManager.addPlayer(player, teamManager.getLowerstTeam());

        player.sendMessage(Component.text("You have been added to the " + teamManager.getTeam(player).getDisplay().content() + " team!"));
        player.sendMessage("You have joined the game!");
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public BlockManager getBlockManager() {
        return blockManager;
    }
}
