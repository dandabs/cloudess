package net.danielonline.Essentials.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryOpenEventListener implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {

        Player p = Bukkit.getPlayer(e.getPlayer().getName());
        Inventory i = e.getInventory();

        ItemStack is = new ItemStack(Material.PAPER);

    }

}
