package com.Ja90n.enums;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.item.*;
import org.junit.jupiter.api.Named;

public enum ShopItems {

    WOOL(ItemStack.AIR,Cost.COD,1),
    CLAY(ItemStack.AIR,Cost.COD,5),
    WOOD(ItemStack.builder(Material.SPRUCE_PLANKS).displayName(Component.text("Wood",NamedTextColor.GOLD)).build(),Cost.SALMON,2),
    STONE_SWORD(ItemStack.builder(Material.STONE_SWORD).displayName(Component.text("Stone sword", NamedTextColor.DARK_GRAY)).build(),Cost.COD,10),
    IRON_SWORD(ItemStack.builder(Material.IRON_SWORD).displayName(Component.text("Iron sword", NamedTextColor.GRAY)).build(),Cost.SALMON,10),
    DIAMOND_SWORD(ItemStack.builder(Material.DIAMOND_SWORD).displayName(Component.text("Diamond sword", NamedTextColor.BLUE)).build(),Cost.PUFFERFISH,5),
    SHEARS(ItemStack.builder(Material.SHEARS).displayName(Component.text("Shears")).meta(metabuilder -> metabuilder.enchantment(Enchantment.EFFICIENCY,(short) 5).hideFlag(ItemHideFlag.HIDE_ENCHANTS)).build(),Cost.COD,5);

    private ItemStack itemStack;
    private Cost cost;
    private int amount;

    ShopItems(ItemStack itemStack, Cost cost, int amount) {
        this.itemStack = itemStack;
        this.cost = cost;
        this.amount = amount;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Cost getCost() {
        return cost;
    }

    public int getAmount() {
        return amount;
    }
}
