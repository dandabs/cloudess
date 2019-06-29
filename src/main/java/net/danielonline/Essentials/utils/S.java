package net.danielonline.Essentials.utils;

import net.danielonline.Essentials.Essentials;

import java.io.IOException;

import static org.bukkit.Bukkit.getConsoleSender;
import java.util.logging.Logger;

public class S {

    private static final Logger log = Logger.getLogger("Minecraft");

    public static void enable() {

        getConsoleSender().sendMessage(C.Aqua + "Cloud" + C.DAqua + "ess" + C.White + " has been enabled!");
        log.info(String.format("[%s] Disabled Version %s", Essentials.getPlugin(Essentials.class).getDescription().getName(), Essentials.getPlugin(Essentials.class).getDescription().getVersion()));

        try {
            H.registerWeb();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            W.startGet();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void disable() {

        getConsoleSender().sendMessage(C.Aqua + "Cloud" + C.DAqua + "ess" + C.White + " has been disabled!");
        try {
            W.stopGet();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
