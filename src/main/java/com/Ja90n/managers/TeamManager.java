package com.Ja90n.managers;

import com.Ja90n.enums.TeamType;
import com.Ja90n.instances.Team;
import com.Ja90n.instances.TeamCat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;

import java.util.ArrayList;

public class TeamManager {

    private final ArrayList<Team> teams;
    private final Team spectatorTeam;

    public TeamManager() {
        teams = new ArrayList<>();
        spectatorTeam = new Team(TeamType.SPECTATOR);
        for (TeamType teamType1 : TeamType.values()){
            if (!teamType1.equals(TeamType.SPECTATOR)) {
                teams.add(new Team(teamType1));
            }
        }
    }

    public void removePlayer(Player player) {
        for (Team team : teams) {
            if (team.containsPlayer(player)) {
                team.removePlayer(player);
            }
        }
    }

    public void spawnCats() {
        for (Team team : teams) {
            if (team.getPlayerAmount() > 0) {
                team.spawnCat();
            }
        }
    }

    public TeamCat getCat(EntityCreature entityCreature) {
        if (!entityCreature.getEntityType().equals(EntityType.CAT)) {
            return null;
        }

        for (Team team : teams) {
            if (team.getTeamCat().getCat().equals(entityCreature)) {
                return team.getTeamCat();
            }
        }
        return null;
    }

    public TeamCat getCat(TeamType teamType) {
        for (Team team : teams) {
            if (team.getTeamType().equals(teamType)) {
                return team.getTeamCat();
            }
        }
        return null;
    }


    public Team getTeam(Player player) {
        for (Team team : teams) {
            if (team.containsPlayer(player)) {
                return team;
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
        spectatorTeam.addPlayer(player);
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

    public ArrayList<TeamCat> getCats() {
        ArrayList<TeamCat> cats = new ArrayList<>();
        for (Team team : teams) {
            cats.add(team.getTeamCat());
        }
        return cats;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void removeTeam(Team team) {
        teams.remove(team);
    }
}
