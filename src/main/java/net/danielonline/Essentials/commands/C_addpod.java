package net.danielonline.Essentials.commands;

import net.danielonline.Essentials.Essentials;
import net.danielonline.Essentials.utils.BukkitSerialization;
import net.danielonline.Essentials.utils.LocationSerialization;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class C_addpod implements CommandExecutor {

    private Connection connection;
    private String host, database, susername, password;
    private int port;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Player p = (Player) sender;

        if (getFacing(p.getLocation().getYaw()) == "EAST") {

            Location l = p.getLocation();
            double lx = l.getX();
            double ly = l.getY();
            double lz = l.getZ();
            World w = l.getWorld();
            Block b = new Location(w, lx + 2, ly - 1, lz).getBlock();
            Sign i = (Sign) b.getState();

            //Bukkit.broadcastMessage(b.getType().toString());
            //Bukkit.broadcastMessage(b.getLocation().getBlockX() + ":" + b.getLocation().getBlockY() + ":" + b.getLocation().getBlockZ());

            host = "116.203.95.196";
            port = 3306;
            database = "essentials";
            susername = "dockerconnect";
            password = "chiicken";

            List<String> sl = null;
            String newid = randomAlphaNumeric(10);

            try {
                openConnection();
                Statement statement = connection.createStatement();

                ResultSet result = statement.executeQuery("SELECT * FROM tblPods;");

                statement.execute("INSERT INTO `tblPods` (`podId`, `podColor`, `podStatusTimestamp`, `podCreatedTimestamp`, `podX`, `podY`, `podZ`, `podDirection`) VALUES ('" + newid + "', '" + "grey" + "', '" + (System.currentTimeMillis() / 1000L) + "', '" + (System.currentTimeMillis() / 1000L) + "', '" + p.getLocation().getX() + "', '" + p.getLocation().getY() + "', '" + p.getLocation().getZ() + "', '" + "EAST" + "')");

                i.setLine(0, "§8§l«§r§8Vacant§8§l»");
                //i.setLine(1, "28/07/19 | 14:15");
                java.util.Date t = new java.util.Date((Long) (System.currentTimeMillis() / 1000L) * 1000);
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy | hh:mm");

                //i.setLine(1, t.getDay() + "/" + t.getMonth() + "/" + t.getYear() + " | " + t.getHours() + ":" + t.getMinutes());
                i.setLine(1, format.format(t));
                i.setLine(3, "| " + newid + " |");
                i.update();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            new Location(w, lx, ly + 1, lz).getBlock().setType(Material.WATER);

            new Location(w, lx + 1, ly + 1, lz).getBlock().setType(Material.GRAY_STAINED_GLASS);
            new Location(w, lx + 1, ly, lz).getBlock().setType(Material.GRAY_STAINED_GLASS);

        } else if (getFacing(p.getLocation().getYaw()) == "WEST") {

            Location l = p.getLocation();
            double lx = l.getX();
            double ly = l.getY();
            double lz = l.getZ();
            World w = l.getWorld();
            Block b = new Location(w, lx - 2, ly - 1, lz).getBlock();
            Sign i = (Sign) b.getState();

            //Bukkit.broadcastMessage(b.getType().toString());
            //Bukkit.broadcastMessage(b.getLocation().getBlockX() + ":" + b.getLocation().getBlockY() + ":" + b.getLocation().getBlockZ());

            host = "116.203.95.196";
            port = 3306;
            database = "essentials";
            susername = "dockerconnect";
            password = "chiicken";

            List<String> sl = null;
            String newid = randomAlphaNumeric(10);

            try {
                openConnection();
                Statement statement = connection.createStatement();

                ResultSet result = statement.executeQuery("SELECT * FROM tblPods;");

                statement.execute("INSERT INTO `tblPods` (`podId`, `podColor`, `podStatusTimestamp`, `podCreatedTimestamp`, `podX`, `podY`, `podZ`, `podDirection`) VALUES ('" + newid + "', '" + "grey" + "', '" + (System.currentTimeMillis() / 1000L) + "', '" + (System.currentTimeMillis() / 1000L) + "', '" + p.getLocation().getX() + "', '" + p.getLocation().getY() + "', '" + (p.getLocation().getZ() - 1) + "', '" + "WEST" + "');");

                i.setLine(0, "§8§l«§r§8Vacant§8§l»");
                //i.setLine(1, "28/07/19 | 14:15");
                java.util.Date t = new java.util.Date((Long) (System.currentTimeMillis() / 1000L) * 1000);
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy | hh:mm");

                //i.setLine(1, t.getDay() + "/" + t.getMonth() + "/" + t.getYear() + " | " + t.getHours() + ":" + t.getMinutes());
                i.setLine(1, format.format(t));
                i.setLine(3, "| " + newid + " |");
                i.update();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            new Location(w, lx, ly + 1, lz).getBlock().setType(Material.WATER);

            new Location(w, lx - 1, ly + 1, lz).getBlock().setType(Material.GRAY_STAINED_GLASS);
            new Location(w, lx - 1, ly, lz).getBlock().setType(Material.GRAY_STAINED_GLASS);

        } else p.sendMessage("wtf you trynna do m8");

        return true;

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

    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyz0123456789";

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static final BlockFace[] axis = {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
    public static final BlockFace[] radial = {BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST};


    private static String getFacing(float yaw) {
        BlockFace bf = yawToFace(yaw);
        if (bf.equals(BlockFace.NORTH)) {
            return "SOUTH";
        } else if (bf.equals(BlockFace.EAST)) {
            return "WEST";
        } else if (bf.equals(BlockFace.SOUTH)) {
            return "NORTH";
        } else
            return "EAST";
    }

    public static BlockFace yawToFace(float yaw, boolean useSubCardinalDirections) {
        if (useSubCardinalDirections) {
            return radial[Math.round(yaw / 45f) & 0x7];
        } else {
            return axis[Math.round(yaw / 90f) & 0x3];
        }
    }

    public static BlockFace yawToFace(float yaw) {
        return yawToFace(yaw, false);
    }

}
