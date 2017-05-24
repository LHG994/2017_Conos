package com.example.lhg.new_proto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by LHG on 2017-04-11.
 */

public class ColleagueFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_colleague, container, false);

        Button button;
        button = (Button)rootview.findViewById(R.id.go_memo_b);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getContext(), MemoActivity.class);
                startActivity(intent);
            }
        });


        return rootview;
    }
}
