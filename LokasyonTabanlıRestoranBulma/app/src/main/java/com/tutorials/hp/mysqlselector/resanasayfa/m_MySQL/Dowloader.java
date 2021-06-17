package com.tutorials.hp.mysqlselector.resanasayfa.m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

/**
 * Created by Gamze on 19.03.2017.
 */
public class Dowloader extends AsyncTask<Void,Void,String> {

    Context c;
    String urlAddress;
    RecyclerView rv;
    ProgressDialog pd;
    public Dowloader(Context c, String urlAddress, RecyclerView rv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.rv = rv;
    }
    //B4 JOB STARTS
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
       // pd.setTitle("Fetch Data");
        pd.setMessage("Lütfen Bekleyin...");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        String data=downloadData();
        return data;
    }
    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        pd.dismiss();

        if(jsonData==null)
        {
          Toast.makeText(c,"Başarısız,veri yok",Toast.LENGTH_SHORT).show();
        }else
        {
         DataParser parser=new DataParser(c,jsonData,rv);
            parser.execute();
        }
    }
    private String downloadData()
    {
       HttpURLConnection con = Connector.connection(urlAddress);

        if(con==null)
        {
            return null;
        }
        try {
            InputStream is=new BufferedInputStream(con.getInputStream());

            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer jsonData=new StringBuffer();


                while ((line=br.readLine()) != null) {
                    jsonData.append(line+"\n");
                }

            br.close();
            is.close();
            return jsonData.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

