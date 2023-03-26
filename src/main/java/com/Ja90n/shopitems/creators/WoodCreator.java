package com.Ja90n.shopitems.creators;

import com.Ja90n.shopitems.IShopItem;
import com.Ja90n.shopitems.ShopItemFactory;
import com.Ja90n.shopitems.items.WoodItem;

public class WoodCreator extends ShopItemFactory {

    @Override
    public IShopItem getShopItem() { return new WoodItem(); }
}
