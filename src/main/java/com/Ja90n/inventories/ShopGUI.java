package com.Ja90n.inventories;

import com.Ja90n.enums.TeamType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

import java.util.List;

public class ShopGUI {

    private final Inventory inventory;
    private final TeamType team;

    public ShopGUI(TeamType team) {
        inventory = new Inventory(InventoryType.CHEST_5_ROW, Component.text("Shop", NamedTextColor.LIGHT_PURPLE));
        this.team = team;
        setupGUI();
    }

    private void setupGUI() {
        setFrame();
        setContent();
        setEvent();
    }

    private void setFrame() {
        ItemStack frame = ItemStack.builder(Material.PINK_STAINED_GLASS_PANE)
                .displayName(Component.text(" "))
                .build();
        for (int i : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44}) {
            inventory.setItemStack(i, frame);
        }
    }

    private void setContent() {
        setBlocks();
        setWeapons();
    }

    private void setBlocks() {
        // Blocks
        setItem(11, team.getDisplay().append(Component.text(" wool")), team.getWool());
        setItem(20, team.getDisplay().append(Component.text(" clay")), team.getClay());
        setItem(29,Component.text("Wood",NamedTextColor.GOLD),Material.SPRUCE_PLANKS);

    }

    private void setWeapons() {
        // Swords
        setItem(10, Component.text("Stone sword", NamedTextColor.DARK_GRAY), Material.STONE_SWORD);
        setItem(19, Component.text("Iron sword", NamedTextColor.GRAY), Material.IRON_SWORD);
        setItem(28, Component.text("Diamond sword", NamedTextColor.BLUE), Material.DIAMOND_SWORD);
    }

    private void setEvent() {
        inventory.addInventoryCondition((player, slot, clickType, inventoryConditionResult) -> {
            inventoryConditionResult.setCancel(true);

            switch (slot) {
                case 10:
                    player.getInventory().addItemStack(itemStackCreator(Component.text("Stone sword", NamedTextColor.DARK_GRAY), Material.STONE_SWORD));
                    break;
                case 19:
                    player.getInventory().addItemStack(itemStackCreator(Component.text("Iron sword", NamedTextColor.GRAY), Material.IRON_SWORD));
                    break;
                case 28:
                    player.getInventory().addItemStack(itemStackCreator(Component.text("Diamond sword", NamedTextColor.BLUE),Material.DIAMOND_SWORD));
                    break;
            }

        });
    }

    private ItemStack itemStackCreator(Component name, Material material) {
        return ItemStack.builder(material).displayName(name).build();
    }

    private ItemStack itemStackCreator(Component name, Material material, List<Component> lore) {
        if (lore.isEmpty()) {
            return ItemStack.builder(material).displayName(name).build();
        } else {
            return ItemStack.builder(material).displayName(name).lore(lore).build();
        }
    }

    private void setItem(int slot, Component name, Material material) {
        ItemStack itemStack = ItemStack.builder(material)
                .displayName(name).build();
        inventory.setItemStack(slot, itemStack);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
