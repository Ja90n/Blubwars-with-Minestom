package com.Ja90n.enums;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.color.Color;
import net.minestom.server.color.DyeColor;
import net.minestom.server.item.Material;

public enum TeamType {
    RED(Component.text("Red", NamedTextColor.RED), Material.RED_WOOL,"red", Material.RED_TERRACOTTA, DyeColor.RED,new Color(255, 69, 97)),
    BLUE(Component.text("Blue", NamedTextColor.BLUE), Material.BLUE_WOOL,"blue", Material.BLUE_TERRACOTTA,DyeColor.BLUE,new Color(107, 102, 250)),
    GREEN(Component.text("Green", NamedTextColor.DARK_GREEN), Material.GREEN_WOOL,"green", Material.LIME_TERRACOTTA,DyeColor.GREEN, new Color(122, 255, 107)),
    YELLOW(Component.text("Yellow", NamedTextColor.YELLOW), Material.YELLOW_WOOL,"yellow", Material.YELLOW_TERRACOTTA,DyeColor.YELLOW, new Color(255, 253, 107)),
    SPECTATOR(Component.text("Spectator", NamedTextColor.GRAY), Material.BARREL, "spectator",Material.BARREL,DyeColor.GRAY, new Color(0));

    private Component display;
    private Material wool;
    private String teamName;
    private Material clay;
    private DyeColor collarColor;
    private Color color;

    TeamType(Component display, Material wool, String teamName, Material clay, DyeColor collarColor, Color color){
        this.display = display;
        this.wool = wool;
        this.teamName = teamName;
        this.clay = clay;
        this.collarColor = collarColor;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Component getDisplay() { return display; }
    public Material getWool() { return wool; }
    public String getTeamName() { return teamName; }

    public Material getClay() {
        return clay;
    }

    public DyeColor getCollarColor() {
        return collarColor;
    }
}
