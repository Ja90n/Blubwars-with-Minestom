package com.Ja90n.runnables;

import com.Ja90n.Blubwars;
import com.Ja90n.instances.TeamCat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.metadata.other.ArmorStandMeta;
import net.minestom.server.timer.Task;
import net.minestom.server.timer.TaskSchedule;

public class CatRespawn {

    private final int RESPAWNDELAYSECONDS = 5;

    private TeamCat teamCat;
    private Entity entity;
    private ArmorStandMeta armorStandMeta;
    private Task task;
    private int timesRun;

    public CatRespawn(TeamCat teamCat) {
        this.teamCat = teamCat;
        if (!teamCat.getTeam().isCatAlive()) {
            return;
        }
        entity = new Entity(EntityType.ARMOR_STAND);
        armorStandMeta = (ArmorStandMeta) entity.getEntityMeta();
        armorStandMeta.setHasNoBasePlate(true);
        armorStandMeta.setInvisible(true);
        armorStandMeta.setCustomNameVisible(true);
        entity.setInstance(Blubwars.getWorld().getInstance(), Blubwars.getConfigManager().getTeamSpawn(teamCat.getTeam().getTeamType()));
        start();
    }

    public void start() {
        timesRun = RESPAWNDELAYSECONDS;
        task = MinecraftServer.getSchedulerManager().scheduleTask(() -> {
            if (timesRun <= 0) {
                teamCat.respawnCat();
                entity.remove();
                task.cancel();
                return;
            }

            armorStandMeta.setCustomName(teamCat.getTeam().getTeamType().getDisplay()
                    .append(Component.text("'s cat is respawning in: ")
                            .append(Component.text(timesRun, NamedTextColor.WHITE))));

            timesRun--;
        }, TaskSchedule.tick(1),TaskSchedule.tick(20));
    }
}
