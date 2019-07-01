package net.danielonline.Essentials.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryMoveItemEventListener implements Listener {

    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent event) {

        /*Bukkit.broadcastMessage(event.getInitiator().getType() + "");

        if (event.getInitiator().getType().equals(InventoryType.PLAYER)) {

            Bukkit.broadcastMessage(event.getItem().getType() + "");

            if (event.getItem().getType().equals(Material.COMMAND_BLOCK)) {

                event.setCancelled(true);

            }

        }*/

    }

}
