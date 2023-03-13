package com.Ja90n.instances;

import net.minestom.server.coordinate.Pos;

public class ArenaConfig {

    private int requiredPlayers;
    private int countdownSeconds;


    private Pos lobbyLocation;
    private Pos redTeamSpawn;
    private Pos greenTeamSpawn;
    private Pos blueTeamSpawn;
    private Pos yellowTeamSpawn;

    public void setDefault() {
        requiredPlayers = 3;
        countdownSeconds = 20;
        lobbyLocation = new Pos(0.5,103,0.5);
        redTeamSpawn = new Pos(0.5,63,-70.5, 0, 0);
        blueTeamSpawn = new Pos(70.5,63,0.5,90,0);
        greenTeamSpawn = new Pos(0.5, 63, 70.5,-180,0);
        yellowTeamSpawn = new Pos(-70.5,63,0.5,-90,0);
    }

    public int getCountdownSeconds() {
        return countdownSeconds;
    }

    public int getRequiredPlayers() {
        return requiredPlayers;
    }

    public Pos getYellowTeamSpawn() {
        return yellowTeamSpawn;
    }

    public Pos getRedTeamSpawn() {
        return redTeamSpawn;
    }

    public Pos getBlueTeamSpawn() {
        return blueTeamSpawn;
    }

    public Pos getGreenTeamSpawn() {
        return greenTeamSpawn;
    }

    public Pos getLobbyLocation() {
        return lobbyLocation;
    }
}
