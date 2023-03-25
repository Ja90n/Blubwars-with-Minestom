package com.Ja90n.instances;

import com.Ja90n.Blubwars;
import com.Ja90n.enums.TeamType;
import com.Ja90n.runnables.CatMovementRunnable;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.metadata.animal.tameable.CatMeta;
import net.minestom.server.instance.Instance;

public class TeamCat {

    private Team team;
    private CatMovementRunnable catMovementRunnable;
    private EntityCreature cat;
    private Instance instance;

    public TeamCat(Team team) {
        this.team = team;
        instance = Blubwars.getWorld().getInstance();
    }

    public void spawnCat() {
        summonCat();
        catMovementRunnable = new CatMovementRunnable(cat);
        catMovementRunnable.start();
    }

    public void respawnCat() {
        summonCat();
        catMovementRunnable.setEntityCreature(cat);
    }

    public void summonCat() {
        cat = new EntityCreature(EntityType.CAT);

        cat.setInstance(instance, Blubwars.getConfigManager().getTeamSpawn(team.getTeamType()));

        CatMeta catMeta = (CatMeta) cat.getEntityMeta();
        catMeta.setCollarColor(team.getTeamType().getCollarColor());
    }

    public void stop() {
        catMovementRunnable.stop();
    }

    public EntityCreature getCat() {
        return cat;
    }

    public void setTarget(Entity target) {
        catMovementRunnable.setTarget(target);
    }

    public Team getTeam() {
        return team;
    }

    public Entity getTarget() {
        return catMovementRunnable.getTarget();
    }
}
