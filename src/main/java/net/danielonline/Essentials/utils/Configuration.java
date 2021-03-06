package net.danielonline.Essentials.utils;

import net.danielonline.Essentials.Essentials;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Configuration {

    private FileConfiguration config = Essentials.getInstance().getConfig();
    private void saveConfig() { Essentials.getInstance().saveConfig(); }

    String[] locationsSpawns = {"world;1;200;1;0;0"};

    public void loadConfiguration() {

        //See "Creating you're defaults"
        config.options().copyDefaults(true); // NOTE: You do not have to use "plugin." if the class extends the java plugin

        //Save the config whenever you manipulate it
        saveConfig();

        config.addDefault("locations.tutorialspawn", "world;0;200;0;0;0");
        config.addDefault("locations.spawn", "world;0;200;0;0;0");
        config.addDefault("locations.joinchambers", Arrays.asList(locationsSpawns));
        config.addDefault("lang.join", "&6{&c+&6} &6%player%");
        config.addDefault("lang.quit", "&6{&c-&6} &6%player%");
        config.addDefault("lang.reload", "&b[CC] &6Plugin reloaded.");
        config.addDefault("lang.reload", "&b[CC] &6Plugin reloaded.");

        config.options().copyDefaults(true);
        saveConfig();
    }

}
