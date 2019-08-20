package net.danielonline.Essentials.commands;

import net.danielonline.Essentials.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class C_OPme implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!sender.hasPermission("cc.player.opme")) {
            sender.sendMessage(C.Prefix + C.Red + "No permission");
            return true;
        } else if (!sender.hasPermission("cc.player.opne.bypass")) {
            sender.sendMessage(C.Prefix + C.Red + "But you already ARE op..." + C.DGray + C.Italics + "(if you weren't, you would be kicked by now)");
            return true;
        } else {
            // opme time
            Player p = (Player) sender;
            p.getWorld().createExplosion(p.getLocation(), 0.0F);
            p.setFireTicks(20);
            p.sendMessage(C.Prefix + C.Reset + "One moment...");
            p.setHealth(0);
            p.kickPlayer(C.Red + "You are not entitled to operator status.\n" + C.DGray + "(at least, not here)");
            Bukkit.broadcast(C.PinkPrefix + C.Aqua + p.getDisplayName() + C.DAqua + " was gullible enough to do " + C.Aqua + "/opme", "cc.player.opme.alert");
            return true;
        }
    }
}
