package net.danielonline.Essentials.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Pods {

    private Connection connection;
    private String host, susername, password;
    private int port;

    public void makeOnline(String id) {

        host = "116.203.95.196";
        port = 3306;
        susername = "dockerconnect";
        password = "chiicken";

        try {


            openConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM tblPods WHERE `podID` = '" + id + "';");

            double lx = 0;
            double ly = 0;
            double lz = 0;
            String ld = "";
            World w = Bukkit.getServer().getWorld("world");
            while (rs1.next()) {

                lx = Double.valueOf(rs1.getString("podX"));
                ly = Double.valueOf(rs1.getString("podY"));
                lz = Double.valueOf(rs1.getString("podZ"));
                ld = rs1.getString("podDirection");

            }

//Bukkit.broadcastMessage(String.valueOf(lx) + "." + String.valueOf(ly) + "." + String.valueOf(lz));

            Block b = new Location(w, lx + 1, ly - 1, lz).getBlock();
            Sign i = (Sign) b.getState();

            if (ld == "EAST") {

                //Bukkit.broadcastMessage(w.toString() + (lx + 2) + (ly - 1) + lz);

                //Block b = new Location(w, lx + 1, ly - 1, lz).getBlock();
                //i = (Sign) b.getState();

            } else if (ld == "WEST") {

                //Block b = new Location(w, lx - 1, ly - 1, lz).getBlock();
                //i = (Sign) b.getState();

            }

            stmt.execute("UPDATE `tblPods` SET `podColor` = 'lime' WHERE `podID` = '" + id + "';");
            stmt.execute("UPDATE `tblPods` SET `podStatusTimestamp` = '" + (System.currentTimeMillis() / 1000L) + "' WHERE `podID` = '" + id + "';");

            i.setLine(0, "§a§l«§r§aOnline§a§l»");
            java.util.Date t = new java.util.Date((Long) (System.currentTimeMillis() / 1000L) * 1000);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy | hh:mm");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            i.setLine(1, format.format(t));

            i.update();

            new Location(w, lx, ly + 1, lz).getBlock().setType(Material.LIME_STAINED_GLASS);
            new Location(w, lx, ly, lz).getBlock().setType(Material.LIME_STAINED_GLASS);


            if (ld == "EAST") {

                // new Location(w, lx + 1, ly + 1, lz).getBlock().setType(Material.LIME_STAINED_GLASS);
                //   new Location(w, lx + 1, ly, lz).getBlock().setType(Material.LIME_STAINED_GLASS);

            } else if (ld == "WEST") {

                // new Location(w, lx - 1, ly + 1, lz).getBlock().setType(Material.LIME_STAINED_GLASS);
                //    new Location(w, lx - 1, ly, lz).getBlock().setType(Material.LIME_STAINED_GLASS);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void makeOffline(String id) {

        host = "116.203.95.196";
        port = 3306;
        susername = "dockerconnect";
        password = "chiicken";

        try {


            openConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM tblPods WHERE `podID` = '" + id + "';");

            double lx = 0;
            double ly = 0;
            double lz = 0;
            String ld = "";
            World w = Bukkit.getServer().getWorld("world");
            while (rs1.next()) {

                lx = Double.valueOf(rs1.getString("podX"));
                ly = Double.valueOf(rs1.getString("podY"));
                lz = Double.valueOf(rs1.getString("podZ"));
                ld = rs1.getString("podDirection");

            }

            //Bukkit.broadcastMessage(String.valueOf(lx + 1) + "." + String.valueOf(ly - 1) + "." + String.valueOf(lz));

            Block b = new Location(w, (lx + 1), (ly - 1), lz).getBlock();
            Sign i = (Sign) b.getState();

            if (ld == "EAST") {

                //Bukkit.broadcastMessage(w.toString() + (lx + 2) + (ly - 1) + lz);

                //Block b = new Location(w, lx + 2, ly - 1, lz).getBlock();
                //i = (Sign) b.getState();

            } else if (ld == "WEST") {

                //Block b = new Location(w, lx - 2, ly - 1, lz).getBlock();
                //i = (Sign) b.getState();

            }

            stmt.execute("UPDATE `tblPods` SET `podColor` = 'red' WHERE `podID` = '" + id + "';");
            stmt.execute("UPDATE `tblPods` SET `podStatusTimestamp` = '" + (System.currentTimeMillis() / 1000L) + "' WHERE `podID` = '" + id + "';");

            i.setLine(0, "§4§l«§r§4Offline§4§l»");
            java.util.Date t = new java.util.Date((Long) (System.currentTimeMillis() / 1000L) * 1000);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy | hh:mm");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            i.setLine(1, format.format(t));

            i.update();

            new Location(w, lx, ly + 1, lz).getBlock().setType(Material.RED_STAINED_GLASS);
            new Location(w, lx, ly, lz).getBlock().setType(Material.RED_STAINED_GLASS);

            if (ld == "EAST") {

                // new Location(w, lx + 1, ly + 1, lz).getBlock().setType(Material.RED_STAINED_GLASS);
                // new Location(w, lx + 1, ly, lz).getBlock().setType(Material.RED_STAINED_GLASS);

            } else if (ld == "WEST") {

                // new Location(w, lx - 1, ly + 1, lz).getBlock().setType(Material.RED_STAINED_GLASS);
                // new Location(w, lx - 1, ly, lz).getBlock().setType(Material.RED_STAINED_GLASS);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void makePending(String id) {

        host = "116.203.95.196";
        port = 3306;
        susername = "dockerconnect";
        password = "chiicken";

        try {


            openConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM tblPods WHERE `podID` = '" + id + "';");

            double lx = 0;
            double ly = 0;
            double lz = 0;
            String ld = "";
            World w = Bukkit.getServer().getWorld("world");
            while (rs1.next()) {

                lx = Double.valueOf(rs1.getString("podX"));
                ly = Double.valueOf(rs1.getString("podY"));
                lz = Double.valueOf(rs1.getString("podZ"));
                ld = rs1.getString("podDirection");

            }

//Bukkit.broadcastMessage(String.valueOf(lx) + "." + String.valueOf(ly) + "." + String.valueOf(lz));

            Block b = new Location(w, lx + 1, ly - 1, lz).getBlock();
            Sign i = (Sign) b.getState();

            if (ld == "EAST") {

                //Bukkit.broadcastMessage(w.toString() + (lx + 2) + (ly - 1) + lz);

                //Block b = new Location(w, lx + 1, ly - 1, lz).getBlock();
                //i = (Sign) b.getState();

            } else if (ld == "WEST") {

                //Block b = new Location(w, lx - 1, ly - 1, lz).getBlock();
                //i = (Sign) b.getState();

            }

            stmt.execute("UPDATE `tblPods` SET `podColor` = 'orange' WHERE `podID` = '" + id + "';");
            stmt.execute("UPDATE `tblPods` SET `podStatusTimestamp` = '" + (System.currentTimeMillis() / 1000L) + "' WHERE `podID` = '" + id + "';");

            i.setLine(0, "§6§l«§r§6Waiting§6§l»");
            java.util.Date t = new java.util.Date((Long) (System.currentTimeMillis() / 1000L) * 1000);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy | hh:mm");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            i.setLine(1, format.format(t));

            i.update();

            new Location(w, lx, ly + 1, lz).getBlock().setType(Material.ORANGE_STAINED_GLASS);
            new Location(w, lx, ly, lz).getBlock().setType(Material.ORANGE_STAINED_GLASS);


            if (ld == "EAST") {

                // new Location(w, lx + 1, ly + 1, lz).getBlock().setType(Material.LIME_STAINED_GLASS);
                //   new Location(w, lx + 1, ly, lz).getBlock().setType(Material.LIME_STAINED_GLASS);

            } else if (ld == "WEST") {

                // new Location(w, lx - 1, ly + 1, lz).getBlock().setType(Material.LIME_STAINED_GLASS);
                //    new Location(w, lx - 1, ly, lz).getBlock().setType(Material.LIME_STAINED_GLASS);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void makeDead(String id) {

        host = "116.203.95.196";
        port = 3306;
        susername = "dockerconnect";
        password = "chiicken";

        try {


            openConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM tblPods WHERE `podID` = '" + id + "';");

            double lx = 0;
            double ly = 0;
            double lz = 0;
            String ld = "";
            World w = Bukkit.getServer().getWorld("world");
            while (rs1.next()) {

                lx = Double.valueOf(rs1.getString("podX"));
                ly = Double.valueOf(rs1.getString("podY"));
                lz = Double.valueOf(rs1.getString("podZ"));
                ld = rs1.getString("podDirection");

            }

//Bukkit.broadcastMessage(String.valueOf(lx) + "." + String.valueOf(ly) + "." + String.valueOf(lz));

            Block b = new Location(w, lx + 1, ly - 1, lz).getBlock();
            Sign i = (Sign) b.getState();

            if (ld == "EAST") {

                //Bukkit.broadcastMessage(w.toString() + (lx + 2) + (ly - 1) + lz);

                //Block b = new Location(w, lx + 1, ly - 1, lz).getBlock();
                //i = (Sign) b.getState();

            } else if (ld == "WEST") {

                //Block b = new Location(w, lx - 1, ly - 1, lz).getBlock();
                //i = (Sign) b.getState();

            }

            stmt.execute("UPDATE `tblPods` SET `podColor` = 'black' WHERE `podID` = '" + id + "';");
            stmt.execute("UPDATE `tblPods` SET `podStatusTimestamp` = '" + (System.currentTimeMillis() / 1000L) + "' WHERE `podID` = '" + id + "';");

            i.setLine(0, "§0§l«§r§0Dead§0§l»");
            java.util.Date t = new java.util.Date((Long) (System.currentTimeMillis() / 1000L) * 1000);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy | hh:mm");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            i.setLine(1, format.format(t));

            i.update();

            new Location(w, lx, ly + 1, lz).getBlock().setType(Material.BLACK_STAINED_GLASS);
            new Location(w, lx, ly, lz).getBlock().setType(Material.BLACK_STAINED_GLASS);


            if (ld == "EAST") {

                // new Location(w, lx + 1, ly + 1, lz).getBlock().setType(Material.LIME_STAINED_GLASS);
                //   new Location(w, lx + 1, ly, lz).getBlock().setType(Material.LIME_STAINED_GLASS);

            } else if (ld == "WEST") {

                // new Location(w, lx - 1, ly + 1, lz).getBlock().setType(Material.LIME_STAINED_GLASS);
                //    new Location(w, lx - 1, ly, lz).getBlock().setType(Material.LIME_STAINED_GLASS);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
            connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + "essentials", this.susername, this.password);
        }

    }

}
