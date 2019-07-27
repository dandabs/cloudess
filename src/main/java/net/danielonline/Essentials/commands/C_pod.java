package net.danielonline.Essentials.commands;

import net.danielonline.Essentials.Essentials;
import net.danielonline.Essentials.utils.LocationSerialization;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.graalvm.compiler.loop.InductionVariable;

public class C_pod implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Player p = (Player) sender;
        Block sign = p.getLocation().getBlock(); // temporarily assign variable

        String direction = new LocationSerialization().getDirection(p);

        if (direction == "E") {

            sign = new Location(p.getWorld(), p.getLocation().getBlock().getX() + 2, p.getLocation().getBlock().getY() - 1, p.getLocation().getBlock().getZ()).getBlock();

        } else if (direction == "W") {

            sign = new Location(p.getWorld(), p.getLocation().getBlock().getX() + 2, p.getLocation().getBlock().getY() - 1, p.getLocation().getBlock().getZ()).getBlock();

        } //else Bukkit.broadcastMessage(direction);

        if (p.getLocation().getBlock().getType() == Material.CHISELED_QUARTZ_BLOCK &&
                sign.getType() == Material.WALL_SIGN) {
            Sign si = (Sign) sign;

            si.setLine(0, "§4Working!");

        } else {

            Bukkit.broadcastMessage("ooooops no work");

        }

        Bukkit.broadcastMessage(sign.getType().toString());

        return true;

    }

}
