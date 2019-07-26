package net.danielonline.Essentials.listeners;

import net.danielonline.Essentials.Essentials;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.*;

public class EntityDamageEventListener implements Listener {

    private Connection connection;

    public Player player;

    public EntityDamageEvent pe;

    public String host = "116.203.95.196";
    public int port = 3306;
    public String database = "dockerconnect";
    public String susername = "dockerconnect";
    public String password = "chiicken";

    BukkitRunnable r1 = new BukkitRunnable() {
        @Override
        public void run() {
            //This is where you should do your database interaction

            try {
                openConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Statement statement = null;
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ResultSet result = null;
            try {
                result = statement.executeQuery("SELECT * FROM corePlayers WHERE USERNAME = '" + player.getUniqueId() + "';");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    if (!result.next()) break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    if (result.getString("immortal") == "1") {

                        pe.setCancelled(true);

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

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
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, susername, password);
            }

        }

        @EventHandler
        public void onEntityDamage(EntityDamageEvent e) {

            pe = e;

            if (e.getEntityType() == EntityType.PLAYER) {

                player = (Player) e.getEntity();

                r1.runTaskAsynchronously(Essentials.getInstance());

            }

        }

    };
}

