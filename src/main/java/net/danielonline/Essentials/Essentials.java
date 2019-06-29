package net.danielonline.Essentials;

import net.danielonline.Essentials.listeners.*;
import net.danielonline.Essentials.utils.*;
import net.danielonline.Essentials.commands.*;

import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;

import java.util.logging.Logger;

public class Essentials extends JavaPlugin {

    public Essentials() {}

    @Override
    public void onEnable() {
        S.enable();
        register();

        if (!setupEconomy() ) {
            Statics.log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        setupPermissions();
        setupChat();

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

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        Statics.econ = rsp.getProvider();
        return Statics.econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        Statics.chat = rsp.getProvider();
        return Statics.chat != null;
    }

    public boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        Statics.perms = rsp.getProvider();
        return Statics.perms != null;
    }

}