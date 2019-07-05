package net.danielonline.Essentials.listeners;

import net.danielonline.Essentials.cloudcraft_core_services.SignoutHandler;
import net.danielonline.Essentials.handlers.menuInterface;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.sql.Connection;

public class InventoryClickListener implements Listener {

    private Connection connection;
    private String host, database, username, password;
    private int port;

    public static int temp = 0;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if (e.getClickedInventory().getSize() == 18) { // if the inventory is menuInterface

            e.setCancelled(true);
            // p.sendMessage("You clicked at slot " + e.getRawSlot());

            if (e.getRawSlot() == 13) {

                try {
                    new SignoutHandler().signOut(p); // signs the player out
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

        }

        //if (e.getClickedInventory().getSize() == 9) { // if the inventory is defaultInterface
        if (e.getInventory().getTitle().equals(net.md_5.bungee.api.ChatColor.DARK_GRAY + (ChatColor.BOLD + "«Welcome»"))) {

            e.setCancelled(true);

            // p.sendMessage("You clicked at slot " + e.getRawSlot());

            if (e.getRawSlot() == 1) {

                temp = 1;

                try {
                    new SignoutHandler().signOut(p); // signs the player out
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
            if (e.getRawSlot() == 0) {

                temp = 1;

                new SignoutHandler().signIn(p); // signs the player in

            }

        }

        if (e.getClickedInventory().getType().equals(InventoryType.PLAYER))
        {

            /*if(e.getCurrentItem().getType().equals(Material.AIR)) {
                e.setCancelled(true); // don't let a player move the item
            }*/

            if(e.getCurrentItem().getType().equals(Material.COMMAND_BLOCK)) {
                e.setCancelled(true); // don't let a player move the item
            }

        }


        //e.setCancelled(true);

        ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

    }

}
