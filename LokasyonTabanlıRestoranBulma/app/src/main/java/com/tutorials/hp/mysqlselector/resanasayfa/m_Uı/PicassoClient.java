package com.tutorials.hp.mysqlselector.resanasayfa.m_Uı;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tutorials.hp.mysqlselector.R;

/**
 * Created by Gamze on 19.03.2017.
 */
public class PicassoClient {

    public static void downloadImage(Context c, String imageUrl,ImageView image){
         if(imageUrl.length()>0&&imageUrl!=null)
         {
             Picasso.with(c).load(imageUrl).placeholder(R.drawable.start_blue).into(image);

         }else{
             Picasso.with(c).load(R.drawable.start_blue);
         }
    }
}
