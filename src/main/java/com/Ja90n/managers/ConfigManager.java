package com.Ja90n.managers;

import com.Ja90n.instances.Config;
import com.google.gson.Gson;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;

import java.io.*;

public class ConfigManager {

    private Config config;

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


        BufferedReader reader = new BufferedReader(new FileReader(file));
        config = gson.fromJson(reader, Config.class);
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



}
