package com.Ja90n.command;

import com.Ja90n.Blubwars;
import com.Ja90n.instances.Arena;
import com.Ja90n.instances.Cuboid;
import com.Ja90n.inventories.ShopGUI;
import net.kyori.adventure.text.Component;
import net.minestom.server.command.builder.Command;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;

import java.util.Iterator;

public class Test extends Command {

    public Test(Arena arena) {
        super("test");

        setDefaultExecutor((sender, context) -> {
            if (sender instanceof Player player) {
                if (player.getDisplayName().contains(Component.text("Ja90n"))) {
                    Blubwars.getWorld().getInstance().saveChunksToStorage();
                }
            }
        });
    }
}
