package com.Ja90n.managers;

import com.Ja90n.Blubwars;
import com.Ja90n.instances.ShopVillager;
import net.minestom.server.coordinate.Pos;

import java.util.ArrayList;

public class VillagerManager {

    private ArrayList<ShopVillager> shopVillagers;

    public VillagerManager() {
        shopVillagers = new ArrayList<>();
    }

    public void setupShopVillagers() {
        for (Pos pos : Blubwars.getConfigManager().getShopLocations()) {
            shopVillagers.add(new ShopVillager(pos));
        }
    }
}
