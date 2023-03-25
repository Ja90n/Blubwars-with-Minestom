package com.Ja90n.inventories;

import com.Ja90n.enums.ShopItems;
import com.Ja90n.enums.TeamType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

import java.util.ArrayList;
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
        setTools();
    }

    private void setBlocks() {
        // Blocks
        setItem(11, team.getDisplay().append(Component.text(" wool")), team.getWool(),4);
        setItem(20, team.getDisplay().append(Component.text(" clay")), team.getClay(),2);
        setItem(29,ShopItems.WOOD);
    }

    private void setWeapons() {
        // Swords
        setItem(10,ShopItems.STONE_SWORD);
        setItem(19,ShopItems.IRON_SWORD);
        setItem(28,ShopItems.DIAMOND_SWORD);
    }

    private void setTools() {
        setItem(13,ShopItems.SHEARS);
    }

    private void setEvent() {
        inventory.addInventoryCondition((player, slot, clickType, inventoryConditionResult) -> {
            inventoryConditionResult.setCancel(true);

            switch (slot) {
                case 10:
                    addPlayerItem(ShopItems.STONE_SWORD, player);
                    break;
                case 11:
                    addPlayerItem(ShopItems.WOOL,player);
                    break;
                case 13:
                    addPlayerItem(ShopItems.SHEARS,player);
                    break;
                case 19:
                    addPlayerItem(ShopItems.IRON_SWORD, player);
                    break;
                case 20:
                    addPlayerItem(ShopItems.CLAY, player);
                    break;
                case 28:
                    addPlayerItem(ShopItems.DIAMOND_SWORD, player);
                    break;
                case 29:
                    addPlayerItem(ShopItems.WOOD,player);
                    break;
            }

        });
    }

    private void addPlayerItem(ShopItems shopItem, Player player) {
        int amount = 0;
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            ItemStack itemStack = player.getInventory().getItemStack(i);
            if (itemStack.material().equals(shopItem.getCost().getMaterial())) {
                if (shopItem.getAmount() <= itemStack.amount()) {
                    ItemStack item = ItemStack.builder(
                                    itemStack.material())
                            .displayName(itemStack.getDisplayName())
                            .amount(itemStack.amount() - shopItem.getAmount()).build();

                    player.getInventory().setItemStack(i, item);
                    addItem(shopItem,player);
                    return;
                }
                amount = amount + itemStack.amount();
            }
        }

        if (!(amount >= shopItem.getAmount())) {
            player.sendMessage(Component.text("You don't have enough to buy this item!", NamedTextColor.RED));
            return;
        }

        amount = shopItem.getAmount();

        for (int i = 0; i < player.getInventory().getSize(); i++) {
            ItemStack itemStack = player.getInventory().getItemStack(i);
            if (itemStack.material().equals(shopItem.getCost().getMaterial())) {
                amount = amount - itemStack.amount();
                if (amount <= 0) {
                    amount = amount*-1;

                    ItemStack item = ItemStack.builder(
                                    itemStack.material())
                            .displayName(itemStack.getDisplayName())
                            .amount(amount).build();

                    player.getInventory().setItemStack(i, item);
                    addItem(shopItem,player);
                    return;
                }
                player.getInventory().setItemStack(i,ItemStack.AIR);
            }

        }
    }

    private void addItem(ShopItems shopItems, Player player) {
        if (shopItems.equals(ShopItems.WOOL)) {
            player.getInventory().addItemStack(getItem(team.getDisplay().append(Component.text(" wool")),team.getWool(),4));
            return;
        }

        if (shopItems.equals(ShopItems.CLAY)) {
            player.getInventory().addItemStack(getItem(team.getDisplay().append(Component.text(" clay")),team.getClay(),2));
            return;
        }

        player.getInventory().addItemStack(shopItems.getItemStack());
    }

    private void setItem(int slot, Component name, Material material) {
        inventory.setItemStack(slot, getItem(name,material,1));
    }

    private void setItem(int slot, Component name, Material material, int amount) {
        inventory.setItemStack(slot, getItem(name,material,amount));
    }

    private ItemStack getItem(Component name, Material material) {
        return getItem(name, material, 1);
    }

    private ItemStack getItem(Component name, Material material, int amount) {
        return ItemStack.builder(material).displayName(name).amount(amount).build();
    }

    private void setItem(int slot, ShopItems shopItems) {
        List<Component> lore = new ArrayList<>();
        Component component = Component.text("Cost: ",NamedTextColor.BLUE).append(Component.text(shopItems.getAmount(),NamedTextColor.WHITE).append(Component.text(" ").append(shopItems.getCost().getComponent())));
        lore.add(component);

        ItemStack itemStack = ItemStack.builder(shopItems.getItemStack().material())
                .displayName(shopItems.getItemStack().getDisplayName())
                .lore(lore)
                .build();

        inventory.setItemStack(slot, itemStack);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
