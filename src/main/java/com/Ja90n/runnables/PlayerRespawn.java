package com.Ja90n.runnables;

import com.Ja90n.Blubwars;
import com.Ja90n.enums.TeamType;
import com.Ja90n.instances.Arena;
import com.Ja90n.instances.Game;
import com.Ja90n.instances.Team;
import com.Ja90n.managers.TeamManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.timer.Task;
import net.minestom.server.timer.TaskSchedule;

public class PlayerRespawn {

    private final Player player;
    private int timesRun;
    private Task task;
    private Arena arena;
    private TeamManager teamManager;
    private Team team;

    public PlayerRespawn(Player player, Arena arena) {
        this.player = player;
        this.arena = arena;
        teamManager = arena.getGame().getTeamManager();
        team = teamManager.getTeam(player);

        start();
    }

    public void start() {
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(Blubwars.getConfigManager().getLobbyLocation());

        if (!team.isCatAlive()) {
            team.removePlayer(player);
            teamManager.addPlayer(player, TeamType.SPECTATOR);

            player.showTitle(Title.title(Component.text("You have been eliminated!", NamedTextColor.RED),
                    Component.text("You are now a spectator!")));
            arena.sendMessage(player.getName().append(Component.text(" has died permanently!")));

            checkLastTeamAlive();
            return;
        }

        timesRun = 7;
        task = MinecraftServer.getSchedulerManager().scheduleTask(() -> {

            if (timesRun <= 0) {
                respawnPlayer();
                task.cancel();
                player.showTitle(Title.title(Component.text(" "),Component.text(" ")));
                return;
            }

            player.showTitle(Title.title(
                    Component.text("You are respawning in ",NamedTextColor.BLUE).append(Component.text(timesRun,NamedTextColor.WHITE)),
                    Component.text(" ")));

            timesRun--;
        }, TaskSchedule.tick(1),TaskSchedule.tick(20));
    }

    private void respawnPlayer() {
        if (team.isCatAlive()) {
            player.setGameMode(GameMode.SURVIVAL);
            arena.getGame().getPlayerManager().setInventory(player,team.getTeamType());

            if (arena.getGame().getPlayerManager().getPreference(player) == 0) {
                player.teleport(Blubwars.getConfigManager().getTeamSpawn(team.getTeamType()));
            } else {
                player.teleport(teamManager.getTeam(player).getTeamCat().getCat().getPosition());
            }
        }

    }

    private boolean checkLastTeamAlive() {
        int aliveTeams = 0;
        for (Team team1 : teamManager.getTeams()) {
            if (team1.getPlayerAmount() > 0) {
                aliveTeams++;
            }
        }

        if (aliveTeams <= 1) {
            for (Team team1 : teamManager.getTeams()) {
                if (team1.getPlayerAmount() > 0) {
                    arena.getGame().won(team1.getTeamType());
                }
            }
            arena.getGame().end();
            return true;
        }
        return false;
    }


}
