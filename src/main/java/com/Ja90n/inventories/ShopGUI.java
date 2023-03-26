package com.Ja90n.inventories;

import com.Ja90n.enums.TeamType;
import com.Ja90n.shopitems.IShopItem;
import com.Ja90n.shopitems.ShopItemFactory;
import com.Ja90n.shopitems.creators.*;
import com.Ja90n.shopitems.items.ClayItem;
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
        setItem(11, new WoolCreator(team));
        setItem(20, new ClayCreator(team));
        setItem(29, new WoodCreator());
    }

    private void setWeapons() {
        // Swords
        setItem(10, new StoneSwordCreator());
        setItem(19, new IronSwordCreator());
        setItem(28, new DiamondSwordCreator());
    }

    private void setTools() {
        setItem(13, new ShearsCreator());
    }

    private void setEvent() {
        inventory.addInventoryCondition((player, slot, clickType, inventoryConditionResult) -> {
            inventoryConditionResult.setCancel(true);

            switch (slot) {
                case 10:
                    addPlayerItem(new StoneSwordCreator(), player);
                    break;
                case 11:
                    addPlayerItem(new WoolCreator(team),player);
                    break;
                case 13:
                    addPlayerItem(new ShearsCreator(),player);
                    break;
                case 19:
                    addPlayerItem(new IronSwordCreator(), player);
                    break;
                case 20:
                    addPlayerItem(new ClayCreator(team), player);
                    break;
                case 28:
                    addPlayerItem(new DiamondSwordCreator(), player);
                    break;
                case 29:
                    addPlayerItem(new WoodCreator(),player);
                    break;
            }

        });
    }

    private void addPlayerItem(ShopItemFactory factory, Player player) {
        IShopItem shopItem = factory.getShopItem();

        int amount = 0;
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            ItemStack itemStack = player.getInventory().getItemStack(i);
            if (itemStack.material().equals(shopItem.getCost().getMaterial())) {
                if (shopItem.getCostAmount() <= itemStack.amount()) {
                    ItemStack item = ItemStack.builder(
                                    itemStack.material())
                            .displayName(itemStack.getDisplayName())
                            .amount(itemStack.amount() - shopItem.getCostAmount()).build();

                    player.getInventory().setItemStack(i, item);
                    addItem(shopItem,player);
                    return;
                }
                amount = amount + itemStack.amount();
            }
        }

        if (!(amount >= shopItem.getCostAmount())) {
            player.sendMessage(Component.text("You don't have enough to buy this item!", NamedTextColor.RED));
            return;
        }

        amount = shopItem.getCostAmount();

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

    private void addItem(IShopItem shopItem, Player player) {
        player.getInventory().addItemStack(shopItem.getItemStack());
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

    private void setItem(int slot, ShopItemFactory shopItemFactory) {
        IShopItem shopItem = shopItemFactory.getShopItem();
        List<Component> lore = new ArrayList<>();
        Component component = Component.text("Cost: ",NamedTextColor.BLUE)
                .append(Component.text(shopItem.getCostAmount(),NamedTextColor.WHITE)
                        .append(Component.text(" ")
                                .append(shopItem.getCost().getComponent())));

        lore.add(component);

        ItemStack itemStack = ItemStack.builder(shopItem.getItemStack().material())
                .displayName(shopItem.getItemStack().getDisplayName())
                .lore(lore)
                .build();

        inventory.setItemStack(slot, itemStack);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
