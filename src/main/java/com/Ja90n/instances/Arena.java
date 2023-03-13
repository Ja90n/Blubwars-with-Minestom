package com.Ja90n.instances;

import com.Ja90n.enums.GameState;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Chunk;

import java.util.ArrayList;

public class Arena {

    /*
    An arena is the same thing is a map in the game. Its main purpose is that it manages the things outside the
    game i.e. the state the game is in, if people can join, the resetting of the minecraft map etc.
     */

    private final int arenaId;
    private final String arenaName;
    private  World world;

    private final ArrayList<Player> players;

    private GameState gameState;

    private Game game;

    public Arena(int arenaId, String arenaName, World world) {
        this.arenaId = arenaId;
        this.arenaName = arenaName;
        this.world = world;

        gameState = GameState.RECRUITING;
        players = new ArrayList<>();

        game = new Game(world.getInstance());
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        //player.teleport()

        game.addPlayer(player);
    }

    public void resetArena() {
        for (Chunk chunk : world.getInstance().getChunks()){
            world.getInstance();
        }
    }

    public World getWorld() {
        return world;
    }
}
