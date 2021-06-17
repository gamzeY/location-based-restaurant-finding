package com.tutorials.hp.mysqlselector.resanasayfa.m_Uı;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.tutorials.hp.mysqlselector.FragementActivity.HomeSecondActivity;
import com.tutorials.hp.mysqlselector.R;
import com.tutorials.hp.mysqlselector.resanasayfa.m_DataObject.Spececraft;

import java.util.ArrayList;

/**
 * Created by Gamze on 19.03.2017.
 */
public class CustomAdapter extends RecyclerView.Adapter<myHolder> {

    Context c;
    ArrayList<Spececraft> spececrafts;

    public CustomAdapter(Context c, ArrayList<Spececraft> spececrafts) {
        this.c = c;
        this.spececrafts = spececrafts;
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, parent, false);
        return new myHolder(v);
    }
    @Override
    public void onBindViewHolder(myHolder holder, int position) {
        final Spececraft s = spececrafts.get(position);
        holder.nametxt.setText(s.getName());
        //image
        PicassoClient.downloadImage(c, s.getImageUrl(), holder.img);

        //item CliCK

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick() {
                OpenSecondActivity(s.getName(), s.getAciklama(),s.getAdres(),s.getImageUrl(),s.getNumber());
            }
        });
    }
    @Override
    public int getItemCount() {
        return spececrafts.size();
    }
    public void OpenSecondActivity(String name,String aciklama,String adres,String imageUrl,Integer number )
    {
        Intent i=new Intent (c,HomeSecondActivity.class);

        //Pack data
        i.putExtra("NAME_KEY",name);
        i.putExtra("ACIKLAMA_KEY",aciklama);
        i.putExtra("ADRES_KEY",adres);
        i.putExtra("IMAGE_KEY",imageUrl);
        i.putExtra("NUMBER",number);

        c.startActivity(i);
    }
}