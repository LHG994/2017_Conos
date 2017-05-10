package com.example.lhg.new_proto;

/**
 * Created by leehs on 2017-05-09.
 */

import android.content.ContentValues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class PostConnection {
    public String request(String _url, ContentValues _params){
        HttpURLConnection urlConn = null;
        StringBuffer sbParams = new StringBuffer();

        // 파라미터 검사.
        if (_params == null)
            sbParams.append("");
        else {
            boolean isAnd = false;
            String key;
            String value;

            for(Map.Entry<String, Object> parameter : _params.valueSet()){
                key = parameter.getKey();
                value = parameter.getValue().toString();
                if (isAnd)
                    sbParams.append("&");

                sbParams.append(key).append("=").append(value);
                if (!isAnd)
                    if (_params.size() >= 2)
                        isAnd = true;
            }
        }

        try{
            URL url = new URL(_url);
            urlConn = (HttpURLConnection) url.openConnection();

            // urlConn 설정.
            urlConn.setRequestMethod("POST");
            urlConn.setRequestProperty("Accept-Charset", "UTF-8");
            urlConn.setRequestProperty("Context_Type", "application/json");

            // parameter 전달 및 데이터 읽어오기.
            String strParams = sbParams.toString();
            OutputStream os = urlConn.getOutputStream();
            os.write(strParams.getBytes("UTF-8"));
            os.flush();
            os.close();

            // 연결 요청 확인.
            // 실패 시 null을 리턴하고 메서드를 종료.
            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK)
                return urlConn.getResponseMessage();

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
