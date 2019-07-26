package net.danielonline.Essentials.handlers;

import net.danielonline.Essentials.utils.G;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.sql.*;
import java.util.Arrays;

public class defaultInterface implements InventoryHolder, Listener {

    private final Inventory inv;

    public defaultInterface() {
        inv = Bukkit.createInventory(this, 9, ChatColor.DARK_GRAY + (ChatColor.BOLD + "«Welcome»"));
        initializeItems();
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    // You can call this whenever you want to put the items in
    public void initializeItems() {

        inv.setItem(3, new G().createGuiItem(Material.LIME_DYE, "§aLink Start!", Arrays.asList("§d§oConnect to the server")));
        inv.setItem(5, new G().createGuiItem(Material.ORANGE_DYE, "§4*Remove NerveGear™*", Arrays.asList("§d§oExit the server")));
    }

    // You can open the inventory with this
    public void openInventory(Player p) {
        p.openInventory(inv);
    }

}
