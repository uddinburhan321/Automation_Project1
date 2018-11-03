package Yahoo;

import Reusable_Objects.Reusable_Methods_Loggers;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import jxl.write.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Yahoo_Search_Result {

    WebDriver driver;
    ExtentReports report;
    ExtentTest logger;

    @BeforeSuite
    public  void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized", "incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport" + UUID.randomUUID() + ".html", true);
        report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport.html",true);

    }

    @Test
    public void yahoo_Search_Res() throws InterruptedException, IOException {
        logger = report.startTest("Yahoo Search Result");
        Reusable_Methods_Loggers.naviagteMethod(logger,driver,"https://www.yahoo.com");
        String yahooTitle = driver.getTitle();
        if(yahooTitle.equalsIgnoreCase("Yahoo")){
            logger.log(LogStatus.PASS,"The yahoo title matched");
        }else{
            logger.log(LogStatus.FAIL,"The yahoo tittle didn't match with " + yahooTitle);
        }
        List<WebElement> linkCount = driver.findElements(By.xpath("//*[contains(@class,'Mstart(')]"));
        logger.log(LogStatus.INFO, "The link count is " + linkCount.size());
        Reusable_Methods_Loggers.sendMethod(logger,driver,"//*[@name='p']","Brooklyn","Search Field");
        Reusable_Methods_Loggers.clickMethod(logger,driver,"//*[@id='uh-search-button']","Search Icon");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        logger.log(LogStatus.INFO,"Scrolling to the bottom of the page");
        Thread.sleep(1500);
        jse.executeScript("scroll(0,2000)");
    }

    @AfterSuite
    public void closeBrowser(){
        report.endTest(logger);
        report.flush();
        report.close();
        //driver.quit();
    }
}
