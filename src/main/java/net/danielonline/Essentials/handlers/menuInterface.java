package net.danielonline.Essentials.handlers;

import net.danielonline.Essentials.utils.G;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.sql.*;
import java.util.Arrays;

public class menuInterface implements InventoryHolder, Listener {

    private final Inventory inv;

    public menuInterface() {
        inv = Bukkit.createInventory(this, 18, ChatColor.DARK_GRAY + (ChatColor.BOLD + "Menu Interface"));
        initializeItems();
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    // You can call this whenever you want to put the items in
    public void initializeItems() {
        //inv.addItem(new G().createGuiItem(Material, "Example Sword", Arrays.asList("This is an example!", "With description")));

        inv.setItem(13, new G().createGuiItem(Material.BARRIER, "§4Log Out", Arrays.asList("§aoof", "§fbigger oof")));
    }

    // You can open the inventory with this
    public void openInventory(Player p) {

        try {
            // create our mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://116.203.95.196/dockerconnect";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "dockerconnect", "chiicken");

            // our SQL SELECT query.
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM corePlayers WHERE username = '" + p.getUniqueId() + "'";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {

                String immortal = rs.getString("immortal");

                if (immortal == "1") {

                    inv.setItem(0, new G().createGuiItem(Material.PURPLE_DYE, "§5Immortal Object", Arrays.asList("§r")));


                } else {

                    inv.setItem(0, new G().createGuiItem(Material.ROSE_RED, "§4Mortal Object", Arrays.asList("§r")));

                }

            }
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }




        p.openInventory(inv);


    }

}