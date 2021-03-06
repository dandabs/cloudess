package net.danielonline.Essentials.commands;

import net.danielonline.Essentials.Essentials;
import net.danielonline.Essentials.utils.LocationSerialization;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class C_addspawn implements CommandExecutor {

    private FileConfiguration config = Essentials.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Player p = (Player) sender;
        if(p.getName() == "Soviet_Garfield") {
            return false;
        }
        Location l = p.getLocation();

        //String st = l.getWorld().getName() + "/" + l.getX() + "." + l.getY() + "." + l.getZ() + "." + l.getPitch() + "." + l.getYaw();

        config.set("location.joinchambers", config.getStringList("location.joinchambers").add(new LocationSerialization().serialize(p.getLocation())));

        Essentials.getInstance().saveConfig();

        return true;
    }

}
