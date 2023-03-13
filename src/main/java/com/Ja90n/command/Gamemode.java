package com.Ja90n.command;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;


public class Gamemode extends Command {

    public Gamemode() {
        super("gamemode", "gm");

        var numberArgument = ArgumentType.String("gamemode");

        addSyntax((sender, context) -> {
            if (sender instanceof Player player){
                if (player.getPermissionLevel() >= 4){
                    final String gamemode = context.get(numberArgument);
                    switch (gamemode) {
                        case "creative", "1" -> player.setGameMode(GameMode.CREATIVE);
                        case "survival", "0" -> player.setGameMode(GameMode.SURVIVAL);
                        case "adventure", "2" -> player.setGameMode(GameMode.ADVENTURE);
                        case "spectator", "3" -> player.setGameMode(GameMode.SPECTATOR);
                        default -> player.sendMessage("nee");
                    }
                }
            }
        }, numberArgument);
    }
}
