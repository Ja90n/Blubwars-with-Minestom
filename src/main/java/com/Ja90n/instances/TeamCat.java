package com.Ja90n.instances;

import com.Ja90n.Blubwars;
import com.Ja90n.enums.TeamType;
import com.Ja90n.runnables.CatMovementRunnable;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.metadata.animal.tameable.CatMeta;

public class TeamCat {

    private Team team;
    private CatMovementRunnable catMovementRunnable;
    private EntityCreature cat;

    public TeamCat(Team team) {
        this.team = team;
    }

    public void spawnCat() {
        summonCat();
        catMovementRunnable = new CatMovementRunnable(cat);
    }

    public void respawnCat() {
        summonCat();
        catMovementRunnable.setEntityCreature(cat);
    }

    public void summonCat() {
        cat = new EntityCreature(EntityType.CAT);
        Player player = MinecraftServer.getConnectionManager().getPlayer(team.getPlayers().get(0));

        if (player == null) return;
        cat.setInstance(player.getInstance(), Blubwars.getConfigManager().getTeamSpawn(team.getTeamType()));

        CatMeta catMeta = (CatMeta) cat.getEntityMeta();
        catMeta.setCollarColor(team.getTeamType().getCollarColor());
    }

    public void stop() {
        catMovementRunnable.stop();
    }

    public EntityCreature getCat() {
        return cat;
    }
}
