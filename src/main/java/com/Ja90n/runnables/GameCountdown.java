package com.Ja90n.runnables;

import com.Ja90n.instances.Arena;
import net.minestom.server.MinecraftServer;
import net.minestom.server.timer.SchedulerManager;
import net.minestom.server.timer.Task;
import net.minestom.server.timer.TaskSchedule;

public class GameCountdown {

    private Arena arena;
    private int countdownSeconds;
    private Task task;

    public GameCountdown(Arena arena, int countdownSeconds){
        this.arena = arena;
        this.countdownSeconds = countdownSeconds;
    }

    public void start(){
        // Arena state

        task = MinecraftServer.getSchedulerManager().scheduleTask(() -> {
            if (countdownSeconds == 0){
                task.cancel();
                // start arena
                return;
            }

            if (countdownSeconds <= 10 || countdownSeconds % 15 == 0){
                //arena.sendMessage(ChatColor.BLUE + "Game will start in " + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s") + ".");
            }

            //arena.sendTitle(ChatColor.GRAY + "Game is starting in ", ChatColor.LIGHT_PURPLE.toString() + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s") + ".");

            countdownSeconds--;
        },TaskSchedule.tick(1), TaskSchedule.tick(20));
    }
}
