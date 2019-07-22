package net.danielonline.Essentials.commands;

import net.danielonline.Essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class C_reload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Player p = (Player) sender;

        Essentials.getInstance().reloadConfig();
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Essentials.getInstance().getConfig().getString("lang.reload")));

        return true;
    }

}
