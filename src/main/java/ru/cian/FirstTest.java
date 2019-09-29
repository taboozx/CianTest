package ru.cian;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FirstTest extends ru.cian.WebDriverSettings {

    private static String currentURL;
    private static String rooms;
    private static WebElement result;
    private static String resule;

    @Test
    public void firstTest1(){
        System.out.println(action);
        driver.get("http://cian.ru");
        String title = driver.getTitle();
        Assert.assertEquals("ЦИАН – база недвижимости в Москве | Продажа, аренда квартир и другой недвижимости", title);

        try{
            WebDriverWait wait = new WebDriverWait(driver, 8);

            //Ир, непомню как зовут чувака, но он очень хотел xPath...
            driver.findElementByXPath("//*[contains(text(), 'Купить')]").click();
            driver.findElementByXPath("//*[contains(text(),"+ "'"+action+"'"+")]").click();

            driver.findElementByXPath(".//button[contains(text(), 'Квартиру')]").click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[contains(text(), 'Жилая')]")));
            //System.out.println(type);
            driver.findElementByXPath(".//span[contains(text(),"+ "'"+type+"'"+")]").click();

            //скок комнат
            if (type.equals("Квартира")) {
                System.out.println("Да Квартра Выбираем комнаты");
                driver.findElementByXPath(".//button[contains(text(), '1, 2 комн.')]").click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//label[contains(text(), '1-комнатная')]")));
                driver.findElementByXPath(".//label[contains(text(), '3-комнатная')]").click();
                rooms = "1, 2, 3";
            }else {
                System.out.println( type +" - не Квартира");
            }
            WebElement amount = driver.findElementByXPath("//input[@placeholder='до']");
            amount.sendKeys(money);

            WebElement geo = driver.findElementById("geo-suggest-input");
            geo.sendKeys(Keys.CONTROL + "a");
            geo.sendKeys(Keys.DELETE);
            geo.sendKeys(city);
            //Ир, всё как любят твои коллеги - Explicit Wait вместо sleep и implicit

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(text(), 'Адреса')]")));
            geo.sendKeys(Keys.ENTER);

            driver.findElementByXPath("//button[contains(text(), 'Найти')]").click();  //здесь дефект


            //раз говорят дождаться, давайте дождемся
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[contains(text(), 'На карте')]")));
            currentURL = driver.getCurrentUrl();

        }
        catch (Exception e) {
            System.out.println("Ир, твои чуваки победили, завалился первый тест");
        }
    }


    @Test
    public void firstTest2() {
        driver.get(currentURL);
        String title2 = driver.getTitle();
        System.out.println(title2);
        //проверям, что попали куда хотели
        try {
            Assert.assertTrue(title2.contains(action));
            //проверям на верстке, что попали куда хотели
            result = driver.findElementByXPath(".//h1");
            resule = result.getText();
            //Check if String contains a sequence
            System.out.println("Contains sequence "+ action+" : "  + resule.contains(action));
            }
        catch (Exception e) {
            System.out.println("Ир, твои чуваки побели, завалился второй тест - ненайден заданный элемент на верстке ");
        }

        try{
            if (type.equals("Квартира")){
                String type_test;
                type_test = "квартиру";
                //надо сделать case insensitive regexp + отнять последний символ из переменной
                System.out.println("Contains sequence "+ type_test +" : "  + resule.contains(type_test));
                System.out.println("Contains sequence комнты : "  + resule.contains(rooms));
            }
        }
        catch (Exception e) {
            System.out.println("Ир, твои чуваки побели, завалился второй тест - ненайден заданный элемент на верстке ");
        }

       try{
            driver.findElementByXPath(".//h1[contains(text(), 'Москве')]");
        }
        catch (Exception e) {
            System.out.println("Ир, твои чуваки побели, завалился второй тест - нет такого города, как");
        }
    }
}
