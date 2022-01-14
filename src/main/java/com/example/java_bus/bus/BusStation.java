package com.example.java_bus.bus;

import com.example.java_bus.vo.BusStationVo;
import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class BusStation {
    // 정류장 데이터 가져오기
    public String busStationStateLoadData(Long busRouteId) throws IOException {
        StringBuffer result = new StringBuffer();
        try{
            StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute?" +
                    "serviceKey=crWr3d38ilIuwdcULZmazg8UNnUS%2B9MEXSS1KKyPvE%2BuYkGBfR6HKTKpMSTEw3i03ISVwG59bJai7JDasd4%2BIw%3D%3D");
            urlBuilder.append("&" + URLEncoder.encode("busRouteId", "UTF-8") + "=" + busRouteId.toString()); /**/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection urlConnection = (HttpURLConnection)  url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));

            String returnLine;
            result.append("<xmp>");
            while((returnLine = br.readLine()) != null) {
                result.append(returnLine + "\r\n");
            }
            urlConnection.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result+"</xmp>";
    }

    // 실시간 노선 데이터 가져오기
    public List<BusStationVo> busStationLoadData(Long busRouteId) throws IOException {
        List<BusStationVo> busStationVoList = new ArrayList<BusStationVo>();

        try {
            StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute?" +
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
                BusStationVo busStationVo = new BusStationVo();

                JSONObject detailInfo = (JSONObject)itemList.get(i);
//                System.out.println("노선 ID  : " + detailInfo.get("busRouteId"));
//                System.out.println("노선명   : " + detailInfo.get("busRouteNm"));
//                System.out.println("순번     : " + detailInfo.get("seq"));
//                System.out.println("구간 ID  : " + detailInfo.get("section"));
//                System.out.println("정류소 ID  : " + detailInfo.get("station"));
//                System.out.println("정류소 이름 : " + detailInfo.get("stationNm"));
//                System.out.println("정류소 X  : " + detailInfo.get("gpsX"));
//                System.out.println("정류소 Y : " + detailInfo.get("gpsY"));
//                System.out.println("-------------------------------------------");
                //busStation.setBusRouteId((Long) detailInfo.get("busRouteId"));
                busStationVo.setBusRouteId((Long) detailInfo.get("busRouteId"));
                busStationVo.setBusRouteNm((String) detailInfo.get("busRouteNm"));
                busStationVo.setStStationNm((String) detailInfo.get("stationNm"));
                busStationVo.setPosX((Double) detailInfo.get("gpsX"));
                busStationVo.setPosY((Double) detailInfo.get("gpsY"));
                busStationVo.setTransYn((String) detailInfo.get("transYn"));
                busStationVoList.add(busStationVo);
            }

            return busStationVoList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return busStationVoList;
    }

//    // 정류장 도착 정보 데이터 가져오기
    public List<BusStationVo> BusStationLoadArriveData(List<BusStationVo> BusStationVos){
        if(BusStationVos.isEmpty())
            return BusStationVos;

        try {
            StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRouteAll?" +
                    "serviceKey=crWr3d38ilIuwdcULZmazg8UNnUS%2B9MEXSS1KKyPvE%2BuYkGBfR6HKTKpMSTEw3i03ISVwG59bJai7JDasd4%2BIw%3D%3D");
            urlBuilder.append("&" + URLEncoder.encode("busRouteId", "UTF-8") + "=" + BusStationVos.get(0).getBusRouteId().toString()); /**/
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

            for (int i =0; i<BusStationVos.size(); i++){
                BusStationVo busStationVo = BusStationVos.get(i);

                JSONObject detailInfo = (JSONObject)itemList.get(i);
//                System.out.println("도착정보  : " + detailInfo.get("arrmsg1"));
//                System.out.println("도착정보2  : " + detailInfo.get("arrmsg2"));
//                System.out.println("정류장이름  : " + detailInfo.get("stNm"));
//                System.out.println("버스번호  : " + detailInfo.get("plainNo1"));
//                System.out.println("버스번호2  : " + detailInfo.get("plainNo2"));

                if(detailInfo.get("stNm").equals(busStationVo.getStStationNm())){
                    busStationVo.setArrmsg((String) detailInfo.get("arrmsg1"));
                    busStationVo.setArrmsg2((String) detailInfo.get("arrmsg2"));
                    busStationVo.setPlainNo1((String) detailInfo.get("plainNo1"));
                    busStationVo.setPlainNo2((String) detailInfo.get("plainNo2"));
                }
            }

            return BusStationVos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BusStationVos;
    }

    public List<Point2D> BusStationLoadPathData(Long busRouteId) {
        List<Point2D> pathPointList = new ArrayList<Point2D>();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/busRouteInfo/getRoutePath?" +
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
                Point2D pointPath = new Point2D.Double();

                JSONObject detailInfo = (JSONObject)itemList.get(i);
//                System.out.println("위치 x  : " + detailInfo.get("gpsX"));
//                System.out.println("위치 y  : " + detailInfo.get("gpsY"));
                 pointPath.setLocation((Double) detailInfo.get("gpsX"), (Double) detailInfo.get("gpsY"));

                 pathPointList.add(pointPath);
            }

            return pathPointList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pathPointList;
    }
//
//    // 버스 정류장 가져오기
//    public List<BusStation> getBusStationList(Long busRouteId) throws IOException {
//        List<BusStation> busStationList = new ArrayList<BusStation>();
//        busStationList = BusStationLoadData(busRouteId);
//
//        return BusStationLoadArriveData(busStationList);
//    }
//
//    public BusStation getBusStation(Long busRouteId, Long busRouteSeq) throws IOException {
//        BusStation searchStation = new BusStation();
//        List<BusStation> busStations = BusStationLoadData(busRouteId);
//
//        if((busRouteSeq<1) || (busRouteSeq > busStations.size()))
//            return searchStation;
//
//        return busStations.get(Math.toIntExact(busRouteSeq));
//    }
//
//    // 정류장 데이터 로드
//    public List<BusStation> getBusStations(Long busRouteId) throws IOException {
//        return BusStationLoadData(busRouteId);
//    }
//
//    //정류장 지도 중심 위치 찾기
//    public Point2D getBusStationsCenter(List<BusStation> busStationList){
//        Point2D centerPos = new Point2D.Double();
//        double avgPosX = 0, avgPosY = 0;
//
//        for(int i=0; i<busStationList.size(); i++){
//            BusStation busStation = busStationList.get(i);
//            avgPosX = avgPosX + busStation.getPosX();
//            avgPosY = avgPosY + busStation.getPosY();
//        }
//
//        avgPosX = avgPosX / busStationList.size();
//        avgPosY = avgPosY / busStationList.size();
//
//        centerPos.setLocation(avgPosX, avgPosY);
//
//        return centerPos;
//    }
}
