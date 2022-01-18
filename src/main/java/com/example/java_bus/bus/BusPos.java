package com.example.java_bus.bus;

import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class BusPos {
    //실시간 버스 위치 가져오기
    public List<VehId> BusArriveLoadData(Long busRouteId){
        List<VehId> VehIdList = new ArrayList<VehId>();

        if(busRouteId.toString().isEmpty())
            return VehIdList;

        try {
            StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid?" +
                    "serviceKey=crWr3d38ilIuwdcULZmazg8UNnUS%2B9MEXSS1KKyPvE%2BuYkGBfR6HKTKpMSTEw3i03ISVwG59bJai7JDasd4%2BIw%3D%3D");
            urlBuilder.append("&" + URLEncoder.encode("busRouteId", "UTF-8") + "=" + busRouteId.toString()); /**/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
            //Connect check log
            //System.out.println(sb.toString());

            org.json.JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
            String xmlJSONObjString = xmlJSONObj.toString();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(xmlJSONObjString);
            JSONObject busStationInfoResult = (JSONObject)jsonObject.get("ServiceResult");
            JSONObject busStationInfo = (JSONObject)busStationInfoResult.get("msgBody");

            JSONArray itemList = (JSONArray)busStationInfo.get("itemList");

            for (int i =0; i<itemList.size(); i++){
                VehId vehId = new VehId("", 0, 0);

                JSONObject detailInfo = (JSONObject)itemList.get(i);

                vehId.setPlainNo((String) detailInfo.get("plainNo"));
                vehId.setPosX((Double) detailInfo.get("gpsX"));
                vehId.setPosY((Double) detailInfo.get("gpsY"));

                VehIdList.add(vehId);
            }

            return VehIdList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return VehIdList;
    }
}
