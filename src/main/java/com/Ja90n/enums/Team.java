package com.Ja90n.enums;

import net.minestom.server.item.Material;

public enum Team {
    SPECTATOR("Spectator", Material.BARREL, "spectator"),
    RED("Red", Material.RED_WOOL,"red"),
    BLUE("Blue", Material.BLUE_WOOL,"blue"),
    GREEN("Green", Material.GREEN_WOOL,"green"),
    YELLOW("Yellow", Material.YELLOW_WOOL,"yellow");

    private String display;
    private Material material;
    private String teamName;

    Team (String display, Material material, String teamName){
        this.display = display;
        this.material = material;
        this.teamName = teamName;
    }

    public String getDisplay() { return display; }
    public Material getMaterial() { return material; }
    public String getTeamName() { return teamName; }
}
