package com.tutorials.hp.mysqlselector.resanasayfa.m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import com.tutorials.hp.mysqlselector.resanasayfa.m_DataObject.Spececraft;
import com.tutorials.hp.mysqlselector.resanasayfa.m_Uı.CustomAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by Gamze on 19.03.2017.
 */
public class DataParser extends AsyncTask<Void,Void,Boolean> {

    Context c;
    String jsondata;
    RecyclerView rv;
    ArrayList<Spececraft> spececrafts = new ArrayList<>();
    ProgressDialog pd;
    public DataParser(Context c, String jsondata, RecyclerView rv) {
        this.c = c;
        this.jsondata =jsondata;
        this.rv = rv;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
      //  pd.setTitle("Parser");
        pd.setMessage("Lütfen Bekleyin...");
        pd.show();
    }
    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseData();
    }
    @Override
    protected void onPostExecute(Boolean parsed) {
        super.onPostExecute(parsed);
         pd.dismiss();
        if (parsed) {
            //bind
            CustomAdapter adapter=new CustomAdapter(c,spececrafts);
            rv.setAdapter(adapter);

        } else {
            Toast.makeText(c, "Bulunamadı", Toast.LENGTH_SHORT).show();
        }
        pd.dismiss();
    }
    //PARSE RECEIVED DATA
    private boolean parseData() {
        try {
            //ADD THAT DATA TO JSON ARRAY FIRST
            JSONArray ja = new JSONArray(jsondata);

            //CREATE JO OBJ TO HOLD A SINGLE ITEM
            JSONObject jo = null;

            spececrafts.clear();
            Spececraft spececraft;
            //LOOP THRU ARRAY
            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);

                int id=jo.getInt("id");
                int number=jo.getInt("number");
                //RETRIOEVE NAME
                String name = jo.getString("adi");
                String aciklama=jo.getString("aciklama");
                String adres=jo.getString("adres");
                String imageUrl=jo.getString("image");
                spececraft=new Spececraft();
                spececraft.setId(id);
                spececraft.setNumber(number);
                spececraft.setName(name);
                spececraft.setAciklama(aciklama);
                spececraft.setAdres(adres);
                spececraft.setImageUrl(imageUrl);

                //ADD IT TO OUR ARRAYLIST
                spececrafts.add(spececraft);
            }
            return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}