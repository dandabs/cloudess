package net.danielonline.Essentials.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class W {

    public static void stopGet()
            throws Exception
    {
        String url = "https://ccessentials.glitch.me/api/stop";

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

    public static void startGet()
            throws Exception
    {
        String url = "https://ccessentials.glitch.me/api/start";

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
