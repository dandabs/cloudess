package net.danielonline.Essentials.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerRespawnEventListener implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {

        Player p = e.getPlayer();

        ItemStack menuItem = new ItemStack(Material.COMMAND_BLOCK);
        ItemMeta menuItemMeta = menuItem.getItemMeta();
        menuItemMeta.setDisplayName(ChatColor.DARK_GREEN + (ChatColor.BOLD + "Menu Interface"));
        menuItem.setItemMeta(menuItemMeta);
        e.getPlayer().getInventory().setItem(8, new ItemStack(menuItem));

    }

}
