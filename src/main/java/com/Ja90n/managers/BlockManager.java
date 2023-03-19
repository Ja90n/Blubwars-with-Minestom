package com.Ja90n.managers;

import com.Ja90n.instances.Game;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;

import java.util.ArrayList;

public class BlockManager {

    private ArrayList<Point> blocks;

    public BlockManager() {
        blocks = new ArrayList<>();
    }

    public void addBlock(Point block){
        blocks.add(block);
    }

    public void removeBlock(Point block) {
        blocks.remove(block);
    }

    public boolean containsBlock(Point block) {
        return blocks.contains(block);
    }
}
