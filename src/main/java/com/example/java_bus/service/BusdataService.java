package com.example.java_bus.service;

import com.example.java_bus.domain.Board;
import com.example.java_bus.domain.Busdata;
import com.example.java_bus.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@Service
public class BusdataService {
//    private final BusdataMapper BusdataMapper;
    String BusData;

//    public List<Busdata> busdataList() {
//        return BusdataMapper.();
//    }

    public void LoadData() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRouteAll"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=crWr3d38ilIuwdcULZmazg8UNnUS%2B9MEXSS1KKyPvE%2BuYkGBfR6HKTKpMSTEw3i03ISVwG59bJai7JDasd4%2BIw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("busRouteId","UTF-8") + "=" + URLEncoder.encode("100100130", "UTF-8")); /*노선ID*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        BusData = sb.toString();
        System.out.println(BusData);
    }

    public String ViewData() {
        return BusData;
    }
}
