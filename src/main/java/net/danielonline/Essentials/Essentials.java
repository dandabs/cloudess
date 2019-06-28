package net.danielonline.Essentials;

import net.danielonline.Essentials.utils.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import me.edge209.OnTime.OnTimeAPI;

import static org.bukkit.Bukkit.getConsoleSender;

public class Essentials extends JavaPlugin {

    @Override
    public void onEnable() {
        //getLogger().info("onEnable is called!");
        getConsoleSender().sendMessage(C.Aqua + "Cloud" + C.DAqua + "ess" + C.White + " has been enabled!");
        registerEvents();
        try {
            registerWeb();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            startGet();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        //getLogger().info("onDisable is called!");
        getConsoleSender().sendMessage(C.Aqua + "Cloud" + C.DAqua + "ess" + C.White + " has been disabled!");
        try {
            stopGet();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final String USER_AGENT = "Mozilla/5.0";
    public Essentials() {}
    public static Map<String, Object> map = new HashMap();

    public class TestHandler implements HttpHandler { public TestHandler() {}

        public void handle(HttpExchange exc) throws IOException { String response = "This is the reponse";
            exc.sendResponseHeaders(200, response.length());
            Map<String, String> params = Essentials.queryToMap(exc.getRequestURI().getQuery());
            //System.out.println("Username:" + (String)params.get("u"));
            //System.out.println("Rank:" + (String)params.get("r"));
            //System.out.println("Message:" + (String)params.get("m"));
            Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "Discord" + ChatColor.GRAY + " | " + ChatColor.DARK_AQUA + (String)params.get("r") + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + (String)params.get("u") + ChatColor.WHITE + " » " + ChatColor.WHITE + (String)params.get("m"));
            //System.out.println(ChatColor.AQUA + "[" + ChatColor.DARK_PURPLE + "Discord" + ChatColor.AQUA + " | " + ChatColor.RED + (String)params.get("r") + ChatColor.DARK_PURPLE + "] " + ChatColor.GOLD + (String)params.get("u") + ChatColor.AQUA + " >> " + ChatColor.WHITE + (String)params.get("m"));

            OutputStream os = exc.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public class TestHandler2 implements HttpHandler { public TestHandler2() {}

        public void handle(HttpExchange exc) throws IOException { String response = "This is the reponse";
            exc.sendResponseHeaders(200, response.length());

            Map<String, String> params = Essentials.queryToMap(exc.getRequestURI().getQuery());
            System.out.println("Username:" + (String)params.get("u"));

            if (OnTimeAPI.playerHasOnTimeRecord(params.get("u")) == true) {

                response = Long.toString(OnTimeAPI.getPlayerTimeData(params.get("u"), OnTimeAPI.data.MONTHPLAY));

            } else {

                response = "error";

            }

            OutputStream os = exc.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public static Map<String, String> queryToMap(String query)
    {
        Map<String, String> result = new HashMap();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }

    public void registerEvents()
    {
        getServer().getPluginManager().registerEvents(new PlayerChatEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerBedEnterEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerBedLeaveEventListener(), this);
    }

    public void registerWeb() throws IOException
    {
        HttpServer server = HttpServer.create(new InetSocketAddress(8118), 0);
        server.createContext("/test", new TestHandler());
        server.createContext("/time", new TestHandler2());
        server.setExecutor(null);
        server.start();
    }

    private void startGet()
            throws Exception
    {
        String url = "https://ccessentials.glitch.me/api/start";

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



    private void stopGet()
            throws Exception
    {
        String url = "https://ccessentials.glitch.me/api/stop";

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