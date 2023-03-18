package com.Ja90n.runnables;

import com.Ja90n.enums.GameState;
import com.Ja90n.instances.Arena;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
        arena.setGameState(GameState.RECRUITING);

        task = MinecraftServer.getSchedulerManager().scheduleTask(() -> {
            if (countdownSeconds == 0){
                arena.start();
                task.cancel();
                return;
            }

            if (countdownSeconds <= 10 || countdownSeconds % 15 == 0){
                arena.sendMessage(Component.text("Game is starting in ", NamedTextColor.WHITE).append(Component.text(countdownSeconds, NamedTextColor.LIGHT_PURPLE)));
            }

            //arena.sendTitle(ChatColor.GRAY + "Game is starting in ", ChatColor.LIGHT_PURPLE.toString() + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s") + ".");

            countdownSeconds--;

        },TaskSchedule.tick(1), TaskSchedule.tick(20));
    }
}
