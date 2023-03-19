package com.Ja90n.enums;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.item.Material;

public enum TeamType {
    RED(Component.text("Red", NamedTextColor.RED), Material.RED_WOOL,"red"),
    BLUE(Component.text("Blue", NamedTextColor.BLUE), Material.BLUE_WOOL,"blue"),
    GREEN(Component.text("Green", NamedTextColor.DARK_GREEN), Material.GREEN_WOOL,"green"),
    YELLOW(Component.text("Yellow", NamedTextColor.YELLOW), Material.YELLOW_WOOL,"yellow"),
    SPECTATOR(Component.text("Spectator", NamedTextColor.GRAY), Material.BARREL, "spectator");

    private TextComponent display;
    private Material material;
    private String teamName;

    TeamType(TextComponent display, Material material, String teamName){
        this.display = display;
        this.material = material;
        this.teamName = teamName;
    }

    public TextComponent getDisplay() { return display; }
    public Material getMaterial() { return material; }
    public String getTeamName() { return teamName; }
}
