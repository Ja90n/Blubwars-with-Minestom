package com.Ja90n.instances;

import com.Ja90n.Blubwars;
import com.Ja90n.runnables.CatMovementRunnable;
import com.Ja90n.runnables.CatRespawn;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.metadata.animal.tameable.CatMeta;
import net.minestom.server.instance.Instance;

public class TeamCat {

    private final Team team;
    private final Instance instance;
    private CatMovementRunnable catMovementRunnable;
    private EntityCreature cat;
    private int lives;

    public TeamCat(Team team) {
        this.team = team;
        instance = Blubwars.getWorld().getInstance();
    }

    public void spawnCat() {
        lives = 9;
        summonCat();
        catMovementRunnable = new CatMovementRunnable(cat);
        catMovementRunnable.start();
    }

    public void died() {
        new CatRespawn(this);
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

    public void removeLife() {
        lives--;
    }

    public int getLives() {
        return lives;
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
