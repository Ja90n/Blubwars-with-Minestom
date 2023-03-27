package com.Ja90n.runnables;

import com.Ja90n.enums.TeamType;
import com.Ja90n.instances.Arena;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ResetCountdown {

    private Arena arena;

    public ResetCountdown(Arena arena) {
        this.arena = arena;
    }

    public void won(TeamType teamType) {
        arena.sendTitle(teamType.getDisplay().append(Component.text(" has won the game!")),Component.text("Thank you for playing!"));
        end();
    }

    public void end() {
        CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS).execute(() -> {
            for (Player target : MinecraftServer.getConnectionManager().getOnlinePlayers()){
                target.kick(Component.text("Game has closed", NamedTextColor.RED));
            }
            CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS)
                    .execute(MinecraftServer::stopCleanly);
        });
    }

}
