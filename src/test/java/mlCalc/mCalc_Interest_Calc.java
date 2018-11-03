package mlCalc;

import Reusable_Objects.Reusable_Methods;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class mCalc_Interest_Calc {
    WebDriver driver;
    Workbook readableFile;
    Sheet readableSheet;
    WritableWorkbook writableFile;
    WritableSheet wrSheet;
    int rows;
    String intCalc;
    Label label;


    @BeforeSuite
    public void OpenBrowser() throws IOException, BiffException {
        readableFile = Workbook.getWorkbook(new File("src\\main\\resources\\mlCalc_Read.xls"));
        readableSheet = readableFile.getSheet(0);
        writableFile = Workbook.createWorkbook(new File("src\\main\\resources\\mlCalc_Write.xls"), readableFile);
        wrSheet = writableFile.getSheet(0);
        rows = wrSheet.getRows();
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized", "incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void Test1() throws InterruptedException {

        for (int i = 1; i < rows; i++) {

            intCalc = readableSheet.getCell(0, i).getContents();

            driver.navigate().to("https://www.mlcalc.com");

            Reusable_Methods.matchTitle(driver, "Mortgage Calculator", "Title");

            Reusable_Methods.clearText(driver, "//*[@name='ma']", "Purchase Price");
            Reusable_Methods.sendMethod(driver, "//*[@name='ma']", intCalc, "Purchase Price");

            intCalc = readableSheet.getCell(1, i).getContents();

            Reusable_Methods.clearText(driver, "//*[@name='dp']", "Down Payment");
            Reusable_Methods.sendMethod(driver, "//*[@name='dp']", intCalc, "Down Payment");

            intCalc = readableSheet.getCell(2, i).getContents();

            Reusable_Methods.clearText(driver, "//*[@name='ir']", "Interest Rate");
            Reusable_Methods.sendMethod(driver, "//*[@name='ir']", intCalc, "Interest Rate");

            intCalc = readableSheet.getCell(3, i).getContents();

            Reusable_Methods.clearText(driver, "//*[@name='zipCode']", "Zipcode");
            Reusable_Methods.sendMethod(driver, "//*[@name='zipCode']", intCalc, "Zipcode");

            intCalc = readableSheet.getCell(4, i).getContents();

            Reusable_Methods.SelectMethod(driver, "//*[@name='sm']", intCalc, "Payment Month");

            intCalc = readableSheet.getCell(5, i).getContents();

            Reusable_Methods.SelectMethod(driver, "//*[@name='sy']", intCalc, "Payment Year");

            intCalc = readableSheet.getCell(6, i).getContents();

            Reusable_Methods.clickMethod(driver, "//*[@class='button-calculate action']", "Calculate");
            try {
                String text = Reusable_Methods.textSelectionIndx(driver,"//*[@class='big']",0,"Monthly Payment");
                System.out.println(text);
                label = new Label(6, i, text);
                wrSheet.addCell(label);
            } catch (Exception err) {
                System.out.println("Unable to capture text for search result" + err);
            }


            try {
                String text1 = Reusable_Methods.textSelectionIndx(driver,"//*[@class='big']",2,"PayOffDate");
                //String expr = "//*[text()='[A-Z]{1}')]";
                System.out.println(text1);
                label = new Label(7, i, text1);
                wrSheet.addCell(label);
            } catch (Exception err) {
                System.out.println("Unable to capture text for PayoffDate" + err);
            }
        }
    }

    @AfterSuite
    public void closeBrowser() throws IOException, WriteException {
        writableFile.write();
        writableFile.close();
        readableFile.close();
        driver.quit();
    }
}
