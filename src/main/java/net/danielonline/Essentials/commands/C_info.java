package net.danielonline.Essentials.commands;

import net.danielonline.Essentials.utils.*;
import org.bukkit.command.*;

public class C_info implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!sender.hasPermission("cc.player.info")) {
            sender.sendMessage(C.Prefix + C.Red + "No permission");
            return false;
        } else {
            sender.sendMessage(C.Aqua + "Cloudess " + C.DAqua + "(aka me) " + C.Aqua + "is the private plugin we use.");
            return false;
        }
    }
}
