package com.Ja90n.instances;

import com.Ja90n.enums.Team;
import net.kyori.adventure.text.Component;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

public class Game {

    /*
    The game class is where the actual game data is being held. I.e. the teams and the droppers etc.
    */

    private HashMap<UUID, Team> teamPlayerHashMap;
    private final Instance world;

    public Game(Instance world){
        this.world = world;
    }

    public void start() {

        Cuboid cuboid = new Cuboid(new Pos(12,110,12),new Pos(-12,100,-12),world);
        for (Iterator<Pos> it = cuboid.posIterator(); it.hasNext(); ) {
            Pos block = it.next();
            world.setBlock(block, Block.AIR);
        }
    }

    public void addPlayer(Player player){

        // Logic to add the player to the team with the least players
        HashMap<Team, Integer> playerAmountPerTeam = new HashMap<>();
        playerAmountPerTeam.put(Team.RED, 0);
        playerAmountPerTeam.put(Team.GREEN, 0);
        playerAmountPerTeam.put(Team.BLUE, 0);
        playerAmountPerTeam.put(Team.YELLOW, 0);
        for (UUID player1 : teamPlayerHashMap.keySet()){
            int playerAmount = playerAmountPerTeam.get(teamPlayerHashMap.get(player1)) + 1;
            playerAmountPerTeam.put(teamPlayerHashMap.get(player1), playerAmount);
        }

        Team lowestTeam = Team.RED;
        for (Team team : playerAmountPerTeam.keySet()){
            if (playerAmountPerTeam.get(team) < playerAmountPerTeam.get(lowestTeam)){
                lowestTeam = team;
            }
        }
        teamPlayerHashMap.put(player.getUuid(), lowestTeam);

        player.sendMessage("You have joined the game!");
    }

    public HashMap<UUID, Team> getTeamPlayerHashMap() {
        return teamPlayerHashMap;
    }
}
