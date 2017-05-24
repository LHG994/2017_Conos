package com.example.lhg.new_proto;

/**
 * Created by leehs on 2017-05-09.
 */

import android.content.ContentValues;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DeleteConnection {
    public String request(String _url, ContentValues _params, String [] _w, String Token){
        HttpURLConnection urlConn = null;

        //파라미터 json object로 변환
        JSONObject  job = new JSONObject();
        for(int i=0; i<_w.length; i++){
            try{
                job.put(_w[i],_params.get(_w[i]));
            }catch (JSONException e){return e.getMessage();}
        }


        try{
            URL url = new URL(_url);
            urlConn = (HttpURLConnection) url.openConnection();

            // urlConn 설정.
            urlConn.setRequestMethod("DELETE");
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.setRequestProperty("Authorization", "Bearer " + Token);

            OutputStream os = urlConn.getOutputStream();
            os.write(job.toString().getBytes("ascii"));
            os.flush();
            os.close();


            // 연결 요청 확인.
            // 실패 시 null을 리턴하고 메서드를 종료.
            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK)
                return "Fail";

            // 읽어온 결과물 리턴.
            // 요청한 URL의 출력물을 BufferedReader로 받는다.
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));

            // 출력물의 라인과 그 합에 대한 변수.
            String line;
            String page = "";

            while ((line = reader.readLine()) != null){
                page += line;
            }
            return page;

        } catch (MalformedURLException e) { // for URL.
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) { // for openConnection().
            e.printStackTrace();
            return e.getMessage();
        } finally {
            if (urlConn != null)
                urlConn.disconnect();
        }
    }

}
