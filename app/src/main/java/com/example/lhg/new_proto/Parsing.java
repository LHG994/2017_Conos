package com.example.lhg.new_proto;

/**
 * Created by leehs on 2017-05-09.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by leehs on 2017-05-09.
 */

public class Parsing {
    public String ParsingResult(String data, String word) {
        boolean startWith = data.startsWith("[");
        boolean endsWith = data.endsWith("]");

        if(startWith != true && endsWith != true) {data = "["+data+"]";}

        try {
            String box = "";
            JSONArray results;
            results = new JSONArray(data);
            for (int i = 0; i < results.length(); i++) {
                JSONObject select = results.getJSONObject(i);
                box += select.getString(word);
                box += "\n";
            }
            return box;
        } catch (JSONException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
/////////////////////// 투두 제목 파싱  ///////////////////////////////////////////////
    public String todoNameParsing(String data, String some) {
        String emp;
        emp = ParsingResult(data,"boards");
        String box = "";
        try {
            JSONArray results;
            results = new JSONArray(emp);
            for (int i = 0; i < results.length(); i++) {
                JSONObject select = results.getJSONObject(i);

                if(some.equals(select.getString("board_id"))){
                    box += select;//board_id 가 일치하는 보드를 저장
                    box += "\n";
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        //emp = ParsingResult(emp,"board_id");
        emp = ParsingResult(box,"todos");
        emp = ParsingResult(emp,"todo_item");

        return emp;
    }

    /////////////////////// 투두 id 파싱  ///////////////////////////////////////////////
    public String todoIdParsing(String data, String some) {
        String emp;
        emp = ParsingResult(data,"boards");
        String box = "";
        try {
            JSONArray results;
            results = new JSONArray(emp);
            for (int i = 0; i < results.length(); i++) {
                JSONObject select = results.getJSONObject(i);

                if(some.equals(select.getString("board_id"))){
                    box += select;//board_id 가 일치하는 보드를 저장
                    box += "\n";
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        //emp = ParsingResult(emp,"board_id");
        emp = ParsingResult(box,"todos");
        emp = ParsingResult(emp,"todo_id");

        return emp;
    }
///////////////////// 투두 두잉 제목 파싱 ///////////////////////
    public String todoDoingNameParsing(String data, String some) {
        String emp;
        emp = ParsingResult(data,"boards");
        String box = "";
        try {
            JSONArray results;
            results = new JSONArray(emp);
            for (int i = 0; i < results.length(); i++) {
                JSONObject select = results.getJSONObject(i);

                if(some.equals(select.getString("board_id"))){
                    box += select;//board_id 가 일치하는 보드를 저장
                    box += "\n";
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        //emp = ParsingResult(emp,"board_id");
        emp = ParsingResult(box,"todos_ongoing");
        emp = ParsingResult(emp,"todo_ongoing_item");

        return emp;
    }

    ///////////////////// 투두 두잉 id 파싱 ///////////////////////
    public String todoDoingIdParsing(String data, String some) {
        String emp;
        emp = ParsingResult(data,"boards");
        String box = "";
        try {
            JSONArray results;
            results = new JSONArray(emp);
            for (int i = 0; i < results.length(); i++) {
                JSONObject select = results.getJSONObject(i);

                if(some.equals(select.getString("board_id"))){
                    box += select;//board_id 가 일치하는 보드를 저장
                    box += "\n";
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        //emp = ParsingResult(emp,"board_id");
        emp = ParsingResult(box,"todos_ongoing");
        emp = ParsingResult(emp,"todo_ongoing_id");

        return emp;
    }
/////////////////////////// 투두 돈 제목 파싱 /////////////////////////////
    public String todoDoneNameParsing(String data, String some) {
        String emp;
        emp = ParsingResult(data,"boards");
        String box = "";
        try {
            JSONArray results;
            results = new JSONArray(emp);
            for (int i = 0; i < results.length(); i++) {
                JSONObject select = results.getJSONObject(i);

                if(some.equals(select.getString("board_id"))){
                    box += select;//board_id 가 일치하는 보드를 저장
                    box += "\n";
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        //emp = ParsingResult(emp,"board_id");
        emp = ParsingResult(box,"todos_done");
        emp = ParsingResult(emp,"todo_done_item");

        return emp;
    }
    /////////////////////////// 투두 돈 id 파싱 /////////////////////////////
    public String todoDoneIdParsing(String data, String some) {
        String emp;
        emp = ParsingResult(data,"boards");
        String box = "";
        try {
            JSONArray results;
            results = new JSONArray(emp);
            for (int i = 0; i < results.length(); i++) {
                JSONObject select = results.getJSONObject(i);

                if(some.equals(select.getString("board_id"))){
                    box += select;//board_id 가 일치하는 보드를 저장
                    box += "\n";
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        //emp = ParsingResult(emp,"board_id");
        emp = ParsingResult(box,"todos_done");
        emp = ParsingResult(emp,"todo_done_id");

        return emp;
    }
}
