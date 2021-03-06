package net.danielonline.Essentials.cloudcraft_core_services;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.danielonline.Essentials.Essentials;
import net.danielonline.Essentials.external.SQL;
import net.danielonline.Essentials.handlers.defaultInterface;
import net.danielonline.Essentials.listeners.InventoryClickListener;
import net.danielonline.Essentials.utils.*;
import net.mcjukebox.plugin.bukkit.api.JukeboxAPI;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
    public Connection connection2;
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
            database = "essentials";
            susername = "dockerconnect";
            password = "chiicken";

            try {
                openConnection();
                Statement statement = connection.createStatement();
                statement.executeUpdate("DELETE FROM `corePlayers` WHERE USERNAME = '" + username + "';");

                //statement.executeUpdate("UPDATE `dockerconnect.corePlayers` SET `lastlogout_X` = '" + (int)lastlogout_X + "' WHERE `username` = '" + username + "';");
                //statement.executeUpdate("UPDATE `dockerconnect.corePlayers` SET `lastlogout_Y` = '" + (int)lastlogout_Y + "' WHERE `username` = '" + username + "';");
                //statement.executeUpdate("UPDATE `dockerconnect.corePlayers` SET `lastlogout_Z` = '" + (int)lastlogout_Z + "' WHERE `username` = '" + username + "';");
                //statement.executeUpdate("UPDATE `dockerconnect.corePlayers` SET `lastlogout_YAW` = '" + lastlogout_yaw + "' WHERE `username` = '" + username + "';");
                //statement.executeUpdate("UPDATE `dockerconnect.corePlayers` SET `lastlogout_PITCH` = '" + lastlogout_pitch + "' WHERE `username` = '" + username + "';");

                statement.executeUpdate("INSERT INTO `corePlayers` (USERNAME, BANNED, LASTLOGOUT_X, LASTLOGOUT_Y, LASTLOGOUT_Z, LASTLOGOUT_YAW, LASTLOGOUT_PITCH) VALUES ('" + username + "', '" + banned + "', '" + lastlogout_X + "', '" + lastlogout_Y + "', '" + lastlogout_Z + "', '" + lastlogout_yaw + "', '" + lastlogout_pitch + "');");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    };

    public void signOut(Player player) throws IOException {

        if (!player.getWorld().getName().equals("world")) {

            player.teleport(new LocationSerialization().deserialize(Essentials.getInstance().getConfig().getString("locations.spawn")));

        }

        if (new serverCore().init(357461)) {

            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Essentials.getInstance().getConfig().getString("lang.quit")).replace("%player%", player.getName()));

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

        host = "116.203.95.196";
        port = 3306;
        database = "essentials";
        susername = "dockerconnect";
        password = "chiicken";

        Statement stmt = null;
        try {
            openConnection();
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {


        }

        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM tblPods WHERE `podMember` = '" + player.getUniqueId() + "';");

            while (rs.next()) {

                //String lastName = rs.getString("Lname");
                //System.out.println(lastName + "\n");

                new Pods().makeOffline(rs.getString("podID"));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<String> loc() {
        return (ArrayList<String>) Essentials.getInstance().getConfig().getStringList("locations.joinchambers");
    }


    public void attemptSignIn(final Player player, String username, String banned, final String lastlogout_X, final String lastlogout_Y, final String lastlogout_Z, final Inventory inventory) {

        // check if player is banned
        if (Boolean.valueOf(banned)) {

            player.kickPlayer("You are dead.");

        } else {

            host = "116.203.95.196";
            port = 3306;
            database = "essentials";
            susername = "dockerconnect";
            password = "chiicken";

            Statement stmt = null;
            try {
                openConnection();
                stmt = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {


            }
            List<String> l2 = new ArrayList<String>();
            List<String> l2x = new ArrayList<String>();
            List<String> l2y = new ArrayList<String>();
            List<String> l2z = new ArrayList<String>();
            List<String> l3 = new ArrayList<String>();
            List<String> l4 = new ArrayList<String>();

            try {
                //ResultSet rs = stmt.executeQuery("SELECT * FROM tblPods;");
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM tblPods;");
                ResultSet rs = result;

                while (rs.next()) {

                    //String lastName = rs.getString("Lname");
                    //System.out.println(lastName + "\n");

                    l2.add(rs.getString("podID"));
                    l2x.add(rs.getString("podX"));
                    l2y.add(rs.getString("podY"));
                    l2z.add(rs.getString("podZ"));
                    l3.add(rs.getString("podMember"));

                }

                Statement statement1 = connection.createStatement();
                ResultSet result1 = statement1.executeQuery("SELECT * FROM tblPods WHERE podMember IS NULL;");
                ResultSet rs1 = result1;

                while (rs1.next()) {

                    //String lastName = rs.getString("Lname");
                    //System.out.println(lastName + "\n");

                    l4.add(rs1.getString("podID"));

                }

                if (!(l3.contains(player.getUniqueId().toString()))) {
                    if (l3.size() != 0 || l2.size() == 0) {
                        player.kickPlayer("§6Please reconnect to the server.\n§4This is an anti-bot verification test.");
                    } else if (l3.size() == 0) {

                        player.kickPlayer("§6There are no available pods.\n§4Please contact a staff member on Discord.");

                    }
                }

                if (l3.size() != 0) {

                    if (!l3.contains(player.getUniqueId().toString())) {

                        stmt.executeUpdate("UPDATE `tblPods` SET `podMember` = '" + player.getUniqueId() + "' WHERE `podID` = '" + l4.get(0) + "';");

                        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, player.getName());
                        npc.spawn(new Location(player.getWorld(), Double.valueOf(l2x.get(0)), Double.valueOf(l2x.get(0)), Double.valueOf(l2x.get(0))));

                        new Pods().makePending(l4.get(0));

                    } else {
                        new Pods().makePending(l2.get(l3.indexOf(player.getUniqueId().toString())));
                    }

                } else {
                    new Pods().makePending(l2.get(l3.indexOf(player.getUniqueId().toString())));
                } //new Pods().makeOnline(l2.get(0));

                //new Pods().makeOnline(l2.get(0));


            } catch (SQLException e) {

                e.printStackTrace();

            }

            //Bukkit.broadcastMessage("loc is " + loc().get(0));

            //player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 0, 2));

            Random rand = new Random();

            List spawns = Essentials.getInstance().getConfig().getList("locations.joinchambers");

            ArrayList<String> array = new ArrayList<>(spawns);

            //String[] loc = spawns.get(rand.nextInt(array.size())).toString().split("/");
            //String[] coords = loc[1].split(".");

            Location l = new LocationSerialization().deserialize(loc().get(rand.nextInt(loc().size())));

            //Location l = new Location(Bukkit.getServer().getWorld("world"), Double.parseDouble("-393.5"), Double.parseDouble("112"), Double.parseDouble("1562.5"), Float.parseFloat("93.355"), Float.parseFloat("6.338"));

            //player.teleport(l);

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + player.getName());

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

                //player.sendTitle("§4Welcome to", "§bCloud§fCraft §5Online", 5, 10, 5);
                //player.sendTitle("§6Remember to log out using the menu", "", 5, 10, 5);
                //player.sendTitle("§6Tutorial now loading...", "", 5, 10, 5);

                tempPlayersX.put(player, lastlogout_X);
                tempPlayersY.put(player, lastlogout_Y);
                tempPlayersZ.put(player, lastlogout_Z);
                tempPlayersInventory.put(player, inventory);

                Bukkit.getScheduler().runTaskLater(Essentials.getInstance(), new Runnable() {
                    @Override
                    public void run() {

                        // ask player what they want to do (eg. log on, log off)
                        Bukkit.dispatchCommand(pp, "spawn");

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

    public void signIn(Player player) throws SQLException {

        host = "116.203.95.196";
        port = 3306;
        database = "essentials";
        susername = "dockerconnect";
        password = "chiicken";

        try {
            openConnection();
            Statement statement = connection.createStatement();

            //Statement statement3 = connection.createStatement();
            ResultSet result5 = statement.executeQuery("SELECT * FROM tblPods WHERE podMember = '" + player.getUniqueId() + "';");
            ResultSet rs5 = result5;
            List<String> l5 = new ArrayList<String>();

            while (rs5.next()) {

                //String lastName = rs.getString("Lname");
                //System.out.println(lastName + "\n");

                l5.add(rs5.getString("podID"));

            }

            new Pods().makeOnline(l5.get(0));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




        pp = player;

        //This is where you should do your database interaction

        host = "116.203.95.196";
        port = 3306;
        database = "essentials";
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

                    Location loc = new Location(pp.getWorld(), Double.valueOf("-396.5"), Double.valueOf("125"), Double.valueOf("1541.5"), Float.valueOf("180.0"), Float.valueOf("15.2"));
                    //Inventory inv = i;
                    Bukkit.dispatchCommand(pp, "spawn");
                    //pp.teleport(loc);
                    //new BukkitSerialization().restoreInventory(player);
                    //pp.sendTitle("Title", "Subtitle", 1, 4, 1);

                } else {

                    Location loc2 = new Location(pp.getWorld(), Double.valueOf("-396.5"), Double.valueOf("125"), Double.valueOf("1541.5"), Float.valueOf("180.0"), Float.valueOf("15.2"));
                    //Inventory inv = i;
                    pp.teleport(loc2);
                    Location loc = new Location(pp.getWorld(), Double.valueOf(x), Double.valueOf(y), Double.valueOf(z), Float.valueOf(yaw), Float.valueOf(pitch));
                    Inventory inv = i;

                    Bukkit.dispatchCommand(pp, "spawn");
                    //pp.teleport(loc);
                    new BukkitSerialization().restoreInventory(player);
                    //pp.getInventory().setContents(inv.getContents());
                    //pp.updateInventory();
                    //pp.sendTitle("Title", "Subtitle", 1, 4, 1);
                }

                //JukeboxAPI.stopMusic(player);
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "jukebox stop " + pp.getName() + "");

                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Essentials.getInstance().getConfig().getString("lang.join")).replace("%player%", player.getName()));

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

    public void openConnection2() throws SQLException, ClassNotFoundException {
        if (connection2 != null && !connection2.isClosed()) {
            return;
        }

        synchronized (this) {
            if (connection2 != null && !connection2.isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection2 = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + "essentials", this.susername, this.password);
        }

    }

}
