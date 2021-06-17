package com.tutorials.hp.mysqlselector.NavigationMenu;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Gamze on 30.04.2017.
 */
public class MySingletion {
    private static MySingletion mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;
    private MySingletion (Context context)
    {
        mCtx=context;
        requestQueue=getRequestQueue();
    }
    private  RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
            return requestQueue;

    }
        public static synchronized MySingletion getInstance (Context context)
    {
        if(mInstance==null)
        {
            mInstance=new MySingletion(context);

          }
        return  mInstance;
    }
    public<T> void  addToRequestQue(Request<T> request)
    {
        getRequestQueue().add(request);
    }
}

