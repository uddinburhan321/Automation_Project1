package Utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Abstract_Class {
    public static WebDriver driver;
    public static ExtentReports report;
    public static JavascriptExecutor jse;
    public static ExtentTest logger;


    @BeforeSuite
    public  void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized", "incognito", "disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport" + UUID.randomUUID() + ".html", true);
        report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport.html",true);
        jse = (JavascriptExecutor)driver;
    }

    @BeforeMethod
    public void loggerSession(Method methodName){
        logger = report.startTest(methodName.getName());
        logger.log(LogStatus.INFO,"Automation Test Suit Started");
    }

    @AfterMethod
    public void endTest(){
        report.endTest(logger);
        logger.log(LogStatus.INFO,"Automation Test Ended");
    }

    @AfterSuite
    public void closeBrowser(){
        //report.endTest(logger);
        report.flush();
        report.close();
        driver.quit();
        logger.log(LogStatus.INFO,"Automation Test Suit Ended");
    }
}
