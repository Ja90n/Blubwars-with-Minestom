package com.Ja90n.instances;

import com.Ja90n.enums.TeamType;
import net.minestom.server.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Team {

    private final TeamType teamType;
    private final ArrayList<UUID> players;

    public Team(TeamType teamType) {
        this.teamType = teamType;
        players = new ArrayList<>();
    }

    public void addPlayer(Player player){
        players.add(player.getUuid());
    }

    public void removePlayer(Player player) {
        players.remove(player.getUuid());
    }

    public TeamType getTeamType() {
        return teamType;
    }

    public int getPlayerAmount() {
        return players.size();
    }

    public boolean containsPlayer(Player player) {
        return players.contains(player.getUuid());
    }
}
