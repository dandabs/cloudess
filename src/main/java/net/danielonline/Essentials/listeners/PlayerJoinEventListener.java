package net.danielonline.Essentials.listeners;

//import com.sun.tools.javac.util.Convert;
import net.danielonline.Essentials.Essentials;
import net.danielonline.Essentials.cloudcraft_core_services.SignoutHandler;
import net.danielonline.Essentials.external.SQL;
import net.danielonline.Essentials.utils.BukkitSerialization;
import net.danielonline.Essentials.utils.Songs;
import net.mcjukebox.plugin.bukkit.api.JukeboxAPI;
import net.mcjukebox.plugin.bukkit.api.ResourceType;
import net.mcjukebox.plugin.bukkit.api.models.Media;
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
import org.bukkit.scoreboard.Team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;

public class PlayerJoinEventListener implements Listener {

    private final String USER_AGENT = "Mozilla/6.9";

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

    private String o_username;
    private String o_banned;
    private String o_x;
    private String o_y;
    private String o_z;
    private Inventory o_inventory;

    BukkitRunnable r1 = new BukkitRunnable() {
        @Override
        public void run() {

            //This is where you should do your database interaction

            host = "116.203.95.196";
            port = 3306;
            database = "essentials";
            susername = "dockerconnect";
            password = "chiicken";

            verification = 0;

            try {
                openConnection();
                Statement statement = connection.createStatement();

                ResultSet result = statement.executeQuery("SELECT * FROM corePlayers WHERE USERNAME = '" + username + "';");
                while (result.next()) {

                    String username = result.getString("USERNAME");
                    o_username = result.getString("USERNAME");
                    o_banned = result.getString("BANNED");
                    o_x = result.getString("LASTLOGOUT_X");
                    o_y = result.getString("LASTLOGOUT_Y");
                    o_z = result.getString("LASTLOGOUT_Z");
                    //o_inventory = BukkitSerialization.fromBase64(result.getString("INVENTORY"));
                    verification = 1;

                }

                if (verification == 0) {

                    try {
                        openConnection();
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate("INSERT INTO corePlayers (USERNAME, BANNED, LASTLOGOUT_X, LASTLOGOUT_Y, LASTLOGOUT_Z) VALUES ('" + username + "', '" + "false" + "', '" + (int) lastlogout_X + "', '" + (int) lastlogout_Y + "', '" + (int) lastlogout_Z + "');");
                        o_username = username;
                        o_banned = "false";
                        o_x = Double.toString(lastlogout_X);
                        o_y = Double.toString(lastlogout_Y);
                        o_z = Double.toString(lastlogout_Z);
                        //o_inventory = BukkitSerialization.fromBase64(inventory);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } //catch (IOException e) {
                      //  e.printStackTrace();
                    //}

                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
           // } catch (IOException e) {
               // e.printStackTrace();
            }

            Bukkit.getScheduler().cancelTask(r1.getTaskId());

        }
    };

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {

        Media media = new Media(ResourceType.MUSIC, Songs.SAO_SWORDLAND);
        media.setVolume(50);
        JukeboxAPI.play(event.getPlayer(), media);

        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "jukebox music " + event.getPlayer().getName() + " " + Songs.SAO_SWORDLAND + "");

        event.setJoinMessage("");

        try {
            Bukkit.getScheduler().cancelTask(r1.getTaskId());
        } catch (IllegalStateException e) {}

        Player player = event.getPlayer();

        //Essentials.getTeam().addPlayer(player);

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
        //event.getPlayer().getInventory().setItem(8, new ItemStack(menuItem));

        username = "" + player.getUniqueId();
        lastlogout_X = player.getLocation().getX();
        lastlogout_Y = player.getLocation().getY();
        lastlogout_Z = player.getLocation().getZ();
        //inventory = new BukkitSerialization().toBase64(player.getInventory());

        try {
            Bukkit.getScheduler().cancelTask(r1.getTaskId());
        } catch (IllegalStateException e) {}
        try {r1.runTaskAsynchronously(Essentials.getInstance());}catch(IllegalStateException e){}
        try {
            Bukkit.getScheduler().cancelTask(r1.getTaskId());
        } catch (IllegalStateException e) {}

        new SignoutHandler().attemptSignIn(player, o_username, o_banned, o_x, o_y, o_z, o_inventory);

    }

    private void sendGet(Player player) throws Exception
    {
        // https://stackoverflow.com/a/25031189/10160296 for player count //
        String url = "https://ccessentials.glitch.me/api/join/" + player.getName() + "/" + Bukkit.getServer().getOnlinePlayers().size();

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection)obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/6.9");

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
