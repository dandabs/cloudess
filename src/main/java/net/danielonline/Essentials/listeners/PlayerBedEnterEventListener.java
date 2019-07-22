package net.danielonline.Essentials.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlayerBedEnterEventListener implements Listener {

    private final String USER_AGENT = "Mozilla/6.9";

    public PlayerBedEnterEventListener() {}

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        try {
            sendGet(event.getPlayer());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendGet(Player player) throws Exception
    {
        String url = "https://ccessentials.glitch.me/api/inbed/" + player.getName();

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection)obj.openConnection();


        con.setRequestMethod("GET");


        con.setRequestProperty("User-Agent", "Mozilla/6.9");





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
