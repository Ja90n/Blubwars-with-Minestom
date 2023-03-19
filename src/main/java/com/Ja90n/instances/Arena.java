package com.Ja90n.instances;

import com.Ja90n.enums.GameState;
import com.Ja90n.managers.ConfigManager;
import com.Ja90n.runnables.GameCountdown;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.title.Title;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;

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

        game = new Game(world.getInstance(), configManager, this);
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

    public void sendTitle(TextComponent title1, TextComponent title2){
        for (UUID uuid : players){
            Player player = MinecraftServer.getConnectionManager().getPlayer(uuid);
            Title title = Title.title(title1,title2);
            player.showTitle(title);
        }
    }

    public void sendMessage(TextComponent textComponent){
        for (UUID uuid : players){
            Player player = MinecraftServer.getConnectionManager().getPlayer(uuid);
            player.sendMessage(textComponent);
        }
    }

    public void start() {
        game.start();
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void resetArena() {

    }

    public GameState getGameState() {
        return gameState;
    }

    public World getWorld() {
        return world;
    }

    public Game getGame() {
        return game;
    }

    public ArrayList<UUID> getPlayers() {
        return players;
    }
}
