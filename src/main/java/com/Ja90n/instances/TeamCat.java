package com.Ja90n.instances;

import com.Ja90n.Blubwars;
import com.Ja90n.runnables.CatMovementRunnable;
import com.Ja90n.runnables.CatRespawn;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.metadata.animal.tameable.CatMeta;
import net.minestom.server.instance.Instance;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;

public class TeamCat {

    private final int CAT_LIVES = 3;

    private final Team team;
    private final Instance instance;
    private CatMovementRunnable catMovementRunnable;
    private EntityCreature cat;
    private int lives;
    private Inventory catChest;

    public TeamCat(Team team) {
        this.team = team;
        catChest = new Inventory(InventoryType.CHEST_3_ROW, Component.text("Cat chest"));
        instance = Blubwars.getWorld().getInstance();
    }

    public Inventory getCatChest() {
        return catChest;
    }

    public void spawnCat() {
        lives = CAT_LIVES;
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
