package ru.cian;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.After;
import org.junit.Before;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class WebDriverSettings {
    ChromeDriver driver;
    String action;
    String type;
    String city;
    String money;

    @Before
    public void setup() throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(".\\src\\main\\resources\\cian.xls"));
        //HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("D:\\Java\\tz\\cian.xls"));
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);

        action = String.valueOf(row.getCell(0).getStringCellValue());
        //System.out.println(action);
        type = String.valueOf(row.getCell(1).getStringCellValue());
        //System.out.println(type);
        city = String.valueOf(row.getCell(2).getStringCellValue());
        //System.out.println(city);
        money = String.valueOf(row.getCell(3).getStringCellValue());
        //System.out.println(money);

        System.setProperty("webdriver.chrome.driver", "D:/Java/chromedriver/chromedriver.exe");
        //driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        System.out.println("test starts");
    }
/*
    @After
    public void close(){
        driver.quit();
        System.out.println("test stops");
    }
*/
}
