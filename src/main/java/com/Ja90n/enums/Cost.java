package com.Ja90n.enums;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public enum Cost {
    COD(Material.COD, Component.text("Cod", NamedTextColor.BLUE),2),
    SALMON(Material.SALMON, Component.text("Salmon", NamedTextColor.RED),7),
    TROPICAL_FISH(Material.TROPICAL_FISH, Component.text("Tropical fish",NamedTextColor.GOLD),30),
    PUFFERFISH(Material.PUFFERFISH, Component.text("Pufferfish",NamedTextColor.YELLOW),60);

    private final Material material;
    private final Component component;
    private final int dropDelay;

    Cost(Material material, Component component, int dropDelay) {
        this.material = material;
        this.component = component;
        this.dropDelay = dropDelay;
    }

    public Material getMaterial() {
        return material;
    }

    public Component getComponent() {
        return component;
    }

    public int getDropDelay() {
        return dropDelay;
    }
}
