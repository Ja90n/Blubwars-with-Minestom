package com.Ja90n.shopitems.creators;

import com.Ja90n.enums.TeamType;
import com.Ja90n.shopitems.IShopItem;
import com.Ja90n.shopitems.ShopItemFactory;
import com.Ja90n.shopitems.items.WoolItem;

public class WoolCreator extends ShopItemFactory {

    private TeamType teamType;

    public WoolCreator(TeamType team) {
        this.teamType = team;
    }

    @Override
    public IShopItem getShopItem() {
        return new WoolItem(teamType);
    }
}
