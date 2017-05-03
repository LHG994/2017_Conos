package com.example.lhg.new_proto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.example.lhg.new_proto.R.layout.fragment_card;

/**
 * Created by LHG on 2017-04-17.
 */

//이 위에 투두, 메모, 미트업, 어태치를 올려야 됨

public class CardFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(fragment_card, container, false);



        return v;
    }
}
