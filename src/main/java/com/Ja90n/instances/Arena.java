package com.Ja90n.instances;

import com.Ja90n.enums.GameState;
import com.Ja90n.managers.ConfigManager;
import com.Ja90n.runnables.GameCountdown;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Chunk;

import java.util.ArrayList;
import java.util.UUID;

public class Arena {

    /*
    An arena is the same thing is a map in the game. Its main purpose is that it manages the things outside the
    game i.e. the state the game is in, if people can join, the resetting of the minecraft map etc.
     */

    private final int arenaId;
    private final String arenaName;
    private World world;
    private ConfigManager configManager;
    private GameCountdown countdown;

    private final ArrayList<UUID> players;

    private GameState gameState;

    private Game game;

    public Arena(int arenaId, String arenaName, World world, ConfigManager configManager) {
        this.arenaId = arenaId;
        this.arenaName = arenaName;
        this.world = world;
        this.configManager = configManager;

        gameState = GameState.RECRUITING;
        players = new ArrayList<>();
        countdown = new GameCountdown(this, configManager.getCountdownSeconds());

        game = new Game(world.getInstance());
    }

    public void addPlayer(Player player) {
        players.add(player.getUuid());
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.teleport(configManager.getLobbyLocation());

        if (gameState.equals(GameState.RECRUITING) && players.size() >= configManager.getRequiredPlayers()){
            countdown.start();
        }

        game.addPlayer(player);
    }

    public void start() {
        game.start();
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void resetArena() {

    }

    public World getWorld() {
        return world;
    }
}
