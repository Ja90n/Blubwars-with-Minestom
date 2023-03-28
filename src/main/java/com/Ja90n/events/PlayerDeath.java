package com.Ja90n.events;

import com.Ja90n.Blubwars;
import com.Ja90n.enums.GameState;
import com.Ja90n.enums.TeamType;
import com.Ja90n.instances.Arena;
import com.Ja90n.instances.Team;
import com.Ja90n.managers.ConfigManager;
import com.Ja90n.managers.TeamManager;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerDeathEvent;
import net.minestom.server.event.player.PlayerRespawnEvent;

public class PlayerDeath {

    public PlayerDeath(GlobalEventHandler globalEventHandler, Arena arena) {

        globalEventHandler.addListener(PlayerDeathEvent.class, event -> {
            event.setDeathText(Component.text(""));
            event.getPlayer().getInventory().clear();
            event.getPlayer().respawn();
        });

        ConfigManager configManager = Blubwars.getConfigManager();

        globalEventHandler.addListener(PlayerRespawnEvent.class, event -> {
            if (!arena.getGameState().equals(GameState.LIVE)){
                event.setRespawnPosition(configManager.getLobbyLocation());
            } else {
                Player player = event.getPlayer();
                TeamManager teamManager = arena.getGame().getTeamManager();
                Team team = teamManager.getTeam(player);

                if (team.isCatAlive()) {
                    event.setRespawnPosition(configManager.getTeamSpawn(team.getTeamType()));
                    arena.getGame().getPlayerManager().setInventory(player,team.getTeamType());
                    return;
                }

                team.removePlayer(player);
                teamManager.addPlayer(player, TeamType.SPECTATOR);
                player.setGameMode(GameMode.SPECTATOR);
                player.teleport(configManager.getTeamSpawn(TeamType.SPECTATOR));
                arena.sendMessage(player.getName().append(Component.text(" has died permanently!")));

                int aliveTeams = 0;
                for (Team team1 : teamManager.getTeams()) {
                    if (team1.getPlayerAmount() > 0) {
                        aliveTeams++;
                    }
                }

                System.out.println(aliveTeams);

                if (aliveTeams <= 1) {
                    for (Team team1 : teamManager.getTeams()) {
                        if (team1.getPlayerAmount() > 0) {
                            arena.getGame().won(team1.getTeamType());
                        }
                    }
                    arena.getGame().end();
                }
            }
        });
    }
}
