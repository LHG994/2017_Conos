package com.example.lhg.new_proto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by LHG on 2017-05-11.
 */

public class JoinDialog extends DialogFragment {

    private EditText id_et;
    private Button ok_button;

    public JoinDialog() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join, container);
        id_et = (EditText) view.findViewById(R.id.id_et);
        ok_button = (Button) view.findViewById(R.id.join_ok);

        ok_button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //editText의 정보를 모아, 회원가입 요청

                JoinDialog.this.dismiss();
            }
        });

        return view;
    }
}