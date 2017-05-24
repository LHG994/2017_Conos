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
        ///////////////////////////////// <보드에 투두 추가> 출력: fail or json ////////////////////////////////////
        else if(order.equals("addTodo")){
            String temp; // 요청 결과를 저장할 변수.

            String[] fomat;
            fomat = new String[2];
            fomat[0] = "board_id";
            fomat[1] = "item";

            PostConnection postConnection = new PostConnection();
            temp = postConnection.request(url + "todo/", values,fomat,_token);


            Parsing parsing = new Parsing();
            if(!temp.equals("Fail")){
                temp = parsing.ParsingResult(temp,"message");
                return temp;
            }
            else{return "fail";}
        }

        ///////////////////////////////// <보드에 투두 제거> 출력: fail or json ////////////////////////////////////
        else if(order.equals("delTodo")){
            String temp; // 요청 결과를 저장할 변수.

            String[] fomat;
            fomat = new String[1];
            fomat[0] = "todo_id";

            DeleteConnection deleteConnection = new DeleteConnection();
            temp = deleteConnection.request(url + "todo/", values,fomat,_token);


            Parsing parsing = new Parsing();
            if(!temp.equals("Fail")){
                temp = parsing.ParsingResult(temp,"message");
                return temp;
            }
            else{return "fail";}
        }

        ///////////////////////////////// <보드에 투두 ongoing 으로 변경> 출력: fail or json ////////////////////////////////////
        else if(order.equals("moveOngoing")){
            String temp; // 요청 결과를 저장할 변수.

            String[] fomat;
            fomat = new String[1];
            fomat[0] = "todo_id";

            PostConnection postConnection = new PostConnection();
            temp = postConnection.request(url + "todo/move_to_ongoing/", values,fomat,_token);


            Parsing parsing = new Parsing();
            if(!temp.equals("Fail")){
                temp = parsing.ParsingResult(temp,"message");
                return temp;
            }
            else{return "fail";}
        }

        ///////////////////////////////// <보드에 투두 done 으로 변경> 출력: fail or json ////////////////////////////////////
        else if(order.equals("moveDone")){
            String temp; // 요청 결과를 저장할 변수.

            String[] fomat;
            fomat = new String[1];
            fomat[0] = "todo_ongoing_id";

            PostConnection postConnection = new PostConnection();
            temp = postConnection.request(url + "todo/move_to_done/", values,fomat,_token);


            Parsing parsing = new Parsing();
            if(!temp.equals("Fail")){
                temp = parsing.ParsingResult(temp,"message");
                return temp;
            }
            else{return "fail";}
        }


        else{return "Wrong order";}
    }





////////////////////////////////////////////////////// 배열 출력 ///////////////////////////////////////////////////////
    public String [] arrayQuest(String url, String order, ContentValues values, String _token){
        ///////////////////////////////// <회원이 속한 보드정보> 출력: null or board array ////////////////////////////////////
        if(order =="user_board_name"){
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

        else if(order =="user_board_id"){
            String temp; // 요청 결과를 저장할 변수.
            GetConnection getConnection = new GetConnection();
            temp = getConnection.request(url + "user/",_token); // 해당 URL로 부터 결과물을 얻어온다.
            /*Parsing parsing = new Parsing();
            temp = parsing.ParsingResult(temp,"board_name");

            String [] res = temp.split("\n");*/
            Parsing parsing = new Parsing();
            if(!temp.equals("Fail")){
                temp = parsing.ParsingResult(temp,"boards");
                temp = parsing.ParsingResult(temp,"board_id");

                String [] res = temp.split("\n");
                return res;
            }
            else{return null;}
        }
        else return null;
    }


    public String [] todoQuest(String url,String order, String b_id, String _token){
        ///////////////////////////////// <보드에 해당하는 notyet to do name 리스트 출력> 출력: null or to do array ////////////////////////////////////
        if(order.equals("notyet_name")){
            FunctionResult functionResult = new FunctionResult();
            String sss = functionResult.stringQuest(url,"user",null,_token);
            Parsing parsing = new Parsing();
            sss = parsing.todoNameParsing(sss,b_id);
            String [] res = sss.split("\n");

            if (res[0].equals(null)){
                return null;
            }
            else{return res;}
        }
        ///////////////////////////////// <보드에 해당하는 doing to do name 리스트 출력> 출력: null or to do array ////////////////////////////////////
        else if(order.equals("doing_name")){
            FunctionResult functionResult = new FunctionResult();
            String sss = functionResult.stringQuest(url,"user",null,_token);
            Parsing parsing = new Parsing();
            sss = parsing.todoDoingNameParsing(sss,b_id);
            String [] res = sss.split("\n");

            if (res[0].equals(null)){
                return null;
            }
            else{return res;}
        }

        ///////////////////////////////// <보드에 해당하는 done to doname 리스트 출력> 출력: null or to do array ////////////////////////////////////
        else if(order.equals("done_name")){
            FunctionResult functionResult = new FunctionResult();
            String sss = functionResult.stringQuest(url,"user",null,_token);
            Parsing parsing = new Parsing();
            sss = parsing.todoDoneNameParsing(sss,b_id);
            String [] res = sss.split("\n");

            if (res[0].equals(null)){
                return null;
            }
            else{return res;}
        }
        ///////////////////////////////// <보드에 해당하는 notyet to do id리스트 출력> 출력: null or to do array ////////////////////////////////////
        else if(order.equals("notyet_id")){
            FunctionResult functionResult = new FunctionResult();
            String sss = functionResult.stringQuest(url,"user",null,_token);
            Parsing parsing = new Parsing();
            sss = parsing.todoIdParsing(sss,b_id);
            String [] res = sss.split("\n");

            if (res[0].equals(null)){
                return null;
            }
            else{return res;}
        }
        ///////////////////////////////// <보드에 해당하는 doing to do id리스트 출력> 출력: null or to do array ////////////////////////////////////
        else if(order.equals("doing_id")){
            FunctionResult functionResult = new FunctionResult();
            String sss = functionResult.stringQuest(url,"user",null,_token);
            Parsing parsing = new Parsing();
            sss = parsing.todoDoingIdParsing(sss,b_id);
            String [] res = sss.split("\n");

            if (res[0].equals(null)){
                return null;
            }
            else{return res;}
        }

        ///////////////////////////////// <보드에 해당하는 done to do id리스트 출력> 출력: null or to do array ////////////////////////////////////
        else if(order.equals("done_id")){
            FunctionResult functionResult = new FunctionResult();
            String sss = functionResult.stringQuest(url,"user",null,_token);
            Parsing parsing = new Parsing();
            sss = parsing.todoDoneIdParsing(sss,b_id);
            String [] res = sss.split("\n");

            if (res[0].equals(null)){
                return null;
            }
            else{return res;}
        }
        else{return null;}
    }
}
