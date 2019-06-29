package net.danielonline.Essentials.utils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import me.edge209.OnTime.OnTimeAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Map;

public class H {

    public static void registerWeb() throws IOException
    {
        HttpServer server = HttpServer.create(new InetSocketAddress(8118), 0);
        server.createContext("/test", new TestHandler());
        server.createContext("/time", new TestHandler2());
        server.setExecutor(null);
        server.start();
    }

    public static class TestHandler implements HttpHandler { public TestHandler() {}

        public void handle(HttpExchange exc) throws IOException { String response = "This is the reponse";
            exc.sendResponseHeaders(200, response.length());
            Map<String, String> params = R.queryToMap(exc.getRequestURI().getQuery());
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

    public static class TestHandler2 implements HttpHandler { public TestHandler2() {}

        public void handle(HttpExchange exc) throws IOException { String response = "This is the reponse";
            exc.sendResponseHeaders(200, response.length());

            Map<String, String> params = R.queryToMap(exc.getRequestURI().getQuery());
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

}
