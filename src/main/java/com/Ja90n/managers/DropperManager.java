package com.Ja90n.managers;

import com.Ja90n.Blubwars;
import com.Ja90n.enums.Cost;
import com.Ja90n.instances.Dropper;
import com.Ja90n.runnables.DropperTask;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Instance;

import java.util.ArrayList;
import java.util.List;

public class DropperManager {

    private ArrayList<Dropper> droppers;
    private DropperTask dropperTask;

    public DropperManager() {
        droppers = new ArrayList<>();
        dropperTask = new DropperTask(this);
    }

    public void start() {
        List<Pos> pos = Blubwars.getConfigManager().getDropperLocations();
        List<Cost> types = Blubwars.getConfigManager().getTypes();

        for (int i = 0; i < pos.size(); i++) {
            addDropper(types.get(i), pos.get(i), Blubwars.getWorld().getInstance());
        }
        dropperTask.start();
    }

    public void addDropper(Cost type, Pos pos, Instance instance) {
        droppers.add(new Dropper(pos,instance,type));
    }

    public ArrayList<Dropper> getDroppers() {
        return droppers;
    }
}
