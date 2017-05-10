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
}
