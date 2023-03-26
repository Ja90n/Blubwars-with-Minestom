package com.Ja90n.shopitems.creators;

import com.Ja90n.enums.TeamType;
import com.Ja90n.shopitems.IShopItem;
import com.Ja90n.shopitems.ShopItemFactory;
import com.Ja90n.shopitems.items.ClayItem;

public class ClayCreator extends ShopItemFactory {

    private TeamType team;

    public ClayCreator(TeamType team) {
        this.team = team;
    }

    @Override
    public IShopItem getShopItem() {
        return new ClayItem(team);
    }
}
