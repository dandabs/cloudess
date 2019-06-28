package net.danielonline.Essentials;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlayerLeaveEventListener implements Listener {

    private final String USER_AGENT = "Mozilla/5.0";

    public PlayerLeaveEventListener() {}

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        try {
            sendGet(event.getPlayer());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendGet(Player player) throws Exception
    {
        String url = "https://ccessentials.glitch.me/api/quit/" + player.getName();

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
