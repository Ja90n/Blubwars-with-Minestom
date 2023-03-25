package com.Ja90n.events;

import com.Ja90n.enums.GameState;
import com.Ja90n.instances.Arena;
import com.Ja90n.instances.TeamCat;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.*;
import net.minestom.server.entity.damage.DamageType;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.entity.EntityAttackEvent;
import net.minestom.server.sound.SoundEvent;

public class EntityAttack {

    public EntityAttack(GlobalEventHandler globalEventHandler, Arena arena) {
        globalEventHandler.addListener(EntityAttackEvent.class, event -> {
            if (!(event.getTarget() instanceof LivingEntity target)) return;

            if (!(event.getEntity() instanceof Player player)) {
                return;
            }

            if (!arena.getGameState().equals(GameState.LIVE)){
                return;
            }

            if (event.getTarget().getEntityType().equals(EntityType.CAT)) {
                EntityCreature entityCreature = (EntityCreature) event.getTarget();
                TeamCat teamCat = arena.getGame().getTeamManager().getCat(entityCreature);

                if (teamCat == null){
                    return;
                }

                if (teamCat.getTeam().getTeamType().equals(arena.getGame().getTeamManager().getTeam(player))) {
                    if (teamCat.getTarget() != null) {
                        if (teamCat.getTarget().equals(player)) {
                            return;
                        }
                    }
                    teamCat.setTarget(player);
                    player.sendMessage(Component.text("Cat claimed!", NamedTextColor.BLUE));
                    player.playSound(Sound.sound(SoundEvent.BLOCK_LEVER_CLICK, Sound.Source.MASTER,1f,1f));
                    return;
                }
            }

            target.damage(DamageType.fromEntity(event.getEntity()),1);
            addKnockback(target,event.getEntity());
        });
    }

    private void addKnockback(Entity target, Entity source){
        target.takeKnockback(
                0.3f,
                Math.sin(source.getPosition().yaw() * (Math.PI / 180)),
                -Math.cos(source.getPosition().yaw() * (Math.PI / 180))
        );
    }
}
