package com.Ja90n.shopitems.items;

import com.Ja90n.enums.Cost;
import com.Ja90n.enums.TeamType;
import com.Ja90n.shopitems.IShopItem;
import net.kyori.adventure.text.Component;
import net.minestom.server.item.ItemStack;

public class ClayItem implements IShopItem {

    private TeamType team;

    public ClayItem(TeamType team) {
        this.team = team;
    }

    @Override
    public Cost getCost() {
        return Cost.COD;
    }

    @Override
    public ItemStack getItemStack() {
        return ItemStack.builder(team.getClay())
                .customName(team.getDisplay().append(Component.text(" clay")))
                .amount(2)
                .build();
    }

    @Override
    public int getCostAmount() {
        return 5;
    }

    @Override
    public int inventoySlot() {
        return 20;
    }
}
