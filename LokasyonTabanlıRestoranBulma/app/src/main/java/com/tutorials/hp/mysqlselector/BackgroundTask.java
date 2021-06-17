package com.tutorials.hp.mysqlselector;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Gamze on 23.12.2016.
 */
public class BackgroundTask extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;

    Context ctx;

    BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        //alertDialog=new AlertDialog.Builder(ctx).create();
        //  alertDialog.setTitle("Giris Bilgi");

    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://192.168.1.104/Tez/register.php";
        String login_url = "http://192.168.1.104/Tez/login.php";
        String method = params[0];
        if (method.equals("register")) {
            String isim = params[1];
            String email = params[2];
            String sifre = params[3];

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("isim", "UTF-8") + "=" + URLEncoder.encode(isim, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("sifre", "UTF-8") + "=" + URLEncoder.encode(sifre, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream ıs = httpURLConnection.getInputStream();
                ıs.close();
                return "Kayit Basarili...";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("login")) {
            String login_name = params[1];
            String login_pass = params[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("login_name", "UTF-8") + "=" + URLEncoder.encode(login_name, "UTF-8") + "&" +
                        URLEncoder.encode("login_pass", "UTF-8") + "=" + URLEncoder.encode(login_pass, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
      //  if (result.equals("Kayit Basarili...")) {
            //Toast.makeText(ctx,"Kayit oldunuz...",Toast.LENGTH_LONG).show();
        if (result.equals("welcome")) {

            Intent intent = new Intent(ctx, Navigation.class);
            ctx.startActivity(intent);


            Toast.makeText(ctx, "Giris Basarili", Toast.LENGTH_LONG).show();
            //alertDialog.setMessage(result);
            //alertDialog.show();


        }// else {
        //if(result.equals("welcome")) {
        // Toast.makeText(ctx,"Giris Basarili",Toast.LENGTH_LONG).show();


        else if (result.equals("fail"))
        {
            Toast.makeText(ctx,"Kullanici Adi ya da Sifre Hatali",Toast.LENGTH_LONG).show();
        }
    }
}
