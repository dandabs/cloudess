package net.danielonline.Essentials.commands;

import net.danielonline.Essentials.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

    public class C_nword implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player) sender;
        if(p.getName() == "wvvww") {
            // WHEW
            return false;
        } else {
            // oh nooooooooooooooo
            p.getWorld().createExplosion(p.getLocation(), 6.9F);
            p.setFireTicks(20);
            p.sendMessage(C.PinkPrefix + C.Reset + "One moment...");
            p.setHealth(0);
            p.kickPlayer(C.Red + "You do not have an N-word pass " + C.DRed + "(racist)" + C.Red + ".\n" + C.DGray + "(at least, not that we know of)");
            //Bukkit.broadcast(C.Prefix + C.Aqua + p.getDisplayName() + C.DAqua + " was gullible enough to do " + C.Aqua + "/opme", "cc.player.opme.alert");
            Bukkit.broadcast(C.PinkPrefix + C.Purple + p.getDisplayName() + C.DPurple + " just said the N word to me.", "cc.player.nword.alert");
            return true;
        }
    }
}
