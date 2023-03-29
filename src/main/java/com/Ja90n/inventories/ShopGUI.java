package com.Ja90n.inventories;

import com.Ja90n.enums.TeamType;
import com.Ja90n.events.ShopGuiEvent;
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
    private final ShopGuiEvent shopGuiEvent;

    public ShopGUI(TeamType team) {
        inventory = new Inventory(InventoryType.CHEST_5_ROW, Component.text("Shop", NamedTextColor.LIGHT_PURPLE));
        this.team = team;
        setupGUI();
        shopGuiEvent = new ShopGuiEvent(inventory,team);
        shopGuiEvent.setEvent();
    }

    private void setupGUI() {
        setFrame();
        setContent();
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
        setArmor();
    }

    private void setBlocks() {
        // Blocks
        setItem(new WoolCreator(team));
        setItem(new ClayCreator(team));
        setItem(new WoodCreator());
    }

    private void setArmor() {
        setItem(new ChainArmorCreator());
        setItem(new IronArmorCreator());
        setItem(new DiamondArmorCreator());
    }

    private void setWeapons() {
        // Swords
        setItem(new StoneSwordCreator());
        setItem(new IronSwordCreator());
        setItem(new DiamondSwordCreator());
    }

    private void setTools() {
        setItem(new ShearsCreator());
        setItem(new PickaxeCreator());
    }

    private void setItem(ShopItemFactory shopItemFactory) {
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

        inventory.setItemStack(shopItem.inventoySlot(), itemStack);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
