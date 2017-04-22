package com.example.lhg.new_proto;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by LHG on 2017-04-11.
 */

public class Board_viewpager extends FragmentStatePagerAdapter{
    int _numOfTabs;

    public Board_viewpager(FragmentManager fm, int numOfTabs) {
        super(fm);
        this._numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ColleagueFragment cf = new ColleagueFragment();
                return cf;
            case 1:
                BoardFragment bf = new BoardFragment();
                return bf;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return _numOfTabs;
    }
}

