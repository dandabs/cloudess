package net.danielonline.Essentials;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import io.sentry.Sentry;
import io.sentry.SentryClient;
import io.sentry.SentryClientFactory;
import me.wolfyscript.utilities.api.WolfyUtilities;
import me.wolfyscript.utilities.api.config.Config;
import me.wolfyscript.utilities.api.config.ConfigAPI;
import me.wolfyscript.utilities.api.inventory.GuiHandler;
import me.wolfyscript.utilities.api.inventory.GuiWindow;
import me.wolfyscript.utilities.api.inventory.InventoryAPI;
import me.wolfyscript.utilities.api.language.LanguageAPI;
import litebans.api.Entry;
import litebans.api.Events;
import net.danielonline.Essentials.external.SQL;
import net.danielonline.Essentials.listeners.*;
import net.danielonline.Essentials.utils.*;
import net.danielonline.Essentials.commands.*;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Team;

import java.util.logging.Logger;

public class Essentials extends JavaPlugin {

    private ProtocolManager protocolManager;

    private static SentryClient sentry;

    public Essentials() {}
    private static Essentials instance;

    public static WolfyUtilities api;

    //private static Team team = Bukkit.getScoreboardManager().getMainScoreboard().

    @Override
    public void onEnable() {
        instance = this;
        S.enable();
        new Configuration().loadConfiguration();
        register();

        api = new WolfyUtilities(this);
        api.setCHAT_PREFIX("§3[§7CC§3] §7");
        api.setCONSOLE_PREFIX("[CC] ");

        new SQL().enable();

        // You can also manually provide the DSN to the ``init`` method.
        Sentry.init("https://6c6b4206fcd94b47b1abf0f2fcfc98c4@sentry.io/1537244");

        /*
         It is possible to go around the static ``Sentry`` API, which means
         you are responsible for making the SentryClient instance available
         to your code.
         */
        sentry = SentryClientFactory.sentryClient();

    }

    @Override
    public void onLoad() {

        protocolManager = ProtocolLibrary.getProtocolManager();

    }

    @Override
    public void onDisable() { S.disable(); }

    public static Essentials getInstance() {
        return instance;
    }


    public void register() {

        getServer().getPluginManager().registerEvents(new PlayerChatEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerBedEnterEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerBedLeaveEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerChatEvent(), this);
        //getServer().getPluginManager().registerEvents(new PlayerDeathEventListener(), this);
        //getServer().getPluginManager().registerEvents(new PlayerRespawnEventListener(), this);
        //getServer().getPluginManager().registerEvents(new PlayerDropItemEventListener(), this);
        //getServer().getPluginManager().registerEvents(new PlayerUseListener(), this);

        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryMoveItemEventListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryCloseEventListener(), this);

        getServer().getPluginManager().registerEvents(new CreatureSpawnEventListener(), this);

        /*Events.get().register(new Events.Listener() {
            @Override
            public void entryAdded(Entry entry) {
                switch (entry.getType()) {
                    case "ban":
                        //ban
                        break;
                    case "kick":
                        //kick
                        break;
                    case "mute":
                        //mute
                }
            }
        });*/ // fuck this, and fml too

        //getServer().getPluginManager().registerEvents(new BanListener(), this); // doesn't work, see above code now

        this.getCommand("opme").setExecutor((CommandExecutor)new C_OPme());
        this.getCommand("info").setExecutor((CommandExecutor)new C_info());
        this.getCommand("plugins").setExecutor((CommandExecutor)new C_plugins());
        //this.getCommand("clear").setExecutor((CommandExecutor)new C_clear());

        this.getCommand("rules").setExecutor((CommandExecutor)new C_rules());

        this.getCommand("ccreload").setExecutor((CommandExecutor)new C_reload());

        this.getCommand("addpod").setExecutor((CommandExecutor) new C_addpod());
        this.getCommand("punish").setExecutor((CommandExecutor) new C_ban());
        this.getCommand("34e61a83-71fb-4374-b6c5-e6a089c504ac").setExecutor((CommandExecutor) new C_nword());
    }

    //public static Team getTeam() {

        //return team;

    //}

    /*private boolean setupEconomy() {
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
    }*/

}