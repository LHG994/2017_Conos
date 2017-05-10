package com.example.lhg.new_proto;

import android.content.ContentValues;

/**
 * Created by leehs on 2017-05-09.
 */

public class FunctionResult {
    public String[] Quest(String url, String order, ContentValues values){

        if(order == "todos"){
            String temp; // 요청 결과를 저장할 변수.
            GetConnection getConnection = new GetConnection();
            temp = getConnection.request(url); // 해당 URL로 부터 결과물을 얻어온다.
            Parsing parsing = new Parsing();
            temp = parsing.ParsingResult(temp,"todos");
            temp = parsing.ParsingResult(temp,"todo_item");

            String [] res = temp.split("\n");

            return res;
        }
        else if(order =="get_token"){
            String res; // 요청 결과를 저장할 변수.
            PostConnection postConnection = new PostConnection();
            res = postConnection.request(url, values);

            return null;
        }
        else if(order =="boards"){
            String temp; // 요청 결과를 저장할 변수.
            GetConnection getConnection = new GetConnection();
            temp = getConnection.request(url); // 해당 URL로 부터 결과물을 얻어온다.
            Parsing parsing = new Parsing();
            temp = parsing.ParsingResult(temp,"board_name");

            String [] res = temp.split("\n");

            return res;
        }

        else{return null;}
    }
}
