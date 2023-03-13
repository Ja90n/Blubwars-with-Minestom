package com.Ja90n.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Restart extends Command {

    public Restart() {
        super("restart");

        setDefaultExecutor((sender, context) -> {
            if (sender instanceof Player player){
                if (player.getPermissionLevel() >= 4) {
                    Audiences.players().sendMessage(Component.text("Server is restarting!", NamedTextColor.RED));
                    for (Player target : MinecraftServer.getConnectionManager().getOnlinePlayers()){
                        target.kick(Component.text("Server is restarting", NamedTextColor.RED));
                    }
                    CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS)
                            .execute(MinecraftServer::stopCleanly);
                }
            }
        });
    }
}
