package com.example.java_bus.service;

import com.example.java_bus.domain.BusNumber;
import com.example.java_bus.domain.BusStation;
import com.example.java_bus.mapper.BusMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusService {
    private final BusMapper busdataMapper;

    // 데이터 가져오기
    public String BusStopLoadData() throws IOException {
        StringBuffer result = new StringBuffer();
        try{
            String urlstr = "http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute?"
                    + "serviceKey=crWr3d38ilIuwdcULZmazg8UNnUS%2B9MEXSS1KKyPvE%2BuYkGBfR6HKTKpMSTEw3i03ISVwG59bJai7JDasd4%2BIw%3D%3D"
                    + "&busRouteId=104000007";
            URL url = new URL(urlstr);
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
    public List<BusStation> BusStationLoadData() throws IOException {
        List<BusStation> busStatinlist = new ArrayList<BusStation>();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=crWr3d38ilIuwdcULZmazg8UNnUS%2B9MEXSS1KKyPvE%2BuYkGBfR6HKTKpMSTEw3i03ISVwG59bJai7JDasd4%2BIw%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("busRouteId", "UTF-8") + "=" + URLEncoder.encode("104000007", "UTF-8")); /**/
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
            System.out.println(sb.toString());

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
                busStation.setBusRouteNm((String) detailInfo.get("busRouteNm"));
                busStation.setNumber((Long) detailInfo.get("seq"));
                busStation.setStStationNm((String) detailInfo.get("stationNm"));
                busStation.setPosX((double) detailInfo.get("gpsX"));
                busStation.setPosY((double) detailInfo.get("gpsY"));

                busStatinlist.add(i, busStation);
            }

            return busStatinlist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return busStatinlist;
    }

    public List<BusNumber> readBusNumber() throws IOException {
        List<BusNumber> dataList = new ArrayList<BusNumber>();

        FileInputStream File = new FileInputStream("C:\\Users\\KJH\\Downloads\\20210520기준_서울시_노선현황.xlsx");

        String extension = FilenameUtils.getExtension("C:\\Users\\KJH\\Downloads\\20210520기준_서울시_노선현황.xlsx"); // 3

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            return dataList;
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(File);
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(File);
        }

        Sheet worksheet = workbook.getSheetAt(0);

        for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
            Row row = worksheet.getRow(i);
            if (row != null) {
                int cellCount = row.getPhysicalNumberOfCells();
                BusNumber busnumber = new BusNumber();
                for (int j=0; j <= cellCount; j++) {
                    Cell cell=row.getCell(j);
                    String value = "";
                    if(cell==null){
                        continue;
                    }else {
                        //타입별로 내용 읽기
                        switch (cell.getCellType()) {
                            case XSSFCell.CELL_TYPE_FORMULA:
                                value = cell.getCellFormula();
                                break;
                            case XSSFCell.CELL_TYPE_NUMERIC:
                                value = cell.getNumericCellValue() + "";
                                break;
                            case XSSFCell.CELL_TYPE_STRING:
                                value = cell.getStringCellValue() + "";
                                if (value.equals("ROUTE_ID") || value.equals("노선명"))
                                    continue;
                                if (j == 0) {
                                    busnumber.setBusRouteId(Long.valueOf(value));
                                } else {
                                    busnumber.setBusRouteNm(value);
                                    dataList.add(busnumber);
                                }
                                break;
                            case XSSFCell.CELL_TYPE_BLANK:
                                value = cell.getBooleanCellValue() + "";
                                break;
                            case XSSFCell.CELL_TYPE_ERROR:
                                value = cell.getErrorCellValue() + "";
                                break;
                        }
                    }
                }
            }
        }
        return dataList;
    }
}
