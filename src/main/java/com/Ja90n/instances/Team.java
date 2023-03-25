package com.Ja90n.instances;

import com.Ja90n.enums.TeamType;
import com.Ja90n.runnables.CatMovementRunnable;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.metadata.animal.tameable.CatMeta;

import java.util.ArrayList;
import java.util.UUID;

public class Team {

    private final TeamType teamType;
    private final ArrayList<UUID> players;
    private final TeamCat teamCat;

    public Team(TeamType teamType) {
        this.teamType = teamType;
        teamCat = new TeamCat(this);
        players = new ArrayList<>();
    }

    public void spawnCat() {
        teamCat.spawnCat();
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

    public ArrayList<UUID> getPlayers() {
        return players;
    }

    public TeamCat getTeamCat() {
        return teamCat;
    }
}
