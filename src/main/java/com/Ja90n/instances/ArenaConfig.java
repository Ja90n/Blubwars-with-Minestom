package com.Ja90n.instances;

import com.Ja90n.enums.Cost;
import net.minestom.server.coordinate.Pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArenaConfig {

    private int requiredPlayers;
    private int countdownSeconds;

    private Pos lobbyLocation;
    private Pos respawnLocation;

    private Pos redTeamSpawn;
    private Pos greenTeamSpawn;
    private Pos blueTeamSpawn;
    private Pos yellowTeamSpawn;

    private List<Pos> posList;
    private List<Cost> costs;

    private List<Pos> shopLocations;

    public void setDefault() {
        requiredPlayers = 3;
        countdownSeconds = 20;

        posList = new ArrayList<>();
        costs = new ArrayList<>();

        posList.add(new Pos(0.5,63.5,-73.5));
        posList.add(new Pos(0.5,63.5,74.5));
        posList.add(new Pos(-73.5,63.5,0.5));
        posList.add(new Pos(74.5,63.5,0.5));

        costs.add(Cost.COD);
        costs.add(Cost.COD);
        costs.add(Cost.COD);
        costs.add(Cost.COD);

        posList.add(new Pos(-73.5,63.5,0.5));
        posList.add(new Pos(74.5,63.5,0.5));
        posList.add(new Pos(0.5,63.5,74.5));
        posList.add(new Pos(0.5,63.5,-73.5));

        costs.add(Cost.SALMON);
        costs.add(Cost.SALMON);
        costs.add(Cost.SALMON);
        costs.add(Cost.SALMON);

        posList.add(new Pos(42.5,60.5,-41.5));
        posList.add(new Pos(42.5,60.5,41.5));
        posList.add(new Pos(-42.5,60.5,-41.5));
        posList.add(new Pos(-42.5,60.5,41.5));

        costs.add(Cost.TROPICAL_FISH);
        costs.add(Cost.TROPICAL_FISH);
        costs.add(Cost.TROPICAL_FISH);
        costs.add(Cost.TROPICAL_FISH);

        posList.add(new Pos(9.5,61.5,-8.5));
        posList.add(new Pos(-8.5,61.5,9.5));

        costs.add(Cost.PUFFERFISH);
        costs.add(Cost.PUFFERFISH);

        lobbyLocation = new Pos(0.5,103,0.5);
        respawnLocation = new Pos(0.5,80,0.5);

        redTeamSpawn = new Pos(0.5,63,-70.5, 0, 0);
        blueTeamSpawn = new Pos(70.5,63,0.5,90,0);
        greenTeamSpawn = new Pos(0.5, 63, 70.5,-180,0);
        yellowTeamSpawn = new Pos(-70.5,63,0.5,-90,0);

        shopLocations = new ArrayList<>();
        shopLocations.add(new Pos(4.5,63,72,90,0));
        shopLocations.add(new Pos(-71,63,4.5,180,0));
        shopLocations.add(new Pos(-3.5,63,-71,-90,0));
        shopLocations.add(new Pos(72,63,-3.5,0,0));
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

    public List<Pos> getShopLocations() {
        return shopLocations;
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

    public List<Pos> getDropperLocations() {
        return posList;
    }

    public List<Cost> getTypes() {
        return costs;
    }

    public Pos getRespawnLocation() {
        return respawnLocation;
    }
}
