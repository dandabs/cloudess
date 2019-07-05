package net.danielonline.Essentials.commands;

import net.danielonline.Essentials.utils.C;
import org.bukkit.GameMode;
import org.bukkit.command.*;
import org.bukkit.Server;

import java.util.Locale;

public class C_gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender.hasPermission("cc.staff.gamemode")) {
            sender.sendMessage(C.Prefix + C.Red + "No permission.");
            return true;
        } else {
            GameMode gm;
            if (args.length == 0) {
                sender.sendMessage(C.Prefix + C.Red + "Please specify a gamemode.");
                return true;
            } else if (args.length == 1) {
                switch (args[0].toLowerCase(Locale.ENGLISH)) {
                    case "0": case "survival": case "s":
                        //survival mode
                        sender.getServer
                    case "1": case "creative": case "c":
                        //creative
                    case "2": case "adventure": case "a":
                        //adv
                    case "3": case "spectator": case "sp":
                }
                gamemodeOtherPlayers(server, sender, gameMode, args[0]);
            } else if (args.length == 2) {
                gm = matchGameMode(args[0].toLowerCase(Locale.ENGLISH));
                gamemodeOtherPlayers(server, sender, gameMode, args[1]);
            }
        }
    }
}
