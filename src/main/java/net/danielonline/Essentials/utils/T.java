package net.danielonline.Essentials.utils;

import org.bukkit.ChatColor;

public class T {

    public String centerTitle(String title) {
        String spacer = "";
        int spaces = 27 - ChatColor.stripColor(title).length();
        for (int i = 0; i < spaces; i++) {
            spacer += " ";
        }
        return spacer + title;
    }

}
