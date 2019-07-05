package net.danielonline.Essentials.listeners;

import net.danielonline.Essentials.Essentials;
import net.danielonline.Essentials.handlers.defaultInterface;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitScheduler;

import static org.bukkit.Bukkit.getServer;

public class InventoryCloseEventListener implements Listener {

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent e) {

        if (e.getInventory().getSize() == 9) {

                BukkitScheduler scheduler = getServer().getScheduler();
                scheduler.scheduleSyncDelayedTask(Essentials.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        if (InventoryClickListener.temp == 0) {
                            new defaultInterface().openInventory(Bukkit.getPlayer(e.getPlayer().getName()));
                        }
                    }
                }, 10L);

        }

    }

}
