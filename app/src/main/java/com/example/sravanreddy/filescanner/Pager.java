package com.example.sravanreddy.filescanner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by sravanreddy on 4/8/18.
 */

public class Pager extends FragmentStatePagerAdapter {
    int tabCount;
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount=tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TopTenFiles tab1 = new TopTenFiles();
                return tab1;
            case 1:
                Frequencies tab2 = new Frequencies();
                return tab2;
            case 2:
                AverageFIleSize tab3 = new AverageFIleSize();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
