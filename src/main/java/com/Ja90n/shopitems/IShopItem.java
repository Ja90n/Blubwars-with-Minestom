package com.Ja90n.shopitems;

import com.Ja90n.enums.Cost;
import net.minestom.server.item.ItemStack;

public interface IShopItem {

    Cost getCost();
    ItemStack getItemStack();
    int getCostAmount();

}
