package net.danielonline.Essentials.listeners;

import com.sun.tools.javac.util.Convert;
import net.danielonline.Essentials.Essentials;
import net.danielonline.Essentials.external.SQL;
import net.danielonline.Essentials.utils.BukkitSerialization;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;

public class PlayerJoinEventListener implements Listener {

    private final String USER_AGENT = "Mozilla/5.0";

    public PlayerJoinEventListener() {}
    private Connection connection;
    private String host, database, susername, password;
    private int port;

    private String username;
    private double lastlogout_X;
    private double lastlogout_Y;
    private double lastlogout_Z;
    private String inventory;

    private int verification;

    BukkitRunnable r1 = new BukkitRunnable() {
        @Override
        public void run() {

            //This is where you should do your database interaction

            host = "eu-sql.pebblehost.com";
            port = 3306;
            database = "customer_74746";
            susername = "customer_74746";
            password = "5f84734369";

            verification = 0;

            try {
                openConnection();
                Statement statement = connection.createStatement();

                ResultSet result = statement.executeQuery("SELECT * FROM corePlayers WHERE USERNAME = '" + username + "';");
                while (result.next()) {

                    String username = result.getString("USERNAME");
                     verification = 1;

                }

                if (verification == 0) {

                    try {
                        openConnection();
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate("INSERT INTO corePlayers (USERNAME, BANNED, LASTLOGOUT_X, LASTLOGOUT_Y, LASTLOGOUT_Z, INVENTORY) VALUES ('" + username + "', '" + "false" + "', '" + (int) lastlogout_X + "', '" + (int) lastlogout_Y + "', '" + (int) lastlogout_Z + "', '" + inventory + "');");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Bukkit.getScheduler().cancelTask(r1.getTaskId());

        }
    };

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {

        try {
            Bukkit.getScheduler().cancelTask(r1.getTaskId());
        } catch (IllegalStateException e) {}

        Player player = event.getPlayer();

        try {
            sendGet(event.getPlayer());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        ItemStack menuItem = new ItemStack(Material.COMMAND_BLOCK);
        ItemMeta menuItemMeta = menuItem.getItemMeta();
        menuItemMeta.setDisplayName(ChatColor.DARK_GREEN + (ChatColor.BOLD + "Menu Interface"));
        menuItem.setItemMeta(menuItemMeta);
        event.getPlayer().getInventory().setItem(8, new ItemStack(menuItem));

        username = "" + player.getUniqueId();
        lastlogout_X = player.getLocation().getX();
        lastlogout_Y = player.getLocation().getY();
        lastlogout_Z = player.getLocation().getZ();
        inventory = new BukkitSerialization().toBase64(player.getInventory());

        try {
            Bukkit.getScheduler().cancelTask(r1.getTaskId());
        } catch (IllegalStateException e) {}
        try {r1.runTaskAsynchronously(Essentials.getInstance());}catch(IllegalStateException e){}
        try {
            Bukkit.getScheduler().cancelTask(r1.getTaskId());
        } catch (IllegalStateException e) {}

    }

    private void sendGet(Player player) throws Exception
    {
        String url = "https://ccessentials.glitch.me/api/join/" + player.getName();

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection)obj.openConnection();


        con.setRequestMethod("GET");


        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();



        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        StringBuffer response = new StringBuffer();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
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
