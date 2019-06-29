package net.danielonline.Essentials;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Statics {

    public static final String USER_AGENT = "Mozilla/5.0";
    public static Map<String, Object> map = new HashMap();

    public static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }

}
