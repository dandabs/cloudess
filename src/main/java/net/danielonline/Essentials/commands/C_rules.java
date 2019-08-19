package net.danielonline.Essentials.commands;

import net.danielonline.Essentials.utils.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class C_rules implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player) sender;
        if(p.getName() == "Soviet_Garfield") {
            return false;
        }

        if (!sender.hasPermission("cc.player.rules")) {
            sender.sendMessage(C.Prefix + C.Red + "No permission.");
            return true;
        } else {
            //User user;
            //sender.sendMessage();
        }
        return true;
    }
}
