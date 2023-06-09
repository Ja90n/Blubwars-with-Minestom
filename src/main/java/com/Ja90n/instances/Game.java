package com.Ja90n.instances;

import com.Ja90n.Blubwars;
import com.Ja90n.enums.GameState;
import com.Ja90n.enums.TeamType;
import com.Ja90n.managers.*;
import com.Ja90n.runnables.ResetCountdown;
import net.kyori.adventure.text.Component;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.*;
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
    private final DropperManager dropperManager;
    private final VillagerManager villagerManager;
    private final PlayerManager playerManager;
    private final Arena arena;

    public Game(Instance world, ConfigManager configManager, Arena arena){
        blockManager = new BlockManager();
        teamManager = new TeamManager();
        dropperManager = new DropperManager();
        villagerManager = new VillagerManager();
        playerManager = new PlayerManager();
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
            player.setGameMode(GameMode.SURVIVAL);
            player.closeInventory();
            player.clearEffects();
            player.teleport(configManager.getTeamSpawn(teamManager.getTeam(player).getTeamType()));
            playerManager.setInventory(player,teamManager.getTeam(player).getTeamType());
        }

        teamManager.spawnCats();
        dropperManager.start();
        villagerManager.setupShopVillagers();

        arena.setGameState(GameState.LIVE);
    }


    public void end() {
        new ResetCountdown(arena).end();
    }

    public void won(TeamType team) {
        new ResetCountdown(arena).won(team);
    }

    public void addPlayer(Player player){
        if (arena.getGameState().equals(GameState.COUNTDOWN) || arena.getGameState().equals(GameState.RECRUITING)) {
            teamManager.addPlayer(player, teamManager.getLowerstTeam());

            player.sendMessage(Component.text("You have been added to the ")
                    .append(teamManager.getTeam(player).getTeamType().getDisplay())
                    .append(Component.text(" team!")));
            player.sendMessage("You have joined the game!");
        } else if (arena.getGameState().equals(GameState.LIVE)){
            teamManager.addPlayer(player, TeamType.SPECTATOR);
            player.sendMessage("You have joined the game while it was active so you are now a spectator!");
        } else {
            player.kick("Game was ending");
        }
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public PlayerManager getPlayerManager() { return playerManager; }

    public BlockManager getBlockManager() {
        return blockManager;
    }

}
