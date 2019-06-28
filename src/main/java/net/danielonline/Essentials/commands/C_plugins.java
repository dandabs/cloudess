package net.danielonline.Essentials.commands;

import net.danielonline.Essentials.utils.*;
import org.bukkit.command.*;

public class C_plugins implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!sender.hasPermission("cc.player.plugins")) {
            // whistles
            return false;
        } else {
            sender.sendMessage(C.White + "Plugins (" + C.Scramble + "69" + C.Reset + "): " + C.Purple + "M" + C.DPurple + "a" + C.Purple + "g" + C.DPurple + "i" + C.Purple + "c");
            return false;
        }
    }
}
