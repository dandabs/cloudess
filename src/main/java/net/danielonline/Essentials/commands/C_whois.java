package net.danielonline.Essentials.commands;

import net.danielonline.Essentials.utils.*;
import org.bukkit.command.*;

//import static org.bukkit.Bukkit.getPlayer;

public class C_whois implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!sender.hasPermission("cc.staff.whois")) {
            sender.sendMessage(C.Prefix + C.Red + "No permission.");
            return true;
        } else {
            //User user;
            sender.sendMessage(C.Gray + "Username: " + C.Aqua + sender.getName());
            //sender.sendMessage(C.DGray + "* " + C.Gray + "UUID: " + C.DAqua + sender.);
        }
    }
}
