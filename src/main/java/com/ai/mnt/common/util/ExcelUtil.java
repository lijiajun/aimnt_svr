package com.ai.mnt.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Title: base tool 
 * Description: Excel operate Company: AI
 * @author H
 * @version 1.0
 */
public class ExcelUtil {

    private static Logger logger = Logger.getLogger(ExcelUtil.class);

    /**
     * Workbook Create
     * @param is
     * @return
     * @throws Exception
     */
    public static Workbook getWorkBook(InputStream is) throws Exception {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(is);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Excel WorkBook 读取失败！ " + e.getMessage());
            throw new Exception("Excel WorkBook 读取失败！ ");
        }
        return wb;
    }

    /**
     * Workbook Create
     * @param fileName
     * @return
     * @throws Exception
     */
    public static Workbook getWorkBook(String fileName) throws Exception {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Excel WorkBook 读取失败！ " + e.getMessage());
            throw new Exception("Excel WorkBook 读取失败！ ");
        }
        return wb;
    }

    /**
     * 获取excel数据
     * @param is
     * @return
     * @throws Exception
     */
    public static Map<String, List<List<String>>> readExcelToList(InputStream is) throws Exception {
        Map<String, List<List<String>>> excelMap = new HashMap<>();
        Workbook wb = getWorkBook(is);;
        try {
            int sheetCount = wb.getNumberOfSheets();
            for(int i=0; i<sheetCount; i++) {
                List<List<String>> rowList = new ArrayList<>();
                Sheet sheet = wb.getSheetAt(i);
                for (Row row : sheet) {
                    List<String> cellList = new ArrayList<>();
                    for (Cell cell : row) {
                        cellList.add(getCellValue(cell));
                    }
                    rowList.add(cellList);
                }
                excelMap.put(sheet.getSheetName(), rowList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Excel WorkBook 读取失败！ " + e.getMessage());
            throw new Exception("EXCEL 数据读取错误！");
        }finally {
            if(wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("Excel WorkBook 关闭失败！ " + e.getMessage());
                    throw new Exception("EXCEL 数据读取错误！");
                }
            }
        }
        return excelMap;
    }
    
    /**
     * 获取对应的sheet索引数据
     * @param is
     * @param sheetNum
     * @return
     * @throws Exception
     */
    public static List<List<String>> readExcelToListBySheetIndex(InputStream is, int sheetIndex) throws Exception {
        List<List<String>> rowList = new ArrayList<>();
        Workbook wb = getWorkBook(is);
        try {
            Sheet sheet = wb.getSheetAt(sheetIndex);
            for (Row row : sheet) {
                List<String> cellList = new ArrayList<>();
                for (Cell cell : row) {
                    cellList.add(getCellValue(cell));
                }
                rowList.add(cellList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Excel WorkBook 读取失败！ " + e.getMessage());
            throw new Exception("EXCEL 数据读取错误！");
        }finally {
            if(wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("Excel WorkBook 读取失败！ " + e.getMessage());
                    throw new Exception("EXCEL 数据读取错误！");
                }
            }
        }
        return rowList;
    }

    /**
     * Cell value
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_STRING:
            cellValue = cell.getRichStringCellValue().getString();
            break;
        case Cell.CELL_TYPE_NUMERIC:
            if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                cellValue = DateUtil.dateToStringShort(cell.getDateCellValue());
            } else {
                cellValue = String.valueOf(cell.getNumericCellValue());
            }
            break;
        case Cell.CELL_TYPE_BOOLEAN:
            cellValue = String.valueOf(cell.getBooleanCellValue());
            break;
        case Cell.CELL_TYPE_FORMULA:
            cellValue = String.valueOf(cell.getCellFormula());
            break;
        default:
            cellValue = "";
        }
        return cellValue;
    }

}
