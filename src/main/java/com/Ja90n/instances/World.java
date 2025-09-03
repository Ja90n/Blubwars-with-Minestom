package com.Ja90n.instances;

import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.Instance;

import java.nio.file.Path;

public class World {

    private final Instance world;

    public World() {
        world = MinecraftServer.getInstanceManager().createInstanceContainer(new net.minestom.server.instance.anvil.AnvilLoader(Path.of("Temple")));
    }

    public Instance getInstance() {
        return world;
    }
}
