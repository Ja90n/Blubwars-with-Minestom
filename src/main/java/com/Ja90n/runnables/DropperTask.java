package com.Ja90n.runnables;

import com.Ja90n.instances.Dropper;
import com.Ja90n.instances.Game;
import com.Ja90n.managers.DropperManager;
import net.minestom.server.MinecraftServer;
import net.minestom.server.timer.Task;
import net.minestom.server.timer.TaskSchedule;

public class DropperTask {

    private Task task;
    private DropperManager dropperManager;

    public DropperTask(DropperManager dropperManager) {
        this.dropperManager = dropperManager;
    }

    public void start() {
        task = MinecraftServer.getSchedulerManager().scheduleTask(() -> {
            for (Dropper dropper : dropperManager.getDroppers()){
                dropper.tick();
            }
        }, TaskSchedule.tick(20),TaskSchedule.tick(20));
    }
}
