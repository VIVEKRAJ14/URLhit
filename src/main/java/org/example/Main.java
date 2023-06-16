package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        URL getUrl = null;
        try {
            getUrl = new URL("https://api.zippopotam.us/us/33162");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) getUrl.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }
        int responseCode = 0;
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(responseCode == 200) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            StringBuilder jsonResponseData = new StringBuilder();
            String readLine = null;

            while (true) {
                try {
                    if (!((readLine = br.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                jsonResponseData.append(readLine);
            }
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JSONObject masterData = new JSONObject(jsonResponseData.toString());
            System.out.println(masterData);
        }else{
            System.out.println("Resource not found");
        }
    }
}