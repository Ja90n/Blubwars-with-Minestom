package com.Ja90n.shopitems.items;

import com.Ja90n.enums.Cost;
import com.Ja90n.enums.TeamType;
import com.Ja90n.shopitems.IShopItem;
import net.kyori.adventure.text.Component;
import net.minestom.server.item.ItemStack;

public class WoolItem implements IShopItem {

    private TeamType team;

    public WoolItem(TeamType teamType) {
        this.team = teamType;
    }

    @Override
    public Cost getCost() {
        return Cost.COD;
    }

    @Override
    public ItemStack getItemStack() {
        return ItemStack.builder(team.getWool())
                .displayName(team.getDisplay().append(Component.text(" wool")))
                .amount(4)
                .build();
    }

    @Override
    public int getCostAmount() {
        return 1;
    }
}
