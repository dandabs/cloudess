package net.danielonline.Essentials.cloudcraft_core_services;

import net.danielonline.Essentials.Essentials;
import net.danielonline.Essentials.external.SQL;
import net.danielonline.Essentials.utils.BukkitSerialization;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import javax.net.ssl.ExtendedSSLSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SignoutHandler {

    private String username;
    private boolean banned;
    private double lastlogout_X;
    private double lastlogout_Y;
    private double lastlogout_Z;
    private String inventory;

    private Connection connection;
    private String host, database, susername, password;
    private int port;

    BukkitRunnable r1 = new BukkitRunnable() {
        @Override
        public void run() {
            //This is where you should do your database interaction

            host = "eu-sql.pebblehost.com";
            port = 3306;
            database = "customer_74746";
            susername = "customer_74746";
            password = "5f84734369";

            try {
                openConnection();
                Statement statement = connection.createStatement();
                statement.executeUpdate("DELETE FROM corePlayers WHERE USERNAME = '" + username + "';");
                statement.executeUpdate("INSERT INTO corePlayers (USERNAME, BANNED, LASTLOGOUT_X, LASTLOGOUT_Y, LASTLOGOUT_Z, INVENTORY) VALUES ('" + username + "', '" + banned + "', '" + (int) lastlogout_X + "', '" + (int) lastlogout_Y + "', '" +(int) lastlogout_Z + "', '" + inventory + "');");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    };

    public void signOut(Player player) {

        if (new serverCore().init(357461)) {

            // warn user to empty all important items from their inventory

            username = "" + player.getUniqueId();
            banned = false;
            lastlogout_X = player.getLocation().getX();
            lastlogout_Y = player.getLocation().getY();
            lastlogout_Z = player.getLocation().getZ();
            inventory = new BukkitSerialization().toBase64(player.getInventory());

            r1.runTaskAsynchronously(Essentials.getInstance());

            // teleport user to a safeplace

            // Remove player from server.
            player.getInventory().clear();
            player.updateInventory();
            player.getInventory().setArmorContents(null);
            player.kickPlayer("Successfully detached from the CloudCraft Mainframe. \n Have a nice day!");

        }

    }

    public void signIn(Player player) {

        try {
            new SQL().openConnection();
            Statement statement = new SQL().connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM corePlayers WHERE USERNAME = " + player.getName() + ");");
            while (result.next()) {

                String username = result.getString("USERNAME");
                String banned = result.getString("BANNED");
                String lastlogout_X = result.getString("LASTLOGOUT_X");
                String lastlogout_Y = result.getString("LASTLOGOUT_Y");
                String lastlogout_Z = result.getString("LASTLOGOUT_Z");
                Inventory inventory = BukkitSerialization.fromBase64(result.getString("INVENTORY"));

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // check if player is banned

        // ask player what they want to do (eg. log on, log off)

        // if log on, reassign inventory, create location, teleport etc.

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
