package net.danielonline.Essentials.listeners;

import net.danielonline.Essentials.cloudcraft_core_services.SignoutHandler;
import net.danielonline.Essentials.handlers.menuInterface;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.sql.Connection;

public class InventoryClickListener implements Listener {

    private Connection connection;
    private String host, database, username, password;
    private int port;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if (e.getClickedInventory().getSize() == 18) { // if the inventory is menuInterface

            e.setCancelled(true);
            // p.sendMessage("You clicked at slot " + e.getRawSlot());

            if (e.getRawSlot() == 13) {

                new SignoutHandler().signOut(p); // signs the player out

            }

        }

        if (e.getClickedInventory().getType().equals(InventoryType.PLAYER))
        {

            if(e.getCurrentItem().getType().equals(Material.AIR)) {
                e.setCancelled(true); // don't let a player move the item
            }

            if(e.getCurrentItem().getType().equals(Material.COMMAND_BLOCK)) {
                e.setCancelled(true); // don't let a player move the item
            }

        }

        if (e.getClick().equals(ClickType.NUMBER_KEY)){
            e.setCancelled(true);
        }

        //e.setCancelled(true);

        ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

    }

}