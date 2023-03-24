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
    private EntityCreature entityCreature;
    private CatMovementRunnable catMovementRunnable;

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

    public void setCat(EntityCreature cat) {
        this.entityCreature = cat;
        catMovementRunnable = new CatMovementRunnable(entityCreature, MinecraftServer.getConnectionManager().getPlayer(players.get(0)));
        CatMeta catMeta = (CatMeta) entityCreature.getEntityMeta();
        catMeta.setCollarColor(teamType.getCollarColor());
    }

    public EntityCreature getCat() {
        return entityCreature;
    }

    public int getPlayerAmount() {
        return players.size();
    }

    public boolean containsPlayer(Player player) {
        return players.contains(player.getUuid());
    }
}
