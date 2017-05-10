package com.example.lhg.new_proto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by LHG on 2017-05-08.
 */

public class LoginActivity extends AppCompatActivity{

    Button Lbutton;
    Button Jbutton;
    EditText id_text;
    EditText password_text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Lbutton = (Button)findViewById(R.id.login_button);
        Jbutton = (Button)findViewById(R.id.join_button);
        id_text = (EditText)findViewById(R.id.id_et);
        password_text = (EditText)findViewById(R.id.password_et);


        class loginbutton_listener implements View.OnClickListener {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
                startActivity(intent);
                finish();
            }
        }

        class joinbutton_listener implements View.OnClickListener {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        }

        Lbutton.setOnClickListener(new loginbutton_listener());

    }



}
