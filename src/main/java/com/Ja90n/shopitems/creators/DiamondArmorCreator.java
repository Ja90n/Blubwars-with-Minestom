package com.Ja90n.shopitems.creators;

import com.Ja90n.shopitems.IShopItem;
import com.Ja90n.shopitems.ShopItemFactory;
import com.Ja90n.shopitems.items.DiamondArmorItem;
import com.Ja90n.shopitems.items.IronArmorItem;

public class DiamondArmorCreator extends ShopItemFactory {

    @Override
    public IShopItem getShopItem() {
        return new DiamondArmorItem();
    }
}
