package com.tutorials.hp.mysqlselector.resanasayfa.m_Uı;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.tutorials.hp.mysqlselector.R;

/**
 * Created by Gamze on 19.03.2017.
 */
public class myHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView nametxt;
    ImageView img;
    ItemClickListener itemClickListener;

    public myHolder(View itemView) {
        super(itemView);

        nametxt=(TextView)itemView.findViewById(R.id.nameText);
        img=(ImageView)itemView.findViewById(R.id.movieImage);

        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

     this.itemClickListener.onItemClick();
    }
    public void setItemClickListener (ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }
}
