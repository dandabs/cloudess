package net.danielonline.Essentials.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlayerChatEventListener implements Listener {

    private final String USER_AGENT = "Mozilla/6.9";

    public PlayerChatEventListener() {}

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) { Player player = event.getPlayer();
        try {
            sendGet(player, event.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("player has chatted");
    }





    private void sendGet(Player player, String message)
            throws Exception
    {
        String url = "https://ccessentials.glitch.me/api/message/" + player.getName() + "/" + message;

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
