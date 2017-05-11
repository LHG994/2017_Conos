package com.example.lhg.new_proto;

/**
 * Created by leehs on 2017-05-09.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetConnection {

    public String request(String _url, String _token){
        HttpURLConnection urlConn = null;

        try{
            URL url = new URL(_url);
            urlConn = (HttpURLConnection) url.openConnection();

            // urlConn 설정.
            urlConn.setRequestMethod("GET");
            urlConn.setRequestProperty("Accept-Charset", "UTF-8");
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.setRequestProperty("Authorization", "Bearer " + _token);

            // 연결 요청 확인.
            // 실패 시 null을 리턴하고 메서드를 종료.
            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK)
                return urlConn.getResponseMessage();

            // 읽어온 결과물 리턴.
            // 요청한 URL의 출력물을 BufferedReader로 받는다.
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));

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