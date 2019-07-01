package net.danielonline.Essentials.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlayerJoinEventListener implements Listener {

    private final String USER_AGENT = "Mozilla/5.0";

    public PlayerJoinEventListener() {}

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
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

}
