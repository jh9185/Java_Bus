package com.example.java_bus.bus;

import com.example.java_bus.vo.BusNumberVo;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BusNumber {

    //    csv file load
    public List<BusNumberVo> readBusNumber() throws IOException {
        List<BusNumberVo> busNumberVoList = new ArrayList<BusNumberVo>();

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
            //생성
            BusNumberVo busNumberVo = new BusNumberVo();
            //행을읽는다
            XSSFRow row=sheet.getRow(rowindex);

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
//                                busnumber.setBusRouteId(Long.valueOf(value));
                                busNumberVo.setRouteId(Long.valueOf(value));
                                break;
                            case 1:
//                                busnumber.setBusRouteNm(value);
//                                uploadBusNumber(busnumber);
                                busNumberVo.setName(value);
                                busNumberVoList.add(busNumberVo);
                                break;
                        }
                    }
                }
            }
        }
        return busNumberVoList;
    }
}
