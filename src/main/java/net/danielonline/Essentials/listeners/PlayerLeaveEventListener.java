package net.danielonline.Essentials.listeners;

import net.danielonline.Essentials.cloudcraft_core_services.SignoutHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//import litebans.api.*;

public class PlayerLeaveEventListener implements Listener {

    private final String USER_AGENT = "Mozilla/6.9";

    public PlayerLeaveEventListener() {}

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage("");
        try {
            sendGet(event.getPlayer());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        InventoryClickListener.temp = 1;

        try {
            new SignoutHandler().signOut(event.getPlayer());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*@Override
    public void entryAdded(Entry entry) {

    }*/

    private void sendGet(Player player) throws Exception
    {
        String url = "https://ccessentials.glitch.me/api/quit/" + player.getName();

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

}
