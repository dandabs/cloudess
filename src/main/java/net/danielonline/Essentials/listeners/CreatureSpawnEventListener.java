package net.danielonline.Essentials.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnEventListener implements Listener {

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e) {

        if (e.getEntity().getType() == EntityType.PHANTOM) {

            e.setCancelled(true);

        }

    }

}
