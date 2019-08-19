package net.danielonline.Essentials.commands;

import net.danielonline.Essentials.utils.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class C_info implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player) sender;
        if(p.getName() == "Soviet_Garfield") {
            return false;
        }

        if(!sender.hasPermission("cc.player.info")) {
            sender.sendMessage(C.Prefix + C.Red + "No permission");
            return true;
        } else {
            sender.sendMessage(C.Aqua + "Cloudess " + C.DAqua + "(aka me) " + C.Aqua + "is the private plugin we use.");
            return true;
        }
    }
}
