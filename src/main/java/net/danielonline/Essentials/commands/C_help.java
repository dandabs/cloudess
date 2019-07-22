package net.danielonline.Essentials.commands;

import net.danielonline.Essentials.utils.*;
import org.bukkit.command.*;

public class    C_help implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!sender.hasPermission("cc.player.help")) {
            sender.sendMessage(C.Prefix + C.Red + "No permission.");
            return true;
        } else {
            sender.sendMessage("not done yet (and probably will never be)");
            return true;
        }
    }
}
