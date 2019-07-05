package net.danielonline.Essentials.cloudcraft_core_services;

import org.bukkit.Bukkit;

public class serverCore {

    public boolean init(int key) {

        System.out.println("Logging in to CloudCraft CORE™...");

        if (key == 357461) {

            System.out.println("Successfully authenticated user into CloudCraft CORE™.");
            return true;

        } else return false;

    }

}
