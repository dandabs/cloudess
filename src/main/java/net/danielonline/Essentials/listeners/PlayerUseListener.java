package net.danielonline.Essentials.listeners;

import net.danielonline.Essentials.handlers.menuInterface;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerUseListener implements Listener {

    @EventHandler(priority= EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event){
        Player p = event.getPlayer();

        if(event.getItem().getType() == Material.COMMAND_BLOCK ||
           event.getItem().getType().toString() == ChatColor.DARK_GREEN + (ChatColor.BOLD + "Menu Interface")){

            new menuInterface().openInventory(p);
            event.setCancelled(true);

        }
    }

}
