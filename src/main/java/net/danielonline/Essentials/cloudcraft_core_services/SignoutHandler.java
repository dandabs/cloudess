package net.danielonline.Essentials.cloudcraft_core_services;

import net.danielonline.Essentials.Essentials;
import net.danielonline.Essentials.external.SQL;
import net.danielonline.Essentials.handlers.defaultInterface;
import net.danielonline.Essentials.listeners.InventoryClickListener;
import net.danielonline.Essentials.utils.Anvils;
import net.danielonline.Essentials.utils.BukkitSerialization;
import net.danielonline.Essentials.utils.Configuration;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.scheduler.BukkitRunnable;

import javax.net.ssl.ExtendedSSLSession;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SignoutHandler {

    private String username;
    private boolean banned;
    private double lastlogout_X;
    private double lastlogout_Y;
    private double lastlogout_Z;
    private String lastlogout_yaw;
    private String lastlogout_pitch;
    private String inventory;

    private Connection connection;
    private String host, database, susername, password;
    private int port;

    public static HashMap<Player,String> tempPlayersX = new HashMap<Player,String>();
    public static HashMap<Player,String> tempPlayersY = new HashMap<Player,String>();
    public static HashMap<Player,String> tempPlayersZ = new HashMap<Player,String>();
    public static HashMap<Player,Inventory> tempPlayersInventory = new HashMap<Player,Inventory>();

    public Inventory AnvilInput;

    BukkitRunnable r1 = new BukkitRunnable() {
        @Override
        public void run() {
            //This is where you should do your database interaction

            host = "116.203.95.196";
            port = 3306;
            database = "dockerconnect";
            susername = "dockerconnect";
            password = "chiicken";

            try {
                openConnection();
                Statement statement = connection.createStatement();
                statement.executeUpdate("DELETE FROM corePlayers WHERE USERNAME = '" + username + "';");
                statement.executeUpdate("INSERT INTO corePlayers (USERNAME, BANNED, LASTLOGOUT_X, LASTLOGOUT_Y, LASTLOGOUT_Z, LASTLOGOUT_YAW, LASTLOGOUT_PITCH) VALUES ('" + username + "', '" + banned + "', '" + (int) lastlogout_X + "', '" + (int) lastlogout_Y + "', '" +(int) lastlogout_Z + "', '" + lastlogout_yaw + "', '" + lastlogout_pitch + "');");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    };

    public void signOut(Player player) throws IOException {

        if (new serverCore().init(357461)) {

            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', new Essentials().getConfig().getString("quitMessage")).replace("%player%", player.getName()));

            username = "" + player.getUniqueId();
            banned = false;
            lastlogout_X = player.getLocation().getX();
            lastlogout_Y = player.getLocation().getY();
            lastlogout_Z = player.getLocation().getZ();
            lastlogout_yaw = String.valueOf(player.getLocation().getYaw());
            lastlogout_pitch = String.valueOf(player.getLocation().getPitch());
            //inventory = new BukkitSerialization().toBase64(player.getInventory());

            new BukkitSerialization().saveInventory(player);

            r1.runTaskAsynchronously(Essentials.getInstance());

            // teleport user to a safeplace


            // Remove player from server.
            player.getInventory().clear();
            player.updateInventory();
            player.getInventory().setArmorContents(null);
            player.kickPlayer("Successfully detached from the CloudCraft Mainframe. \n Have a nice day!");

        }

    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    public void attemptSignIn(final Player player, String username, String banned, final String lastlogout_X, final String lastlogout_Y, final String lastlogout_Z, final Inventory inventory) {

        // check if player is banned
        if (Boolean.valueOf(banned)) {

            player.kickPlayer("You are dead.");

        } else {

            Random rand = new Random();

            List spawns = Essentials.getInstance().getConfig().getList("locations.spawns");

            ArrayList<String> array = new ArrayList<>(spawns);

            String[] loc = spawns.get(rand.nextInt(array.size())).toString().split("/");
            String[] coords = loc[1].split(".");
            Location l = new Location(Bukkit.getServer().getWorld("world"), Double.parseDouble("394.5"), Double.parseDouble("112.5"), Double.parseDouble("1562"), Float.parseFloat("93.355"), Float.parseFloat("6.338"));

            player.teleport(l);

            File f = new File(Essentials.getInstance().getDataFolder().getAbsolutePath(), "players/inventories/" + player.getName() + ".yml");

            if (f.isFile()) {

                tempPlayersX.put(player, lastlogout_X);
                tempPlayersY.put(player, lastlogout_Y);
                tempPlayersZ.put(player, lastlogout_Z);
                tempPlayersInventory.put(player, inventory);

                Bukkit.getScheduler().runTaskLater(Essentials.getInstance(), new Runnable() {
                    @Override
                    public void run() {

                        // ask player what they want to do (eg. log on, log off)
                        InventoryClickListener.temp = 0;
                        new defaultInterface().openInventory(player);

                    }
                }, 20L);

            } else {

                // new player is joining

                player.sendTitle("§4Welcome to", "§bCloud§fCraft §5Online", 5, 10, 5);
                player.sendTitle("§6Remember to log out using the menu", "", 5, 10, 5);
                player.sendTitle("§6Tutorial now loading...", "", 5, 10, 5);

                tempPlayersX.put(player, lastlogout_X);
                tempPlayersY.put(player, lastlogout_Y);
                tempPlayersZ.put(player, lastlogout_Z);
                tempPlayersInventory.put(player, inventory);

                Bukkit.getScheduler().runTaskLater(Essentials.getInstance(), new Runnable() {
                    @Override
                    public void run() {

                        // ask player what they want to do (eg. log on, log off)
                        InventoryClickListener.temp = 0;
                        new defaultInterface().openInventory(player);

                    }
                }, 20L);

            }

        }

    }

    private String x;
    private String y;
    private String z;
    private String yaw;
    private String pitch;
    private Inventory i;

    private Player pp;

    BukkitRunnable r2 = new BukkitRunnable() {
        @Override
        public void run() {


        }
    };

    public void signIn(Player player) {

        pp = player;

        //This is where you should do your database interaction

        host = "116.203.95.196";
        port = 3306;
        database = "dockerconnect";
        susername = "dockerconnect";
        password = "chiicken";

        try {
            openConnection();
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM corePlayers WHERE USERNAME = '" + player.getUniqueId() + "';");
            while (result.next()) {

                x = result.getString("LASTLOGOUT_X");
                y = result.getString("LASTLOGOUT_Y");
                z = result.getString("LASTLOGOUT_Z");
                yaw = result.getString("LASTLOGOUT_YAW");
                pitch = result.getString("LASTLOGOUT_PITCH");
                //i = BukkitSerialization.fromBase64(result.getString("INVENTORY"));

                if (x == "") {

                    Location loc = new Location(pp.getWorld(), Double.valueOf("338"), Double.valueOf("64"), Double.valueOf("419"), Float.valueOf("180.209"), Float.valueOf("6.753"));
                    //Inventory inv = i;
                    pp.teleport(loc);
                    //new BukkitSerialization().restoreInventory(player);
                    pp.sendTitle("Title", "Subtitle", 1, 4, 1);

                } else {

                    Location loc = new Location(pp.getWorld(), Double.valueOf(x), Double.valueOf(y), Double.valueOf(z), Float.valueOf(yaw), Float.valueOf(pitch));
                    Inventory inv = i;

                    pp.teleport(loc);
                    new BukkitSerialization().restoreInventory(player);
                    //pp.getInventory().setContents(inv.getContents());
                    //pp.updateInventory();
                    pp.sendTitle("Title", "Subtitle", 1, 4, 1);
                }

                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', new Essentials().getConfig().getString("joinMessage")).replace("%player%", player.getName()));

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } //catch (IOException e) {
        catch (IOException e) {
            e.printStackTrace();
        }
        //e.printStackTrace();
        //}

    }

    public void openConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.susername, this.password);
        }

    }

}
