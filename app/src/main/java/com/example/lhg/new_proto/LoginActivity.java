package com.example.lhg.new_proto;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.mrapp.android.dialog.MaterialDialog;

/**
 * Created by LHG on 2017-05-08.
 */

public class LoginActivity extends AppCompatActivity {

    Button Lbutton;
    TextView Jbutton;
    EditText id_text;
    EditText password_text;

    MaterialDialog.Builder dialogBuilder;
    MaterialDialog dialog;

    String url = "http://166.62.32.120:5000/";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Lbutton = (Button)findViewById(R.id.login_button);
        Jbutton = (TextView)findViewById(R.id.join_button);
        id_text = (EditText)findViewById(R.id.id_et);
        password_text = (EditText)findViewById(R.id.password_et);

        //밑줄 추가하는 부분
        SpannableString content = new SpannableString("처음이신가요?");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        Jbutton.setText(content);

        class loginbutton_listener implements View.OnClickListener {
            @Override
            public void onClick(View view){
                String id = id_text.getText().toString();
                String pw = password_text.getText().toString();

                ContentValues values = new ContentValues();

                values.put("email",id);
                values.put("password",pw);

                NetworkTask networkTask = new NetworkTask(url, values,null);
                networkTask.execute();
            }
        }

        //다이얼로그 불러오는 리스너
        class joinbutton_listener implements View.OnClickListener {
            @Override
            public void onClick(View view){
                FragmentManager fm = getSupportFragmentManager();
                JoinDialog df = new JoinDialog();
                df.show(fm, "Join");
            }
        }

        Lbutton.setOnClickListener(new loginbutton_listener());
        Jbutton.setOnClickListener(new joinbutton_listener());

        class ok_button_listener implements DialogInterface.OnClickListener {
            @Override
            public void onClick(DialogInterface di, int i){

            }
        }

    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;
        private String token;

        public NetworkTask(String url, ContentValues values, String token) {
            this.url = url;
            this.values = values;
            this.token = token;
        }

        @Override
        protected String doInBackground(Void... params) {
            FunctionResult functionResult = new FunctionResult();
            String ss = functionResult.stringQuest(url,"getToken",values,null);

            return ss;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //로그인 성공
            Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
            if(!s.equals("fail")){
                Log.i("token",s);
                intent.putExtra("token",s);//데이터 전달
                startActivity(intent);//다음액티비티 실행
                finish();//현재 액티비티 종료
            }
            //로그인 실패
            else{
                Toast.makeText(getApplicationContext(), "login failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
