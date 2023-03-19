package com.Ja90n.managers;

import com.Ja90n.enums.TeamType;
import com.Ja90n.instances.Team;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.Player;

import java.util.ArrayList;

public class TeamManager {

    private final ArrayList<Team> teams;

    public TeamManager() {
        teams = new ArrayList<>();
        for (TeamType teamType1 : TeamType.values()){
            teams.add(new Team(teamType1));
        }
    }

    public TeamType getTeam(Player player) {
        for (Team team : teams) {
            if (team.containsPlayer(player)) {
                return team.getTeamType();
            }
        }
        return null;
    }

    public void addPlayer(Player player, TeamType teamType){
        for (Team team : teams) {
            if (team.getTeamType().equals(teamType)){
                team.addPlayer(player);
                return;
            }
        }
        player.sendMessage(Component.text("Team you were trying to join does not exist! This should not happen." +
                " If you see this please notify Ja90n#5855", NamedTextColor.RED));
    }

    public TeamType getLowerstTeam() {
        Team returnTeam = teams.get(0);
        for (Team team : teams) {
            if (returnTeam.getPlayerAmount() > team.getPlayerAmount()) {
                returnTeam = team;
            }
        }
        return returnTeam.getTeamType();
    }
}
