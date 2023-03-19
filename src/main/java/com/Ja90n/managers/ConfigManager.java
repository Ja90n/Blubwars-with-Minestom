package com.Ja90n.managers;

import com.Ja90n.enums.TeamType;
import com.Ja90n.instances.ArenaConfig;
import com.Ja90n.instances.Config;
import com.google.gson.Gson;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.minestom.server.coordinate.Pos;

import java.io.*;

public class ConfigManager {

    private Config config;
    private ArenaConfig arenaConfig;

    public ConfigManager(ComponentLogger logger) throws IOException {

        Gson gson = new Gson();
        File file = new File("config.json");
        if (file.exists()){
            logger.info("Config file found!");
        } else {
            file.createNewFile();

            Config config1 = new Config();
            config1.setDefault();

            FileWriter writer = new FileWriter(file);
            String json = gson.toJson(config1);
            writer.write(json);
            writer.close();

            logger.info("Config file created");
        }

        File arenafile = new File("arenaconfig.json");
        if (arenafile.exists()){
            logger.info("Arena config file found!");
        } else {
            arenafile.createNewFile();

            ArenaConfig arenaConfig = new ArenaConfig();
            arenaConfig.setDefault();

            FileWriter writer = new FileWriter(arenafile);
            String json = gson.toJson(arenaConfig);
            writer.write(json);
            writer.close();

            logger.info("Arena config file created");
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        config = gson.fromJson(reader, Config.class);

        BufferedReader arenareader = new BufferedReader(new FileReader(arenafile));
        arenaConfig = gson.fromJson(arenareader, ArenaConfig.class);
    }

    public String getHost() {
        return config.getServerHost();
    }

    public String getForwardingSecret() {
        return config.getForwardingSecret();
    }

    public int getPort() {
        return config.getPort();
    }

    public boolean getVelocity() {
        return config.getVelocity();
    }

    public Pos getLobbyLocation() {
        return arenaConfig.getLobbyLocation();
    };

    public int getCountdownSeconds() {
        return arenaConfig.getCountdownSeconds();
    }

    public int getRequiredPlayers() {
        return arenaConfig.getRequiredPlayers();
    }

    public Pos getTeamSpawn(TeamType teamType){
        switch (teamType){
            case SPECTATOR -> {
                return arenaConfig.getLobbyLocation();
            }
            case RED -> {
                return arenaConfig.getRedTeamSpawn();
            }
            case BLUE -> {
                return arenaConfig.getBlueTeamSpawn();
            }
            case YELLOW -> {
                return arenaConfig.getYellowTeamSpawn();
            }
            case GREEN -> {
                return arenaConfig.getGreenTeamSpawn();
            }
            default -> {
                return null;
            }
        }
    }

}
