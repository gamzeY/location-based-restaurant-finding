package com.tutorials.hp.mysqlselector.FragementActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.tutorials.hp.mysqlselector.Downloader;
import com.tutorials.hp.mysqlselector.KategoriList.Kategory2Activity;
import com.tutorials.hp.mysqlselector.KategoriList.Kategory3Activity;
import com.tutorials.hp.mysqlselector.KategoriList.KategoryActivity;
import com.tutorials.hp.mysqlselector.MainActivity;
import com.tutorials.hp.mysqlselector.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResturantFragment extends Fragment {
    String url="http://192.168.1.104/Tez/kategori.php ";
    Toolbar toolbar;
    ListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resturant, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
         toolbar.setTitle(getResources().getString(R.string.app_name));

       ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        lv = (ListView) view.findViewById(R.id.lv);

        final Downloader d = new Downloader(getActivity(), url, lv);
        d.execute();
        ArrayAdapter<String> mAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Restorantlar));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long  id) {

                if (position == 0) {
                    Intent intent = new Intent(getActivity(), KategoryActivity.class);
                    intent.putExtra("ResturantName", lv.getItemAtPosition(position).toString());
                    startActivity(intent);
                }
                if (position == 1) {

                    Intent intent = new Intent(getActivity(), Kategory2Activity.class);
                    intent.putExtra("ResturantName", lv.getItemAtPosition(position).toString());
                    startActivity(intent);
                }    if (position == 2) {

                    Intent intent = new Intent(getActivity(), Kategory3Activity.class);
                    intent.putExtra("ResturantName", lv.getItemAtPosition(position).toString());
                    startActivity(intent);
                }
            }
        });
         lv.setAdapter(mAdapter);
        return view;
    }
}
