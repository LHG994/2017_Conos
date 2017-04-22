package com.example.lhg.new_proto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by LHG on 2017-04-12.
 */

public class InviteActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);

        toolbar = (Toolbar) findViewById(R.id.Invite_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ArrayList<String> items = new ArrayList<String>();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, items);

        final ListView listView = (ListView) findViewById(R.id.invite_list);
        listView.setAdapter(adapter);

        items.add("EJ LEE");
        items.add("HG LEE");
        items.add("LC CHANG");
        items.add("YJ CHOI");

        adapter.notifyDataSetChanged();

        final EditText et = (EditText) findViewById(R.id.invite_edittext);

        findViewById(R.id.invite_make_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String boardname = "";
                SparseBooleanArray cheakedItems = listView.getCheckedItemPositions();
                int itemnumber = 0;
                int count = adapter.getCount();
                Intent intent = new Intent();


                for (int i = count - 1; i >= 0; i--) {
                    if (cheakedItems.get(i)) {
                        itemnumber++;
                    }
                }


                for (int i = count - 1; i >= 0; i--) {
                    if (cheakedItems.get(i)) {
                        if (itemnumber-- > 1)
                            boardname = boardname + items.get(i).toString() + ", ";
                        else
                            boardname = boardname + items.get(i).toString();
                    }
                }


                intent.putExtra("BN", boardname);
                setResult(0, intent);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
