package ru.cian;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadExcel {
    public static String action;
    public static String type;
    public static String city;
    public static String money;

    public static void main(String[] args) throws FileNotFoundException, IOException{
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("D:\\Java\\tz\\cian.xls"));
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);

        action = String.valueOf(row.getCell(0).getStringCellValue());
        System.out.println(action);
        type = String.valueOf(row.getCell(1).getStringCellValue());
        System.out.println(type);
        city = String.valueOf(row.getCell(2).getStringCellValue());
        System.out.println(city);
        money = String.valueOf(row.getCell(3).getStringCellValue());
        System.out.println(money);

    }
}
