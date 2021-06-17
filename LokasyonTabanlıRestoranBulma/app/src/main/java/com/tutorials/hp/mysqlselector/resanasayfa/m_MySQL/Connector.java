package com.tutorials.hp.mysqlselector.resanasayfa.m_MySQL;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gamze on 19.03.2017.
 */
public class Connector {

 public static HttpURLConnection connection(String urlAddress) {

     try {
         URL url = new URL(urlAddress);
         HttpURLConnection con = (HttpURLConnection) url.openConnection();
         con.setRequestMethod("GET");
         con.setConnectTimeout(200000);
         con.setReadTimeout(200000);
         con.setDoInput(true);
         return con;
     } catch (MalformedURLException e) {
         e.printStackTrace();
     } catch (IOException e) {
         e.printStackTrace();
     }
         return null;
     }
 }
