package com.Ja90n.enums;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.item.*;
import org.junit.jupiter.api.Named;

public enum ShopItems {

    STONE_SWORD(ItemStack.builder(Material.STONE_SWORD).displayName(Component.text("Stone sword", NamedTextColor.DARK_GRAY)).build()),
    IRON_SWORD(ItemStack.builder(Material.IRON_SWORD).displayName(Component.text("Iron sword", NamedTextColor.GRAY)).build()),
    DIAMOND_SWORD(ItemStack.builder(Material.DIAMOND_SWORD).displayName(Component.text("Diamond sword", NamedTextColor.BLUE)).build()),
    SHEARS(ItemStack.builder(Material.SHEARS).displayName(Component.text("Shears")).meta(metabuilder -> metabuilder.enchantment(Enchantment.EFFICIENCY,(short) 5).hideFlag(ItemHideFlag.HIDE_ENCHANTS)).build());

        private ItemStack itemStack;

    ShopItems(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
