package com.example.lhg.new_proto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by LHG on 2017-04-13.
 */

public class CardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    String token;
    String b_id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        b_id = intent.getStringExtra("b_id");


        Toolbar card_toolbar = (Toolbar)findViewById(R.id.card_toolbar);
        setSupportActionBar(card_toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout card_drawer = (DrawerLayout)findViewById(R.id.card_drawer);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, card_drawer, card_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        card_drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Create tabs
        final TabLayout tabs = (TabLayout) findViewById(R.id.card_tab);
        tabs.addTab(tabs.newTab().setText("Card"));
        tabs.addTab(tabs.newTab().setText("History"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.card_container);
        final Card_viewpager adapter = new Card_viewpager(getSupportFragmentManager(), tabs.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });





    }

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
                    Bundle bundle = new Bundle(2); // 파라미터는 전달할 데이터 개수
                    bundle.putString("token", token); // key , value
                    bundle.putString("b_id",b_id);
                    cf.setArguments(bundle);

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



    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.card_drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_myInfo) {
            // Handle the camera action
        } else if (id == R.id.drawer_memberInfo) {

        } else if (id == R.id.drawer_Setting) {

        } else if (id == R.id.drawer_history) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.card_drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public  void onResume(){
        super.onResume();
        TabLayout tabs = (TabLayout) findViewById(R.id.card_tab);
        Card_viewpager adapter = new Card_viewpager(getSupportFragmentManager(), tabs.getTabCount());
        adapter.notifyDataSetChanged();
    }
}
