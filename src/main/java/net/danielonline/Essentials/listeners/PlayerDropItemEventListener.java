package net.danielonline.Essentials.listeners;

import net.danielonline.Essentials.handlers.menuInterface;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemEventListener implements Listener {

    @EventHandler
    public void playerDropItem(PlayerDropItemEvent e) {

        Player p = e.getPlayer();

        if(e.getItemDrop().getItemStack().getType() == Material.COMMAND_BLOCK ||
                e.getItemDrop().getItemStack().getType().toString() == ChatColor.DARK_GREEN + (ChatColor.BOLD + "Menu Interface")){

            new menuInterface().openInventory(p);
            e.setCancelled(true);

        }

    }

}
