package com.example.lhg.new_proto;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by LHG on 2017-04-17.
 */

public class Card_viewpager extends FragmentStatePagerAdapter {
    int _numOfTabs;

    public Card_viewpager(FragmentManager fm, int numOfTabs) {
        super(fm);
        this._numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CardFragment cf = new CardFragment();
                return cf;
            case 1:
                HistoryFragment hf = new HistoryFragment();
                return hf;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return _numOfTabs;
    }
}
