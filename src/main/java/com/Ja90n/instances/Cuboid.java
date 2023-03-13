package com.Ja90n.instances;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Cuboid {

    private final int xMin;
    private final int xMax;
    private final int yMin;
    private final int yMax;
    private final int zMin;
    private final int zMax;
    private final double xMinCentered;
    private final double xMaxCentered;
    private final double yMinCentered;
    private final double yMaxCentered;
    private final double zMinCentered;
    private final double zMaxCentered;
    private final Instance world;

    public Cuboid(final Pos point1, final Pos point2, Instance instance) {
        this.xMin = (int) Math.min(point1.x(), point2.x());
        this.xMax = (int) Math.max(point1.x(), point2.x());
        this.yMin = (int) Math.min(point1.y(), point2.y());
        this.yMax = (int) Math.max(point1.y(), point2.y());
        this.zMin = (int) Math.min(point1.z(), point2.z());
        this.zMax = (int) Math.max(point1.z(), point2.z());
        this.xMinCentered = this.xMin + 0.5;
        this.xMaxCentered = this.xMax + 0.5;
        this.yMinCentered = this.yMin + 0.5;
        this.yMaxCentered = this.yMax + 0.5;
        this.zMinCentered = this.zMin + 0.5;
        this.zMaxCentered = this.zMax + 0.5;
        this.world = instance;
    }

    public Iterator<Block> blockList() {
        List<Block> bL = new ArrayList<>(this.getTotalBlockSize());
        for (int x = this.xMin; x <= this.xMax; ++x) {
            for (int y = this.yMin; y <= this.yMax; ++y) {
                for (int z = this.zMin; z <= this.zMax; ++z) {
                    Block b = this.world.getBlock(x, y, z);
                    bL.add(b);
                }
            }
        }
        return bL.iterator();
    }

    public Iterator<Pos> posIterator() {
        List<Pos> pL = new ArrayList<>(this.getTotalBlockSize());
        for (int x = this.xMin; x <= this.xMax; ++x) {
            for (int y = this.yMin; y <= this.yMax; ++y) {
                for (int z = this.zMin; z <= this.zMax; ++z) {
                    Pos b = new Pos(x, y, z);
                    pL.add(b);
                }
            }
        }
        return pL.iterator();
    }

    public Pos getCenter() {
        return new Pos((this.xMax - this.xMin) / 2 + this.xMin, (this.yMax - this.yMin) / 2 + this.yMin, (this.zMax - this.zMin) / 2 + this.zMin);
    }

    public int getHeight() {
        return this.yMax - this.yMin + 1;
    }

    public Pos getPoint1() {
        return new Pos(this.xMin, this.yMin, this.zMin);
    }

    public Pos getPoint2() {
        return new Pos(this.xMax, this.yMax, this.zMax);
    }

    public Pos getRandomLocation() {
        final Random rand = new Random();
        final int x = rand.nextInt(Math.abs(this.xMax - this.xMin) + 1) + this.xMin;
        final int y = rand.nextInt(Math.abs(this.yMax - this.yMin) + 1) + this.yMin;
        final int z = rand.nextInt(Math.abs(this.zMax - this.zMin) + 1) + this.zMin;
        return new Pos(x, y, z);
    }

    public int getTotalBlockSize() {
        return this.getHeight() * this.getXWidth() * this.getZWidth();
    }

    public int getXWidth() {
        return this.xMax - this.xMin + 1;
    }

    public int getZWidth() {
        return this.zMax - this.zMin + 1;
    }

    public boolean isIn(final Pos loc) {
        return loc.x() >= this.xMin && loc.x() <= this.xMax && loc.y() >= this.yMin && loc.y() <= this.yMax && loc
                .z() >= this.zMin && loc.z() <= this.zMax;
    }

    public boolean isIn(final Player player) {
        return this.isIn(player.getPosition());
    }
}