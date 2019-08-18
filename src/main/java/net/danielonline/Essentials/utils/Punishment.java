package net.danielonline.Essentials.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import litebans.api.*;

public class Punishment {

    public void permban(Player sender, String victim, String reason) {

        Bukkit.dispatchCommand(sender, "litebans:ban " + victim + " " + reason);

    }

    public void tempban(Player sender, String victim, String reason, String time) {

        Bukkit.dispatchCommand(sender, "litebans:tempban " + victim + " "  + time + " " + reason);

    }

    public void permmute(Player sender, String victim, String reason) {

        Bukkit.dispatchCommand(sender, "litebans:mute " + victim + " " + reason);

    }

    public void tempmute(Player sender, String victim, String reason, String time) {

        Bukkit.dispatchCommand(sender, "litebans:tempmute " + victim + " " + time + " " + reason);

    }

    public void kick(Player sender, String victim, String reason) {

        Bukkit.dispatchCommand(sender, "litebans:kick " + victim + " " + reason);

    }

    public void warn(Player sender, String victim, String reason) {

        Bukkit.dispatchCommand(sender, "litebans:warn " + victim + " " + reason);

    }

}
