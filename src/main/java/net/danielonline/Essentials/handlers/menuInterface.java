package net.danielonline.Essentials.handlers;

import net.danielonline.Essentials.utils.G;
import net.danielonline.Essentials.utils.T;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.Arrays;

public class menuInterface implements InventoryHolder, Listener {

    private final Inventory inv;

    public menuInterface() {
        inv = Bukkit.createInventory(this, 18, ChatColor.DARK_GRAY + (ChatColor.BOLD + "Menu Interface"));
        initializeItems();
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    // You can call this whenever you want to put the items in
    public void initializeItems() {
        inv.addItem(new G().createGuiItem(Material.DIAMOND_SWORD, "Example Sword", Arrays.asList("This is an example!", "With description")));
        inv.addItem(new G().createGuiItem(Material.IRON_HELMET, "§bExample Helmet", Arrays.asList("§aThis is an colored example!", "§fWith description")));
    }

    // You can open the inventory with this
    public void openInventory(Player p) {
        p.openInventory(inv);
    }

}