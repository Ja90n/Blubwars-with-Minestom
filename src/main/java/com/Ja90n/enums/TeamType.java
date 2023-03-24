package com.Ja90n.enums;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.item.Material;

public enum TeamType {
    RED(Component.text("Red", NamedTextColor.RED), Material.RED_WOOL,"red", Material.RED_TERRACOTTA, 1),
    BLUE(Component.text("Blue", NamedTextColor.BLUE), Material.BLUE_WOOL,"blue", Material.BLUE_TERRACOTTA,2),
    GREEN(Component.text("Green", NamedTextColor.DARK_GREEN), Material.GREEN_WOOL,"green", Material.LIME_TERRACOTTA,3),
    YELLOW(Component.text("Yellow", NamedTextColor.YELLOW), Material.YELLOW_WOOL,"yellow", Material.YELLOW_TERRACOTTA,5),
    SPECTATOR(Component.text("Spectator", NamedTextColor.GRAY), Material.BARREL, "spectator",Material.BARREL,-1);

    private Component display;
    private Material wool;
    private String teamName;
    private Material clay;
    private int collarColor;

    TeamType(Component display, Material wool, String teamName, Material clay, int collarColor){
        this.display = display;
        this.wool = wool;
        this.teamName = teamName;
        this.clay = clay;
        this.collarColor = collarColor;
    }

    public Component getDisplay() { return display; }
    public Material getWool() { return wool; }
    public String getTeamName() { return teamName; }

    public Material getClay() {
        return clay;
    }

    public int getCollarColor() {
        return collarColor;
    }
}
