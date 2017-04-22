package com.example.lhg.new_proto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.example.lhg.new_proto.R.layout.cardview_todo;

/**
 * Created by LHG on 2017-04-17.
 */

public class Todo_cardviewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(cardview_todo, container, false);

        return v;
    }
}
