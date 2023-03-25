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

    public TeamManager() {
        teams = new ArrayList<>();
        for (TeamType teamType1 : TeamType.values()){
            if (!teamType1.equals(TeamType.SPECTATOR)) {
                teams.add(new Team(teamType1));
            }
        }
    }

    public void spawnCats() {
        for (Team team : teams) {
            team.spawnCat();
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
}
