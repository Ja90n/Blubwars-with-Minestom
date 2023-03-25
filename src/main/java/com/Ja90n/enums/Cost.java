package com.Ja90n.enums;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public enum Cost {
    COD(Material.COD, Component.text("Cod", NamedTextColor.BLUE)),
    SALMON(Material.SALMON, Component.text("Salmon", NamedTextColor.RED)),
    TROPICAL_FISH(Material.TROPICAL_FISH, Component.text("Tropical fish",NamedTextColor.GOLD)),
    PUFFERFISH(Material.PUFFERFISH, Component.text("Pufferfish",NamedTextColor.YELLOW));

    private final Material material;
    private final Component component;

    Cost(Material material, Component component) {
        this.material = material;
        this.component = component;
    }

    public Material getMaterial() {
        return material;
    }

    public Component getComponent() {
        return component;
    }
}
