package com.example.java_bus.service;

import com.example.java_bus.domain.Board;
import com.example.java_bus.domain.BusNumber;
import com.example.java_bus.domain.BusStation;
import com.example.java_bus.mapper.BusMapper;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class BusService {
    private final BusMapper busdataMapper;

    // 정류장 데이터 가져오기
    public String BusStopLoadData(Long busRouteId) throws IOException {
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

    // 노선 데이터 가져오기
    public List<BusStation> BusStationLoadData(Long busRouteId) throws IOException {
        List<BusStation> busStatinlist = new ArrayList<BusStation>();
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
                BusStation busStation = new BusStation();

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
                busStation.setBusRouteId((Long) detailInfo.get("busRouteId"));
                busStation.setBusRouteNm(String.valueOf(detailInfo.get("busRouteNm")));
                busStation.setNumber((Long) detailInfo.get("seq"));
                busStation.setStStationNm(String.valueOf(detailInfo.get("stationNm")));
                busStation.setPosX((double) detailInfo.get("gpsX"));
                busStation.setPosY((double) detailInfo.get("gpsY"));
                busStation.setTransYn(String.valueOf(detailInfo.get("transYn")));

                busStatinlist.add(i, busStation);
            }

            return busStatinlist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return busStatinlist;
    }

    // 정류장 도착 정보 데이터 가져오기
    public List<BusStation> BusStationLoadArriveData(List<BusStation> busStations){
        if(busStations.isEmpty())
            return busStations;

        List<BusStation> busStatinlist = busStations;
        try {
            StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRouteAll?" +
                    "serviceKey=crWr3d38ilIuwdcULZmazg8UNnUS%2B9MEXSS1KKyPvE%2BuYkGBfR6HKTKpMSTEw3i03ISVwG59bJai7JDasd4%2BIw%3D%3D");
            urlBuilder.append("&" + URLEncoder.encode("busRouteId", "UTF-8") + "=" + busStations.get(0).getBusRouteId().toString()); /**/
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
            for (int i =0; i<busStatinlist.size(); i++){
                BusStation busStation = busStatinlist.get(i);

                JSONObject detailInfo = (JSONObject)itemList.get(i);
//                System.out.println("도착정보  : " + detailInfo.get("arrmsg1"));
//                System.out.println("도착정보2  : " + detailInfo.get("arrmsg2"));
//                System.out.println("정류장이름  : " + detailInfo.get("stNm"));
//                System.out.println("버스번호  : " + detailInfo.get("plainNo1"));
//                System.out.println("버스번호2  : " + detailInfo.get("plainNo2"));

                if(detailInfo.get("stNm").equals(busStation.getStStationNm())){
                    busStation.setArrmsg((String) detailInfo.get("arrmsg1"));
                    busStation.setArrmsg2((String) detailInfo.get("arrmsg2"));
                    busStation.setPlainNo1((String) detailInfo.get("plainNo1"));
                    busStation.setPlainNo2((String) detailInfo.get("plainNo2"));
                }
            }

            return busStatinlist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return busStatinlist;
    }
    @Transactional // 등록
    public void uploadBusNumber(BusNumber busNumber){
        busdataMapper.insertBusNumberList(busNumber);
    }

    public void readBusNumber() throws IOException {
//        if (!extension.equals("xlsx") && !extension.equals("xls")) {
//            return dataList;
//        }
        FileInputStream fis=new FileInputStream("C:\\Users\\KJH\\Downloads\\20210520기준_서울시_노선현황.xlsx");
        XSSFWorkbook workbook=new XSSFWorkbook(fis);
        int rowindex=0;
        int columnindex=0;
        //시트 수 (첫번째에만 존재하므로 0을 준다)
        //만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
        XSSFSheet sheet=workbook.getSheetAt(0);
        //행의 수
        int rows=sheet.getPhysicalNumberOfRows();

        for(rowindex=1;rowindex<rows;rowindex++){
            //행을읽는다
            XSSFRow row=sheet.getRow(rowindex);
            BusNumber busnumber = new BusNumber();
            if(row !=null){
                //셀의 수
                int cells=row.getPhysicalNumberOfCells();
                for(columnindex=0;columnindex<=cells;columnindex++){
                    //셀값을 읽는다
                    XSSFCell cell=row.getCell(columnindex);
                    String value="";
                    //셀이 빈값일경우를 위한 널체크
                    if(cell==null){
                        continue;
                    }else{
                        //타입별로 내용 읽기
                        switch (cell.getCellType()){
                            case FORMULA:
                                value=cell.getCellFormula();
                                break;
                            case NUMERIC:
                                value=cell.getNumericCellValue()+"";
                                break;
                            case STRING:
                                value=cell.getStringCellValue()+"";
                                if(value.equals("ROUTER_ID") || value.equals("노선명"))
                                    continue;
                                break;
                            case BLANK:
                                value=cell.getBooleanCellValue()+"";
                                break;
                            case ERROR:
                                value=cell.getErrorCellValue()+"";
                                break;
                        }
                        switch(columnindex){
                            case 0:
                                busnumber.setBusRouteId(Long.valueOf(value));
                                break;
                            case 1:
                                busnumber.setBusRouteNm(value);
                                uploadBusNumber(busnumber);
                                break;
                        }
                    }
                }
            }
        }
    }

    public Long searchBusRouteId(String busName) {
        BusNumber busNumber;
        busNumber = busdataMapper.getBusRouteId(busName);
        return busNumber.getBusRouteId();
    }
    // 버스 번호 가져오기
    public List<BusNumber> getBusNumberList() {
        return busdataMapper.getNumberList();
    }

    // 버스 정류장 가져오기
    public List<BusStation> getBusStationList(Long busRouteId) throws IOException {
        List<BusStation> busStationList = new ArrayList<BusStation>();
        busStationList = BusStationLoadData(busRouteId);

        return BusStationLoadArriveData(busStationList);
    }

    public BusStation getBusStation(Long busRouteId, Long busRouteSeq) throws IOException {
        BusStation searchStation = new BusStation();
        List<BusStation> busStations = BusStationLoadData(busRouteId);

        if((busRouteSeq<1) || (busRouteSeq > busStations.size()))
            return searchStation;

        return busStations.get(Math.toIntExact(busRouteSeq));
    }

    // 정류장 데이터 로드
    public List<BusStation> getBusStations(Long busRouteId) throws IOException {
        return BusStationLoadData(busRouteId);
    }

    //정류장 지도 중심 위치 찾기
    public Point2D getBusStationsCenter(List<BusStation> busStationList){
        Point2D centerPos = new Point2D.Double();
        double avgPosX = 0, avgPosY = 0;

        for(int i=0; i<busStationList.size(); i++){
            BusStation busStation = busStationList.get(i);
            avgPosX = avgPosX + busStation.getPosX();
            avgPosY = avgPosY + busStation.getPosY();
        }

        avgPosX = avgPosX / busStationList.size();
        avgPosY = avgPosY / busStationList.size();

        centerPos.setLocation(avgPosX, avgPosY);

        return centerPos;
    }
}
