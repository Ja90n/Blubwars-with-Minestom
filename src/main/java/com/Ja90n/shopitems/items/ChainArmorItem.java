package com.Ja90n.shopitems.items;

import com.Ja90n.enums.Cost;
import com.Ja90n.shopitems.IShopItem;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class ChainArmorItem implements IShopItem {

    @Override
    public Cost getCost() {
        return Cost.COD;
    }

    @Override
    public ItemStack getItemStack() {
        return ItemStack.builder(Material.CHAINMAIL_BOOTS).build();
    }

    @Override
    public int getCostAmount() {
        return 10;
    }

    @Override
    public int inventoySlot() {
        return 12;
    }

    @Override
    public boolean isArmor() {
        return true;
    }

    @Override
    public void setArmor(Player player) {
        player.getInventory().setBoots(ItemStack.builder(Material.CHAINMAIL_BOOTS).build());
        player.getInventory().setLeggings(ItemStack.builder(Material.CHAINMAIL_LEGGINGS).build());
    }
}
