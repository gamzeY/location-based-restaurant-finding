package com.tutorials.hp.mysqlselector.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
/**
 * Created by Gamze on 15.02.2017.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter{
    int nunTabs;
    ArrayList<Fragment>fragements=new ArrayList<>();
    ArrayList<String>tabTitles=new ArrayList<>();

    public void addFragments(Fragment fragments, String titles)
    {
        this.fragements.add(fragments);
        this.tabTitles.add(titles);
    }
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }
    @Override
    public Fragment getItem(int position) {

    return fragements.get(position);
    }
    @Override
    public int getCount() {
        return fragements.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return  tabTitles.get(position);
    }
}
