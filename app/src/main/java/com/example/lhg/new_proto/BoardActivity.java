package com.example.lhg.new_proto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import de.mrapp.android.dialog.MaterialDialog;

import static com.example.lhg.new_proto.BoardFragment.board_itemList;
import static com.example.lhg.new_proto.BoardFragment.mboard_adapter;

public class BoardActivity extends AppCompatActivity {

    //github
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);


        toolbar = (Toolbar) findViewById(R.id.board_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);


        //Create tabs
        final TabLayout tabs = (TabLayout) findViewById(R.id.board_tab);
        tabs.addTab(tabs.newTab().setText("Colleague"));
        tabs.addTab(tabs.newTab().setText("Board"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.board_container);
        final Board_viewpager adapter = new Board_viewpager(getSupportFragmentManager(), tabs.getTabCount());
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
        //Create tabs end

        //Board button listener
        findViewById(R.id.board_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (tabs.getSelectedTabPosition()) {
                    case 0:
                        Toast.makeText(BoardActivity.this, "Colleague Menu Button", Toast.LENGTH_LONG).show();
                        BoardFragment.board_item item = new BoardFragment.board_item();

                        return;
                    case 1:
                        Toast.makeText(BoardActivity.this, "Board Menu Button", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), InviteActivity.class);
                        startActivityForResult(intent, 0);
                        return;
                }
            }
        });
        //Board button listener end


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0)
            if(resultCode == 0){
                String string;
                BoardFragment.board_item item = new BoardFragment.board_item();
                string = data.getStringExtra("BN");
                item.setName(string);
                board_itemList.add(item);
                mboard_adapter.notifyDataSetChanged();

            }
    }
}




