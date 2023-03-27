package com.Ja90n.events;

import com.Ja90n.enums.TeamType;
import com.Ja90n.shopitems.IShopItem;
import com.Ja90n.shopitems.ShopItemFactory;
import com.Ja90n.shopitems.creators.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.item.ItemStack;

public class ShopGuiEvent {

    private Inventory shopGUI;
    private TeamType team;
    private int amount;

    public ShopGuiEvent(Inventory shopGUI, TeamType team) {
        this.shopGUI = shopGUI;
        this.team = team;
    }

    public void setEvent() {
        shopGUI.addInventoryCondition((player, slot, clickType, inventoryConditionResult) -> {
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
                case 30:
                    addPlayerItem(new DiamondArmorCreator(),player);
                    break;
                case 21:
                    addPlayerItem(new IronArmorCreator(),player);
                    break;

            }

        });
    }

    private void addPlayerItem(ShopItemFactory factory, Player player) {
        IShopItem shopItem = factory.getShopItem();
        if (canBuyAtOnce(player,shopItem)) {
            return;
        }

        if (!(amount >= shopItem.getCostAmount())) {
            player.sendMessage(Component.text("You don't have enough to buy this item!", NamedTextColor.RED));
            return;
        }

        buySeperate(player,shopItem);
    }

    private boolean canBuyAtOnce(Player player,IShopItem shopItem) {
        amount = 0;
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
                    return true;
                }
            }
        }
        return false;
    }

    public void buySeperate(Player player, IShopItem shopItem) {
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
        if (shopItem.isArmor()) {
            shopItem.setArmor(player);
            return;
        }
        player.getInventory().addItemStack(shopItem.getItemStack());
    }
}
