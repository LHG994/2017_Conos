package com.example.lhg.new_proto;

import android.content.ContentValues;

/**
 * Created by leehs on 2017-05-09.
 */

public class FunctionResult {


    ////////////////////////////////////////////////////// 문자열 출력 ///////////////////////////////////////////////////////
    public String stringQuest(String url, String order, ContentValues values, String _token){
        /////////////////////////////////// <이메일 인증> 출력: fail or success //////////////////////////////////
        if(order.equals("confirmMail")){
            String temp; // 요청 결과를 저장할 변수.

            String[] fomat;
            fomat = new String[5];
            fomat[0] = "email";

            PostConnection postConnection = new PostConnection();
            temp = postConnection.request(url + "user/get_confirm_mail/", values,fomat,null);


            Parsing parsing = new Parsing();
            if(!temp.equals("Fail")){
                temp = parsing.ParsingResult(temp,"message");
                return "success";
            }
            else{return "fail";}
        }
        ////////////////////////////////// <토큰 가져오기> 출력: fail or token ///////////////////////////////////
        else if(order.equals("getToken")){
            String temp; // 요청 결과를 저장할 변수.

            String[] fomat;
            fomat = new String[5];
            fomat[0] = "email";
            fomat[1] = "password";

            PostConnection postConnection = new PostConnection();
            temp = postConnection.request(url + "user/get_token/", values,fomat,null);


            Parsing parsing = new Parsing();
            if(!temp.equals("Fail")){
                temp = parsing.ParsingResult(temp,"token");
                return temp;
            }
            else{return "fail";}
        }
        ///////////////////////////////// <회원가입> 출력: fail or success////////////////////////////////////
        else if(order.equals("register")){
            String temp; // 요청 결과를 저장할 변수.

            String[]  fomat;
            fomat = new String[5];
            fomat[0] = "name";
            fomat[1] = "email";
            fomat[2] = "password";

            PostConnection postConnection = new PostConnection();
            temp = postConnection.request(url + "user/register/", values,fomat,null);


            Parsing parsing = new Parsing();
            if(temp.equals("Fail")){
                temp = parsing.ParsingResult(temp,"message");
                return temp;
            }
            else{return temp;}
            //String [] tt = res.split(" ");
        }
        ///////////////////////////////// <회원의 모든 정보> 출력: fail or json ////////////////////////////////////
        else if(order =="user"){
            String temp; // 요청 결과를 저장할 변수.
            GetConnection getConnection = new GetConnection();
            temp = getConnection.request(url + "user/",_token); // 해당 URL로 부터 결과물을 얻어온다.
            /*Parsing parsing = new Parsing();
            temp = parsing.ParsingResult(temp,"board_name");

            String [] res = temp.split("\n");*/
            Parsing parsing = new Parsing();
            if(!temp.equals("Fail")){

                return temp;
            }
            else{return null;}
        }
        else{return "Wrong order";}
    }
////////////////////////////////////////////////////// 배열 출력 ///////////////////////////////////////////////////////
    public String [] arrayQuest(String url, String order, ContentValues values, String _token){
        ///////////////////////////////// <회원이 속한 보드정보> 출력: null or board array ////////////////////////////////////
        if(order =="user_board"){
            String temp; // 요청 결과를 저장할 변수.
            GetConnection getConnection = new GetConnection();
            temp = getConnection.request(url + "user/",_token); // 해당 URL로 부터 결과물을 얻어온다.
            /*Parsing parsing = new Parsing();
            temp = parsing.ParsingResult(temp,"board_name");

            String [] res = temp.split("\n");*/
            Parsing parsing = new Parsing();
            if(!temp.equals("Fail")){
                temp = parsing.ParsingResult(temp,"boards");
                temp = parsing.ParsingResult(temp,"board_name");

                String [] res = temp.split("\n");
                return res;
            }
            else{return null;}
        }
        else{return null;}
    }
}
