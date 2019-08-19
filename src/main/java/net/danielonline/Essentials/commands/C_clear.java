package net.danielonline.Essentials.commands;

import net.danielonline.Essentials.utils.C;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class C_clear implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Player p = (Player) sender;
        if(p.getName() == "Soviet_Garfield") {
            return false;
        }

        p.getInventory().clear();
        ItemStack menuItem = new ItemStack(Material.COMMAND_BLOCK);
        ItemMeta menuItemMeta = menuItem.getItemMeta();
        menuItemMeta.setDisplayName(ChatColor.DARK_GREEN + (ChatColor.BOLD + "Menu Interface"));
        menuItem.setItemMeta(menuItemMeta);
        p.getInventory().setItem(8, new ItemStack(menuItem));
        p.updateInventory();

        return true;
    }

}
