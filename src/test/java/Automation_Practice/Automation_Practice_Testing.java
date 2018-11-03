package Automation_Practice;

import Reusable_Objects.Reusable_Methods_Loggers;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Automation_Practice_Testing {
    WebDriver driver;
    ExtentReports report;
    ExtentTest logger1;
    ExtentTest logger2;
    JavascriptExecutor jse;

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

    @Test
    public void Test1() throws IOException, InterruptedException {
        logger1 = report.startTest("Proceed to Check out for Tshirts");
        Reusable_Methods_Loggers.naviagteMethod(logger1,driver,"http://automationpractice.com/index.php");
        Reusable_Methods_Loggers.matchTitle(logger1,driver,"My-Store","Title");
        Reusable_Methods_Loggers.mouseHoverByIndex(logger1,driver,"//*[@class='sf-with-ul']",0, "Women Tab");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.clickMethodByIndex(logger1,driver,"//*[@title='T-shirts']",0,"T-shirt Link");
        //JavascriptExecutor jse = (JavascriptExecutor)driver;
        logger1.log(LogStatus.INFO,"Scrolling to the bottom of the page");
        Thread.sleep(1500);
        jse.executeScript("scroll(0,600)");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.mouseHover(logger1,driver,"//*[@title='Faded Short Sleeve T-shirts']","Product Picture");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.clickMethod(logger1,driver,"//*[@title='Add to cart']","Add To Cart");
        Thread.sleep(1500);
        //Reusable_Methods_Loggers.textSelection(logger1,driver,"//*[@class='icon-ok']","Successful Massage Displayed");
        Reusable_Methods_Loggers.textSelectionWithIf(logger1,driver,"//*[@id=\"layer_cart\"]/div[1]/div[1]/h2","Product successfully added to your shopping cart","verification Massage");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.clickMethod(logger1,driver,"//*[@title='Proceed to checkout']","Proceed to checkout");
        Thread.sleep(1500);
        //Reusable_Methods_Loggers.clickMethod(logger1,driver,"//*[@id='cart_quantity_up_1_1_0_0']","Add More Button");
        //Reusable_Methods_Loggers.clickMethod(logger1,driver,"//*[@id='cart_quantity_up_1_1_0_0']","Add More Button");
        Reusable_Methods_Loggers.sendMethod(logger1,driver,"//*[@class='cart_quantity_input form-control grey']","3","Quantity Change");
        Thread.sleep(1500);
        jse.executeScript("scroll(0,300)");
        Thread.sleep(1500);
        //Reusable_Methods_Loggers.clickMethod(logger1,driver,"//*[@class='button btn btn-default standard-checkout button-medium']","Proceed to checkout");
        Reusable_Methods_Loggers.clickMethodByIndex(logger1,driver,"//*[@title='Proceed to checkout']",1,"Proceed to checkout");
        Thread.sleep(1500);
    }

    @Test(dependsOnMethods = "Test1")
    public void Test2() throws IOException, InterruptedException {
        logger2 = report.startTest("Procedd to Checkout for Summer Dresses");
        Reusable_Methods_Loggers.mouseHoverByIndex(logger2,driver,"//*[@class='sf-with-ul']", 3,"Dresses Tab");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.clickMethodByIndex(logger2,driver,"//*[@title='Summer Dresses']",1,"Summer Dresses");
        //JavascriptExecutor jse = (JavascriptExecutor)driver;
        logger1.log(LogStatus.INFO,"Scrolling to the bottom of the page");
        Thread.sleep(1500);
        jse.executeScript("scroll(0,600)");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.mouseHoverByIndex(logger2,driver,"//*[@class='left-block']",0,"First Summer Dress");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.clickMethodByIndex(logger2,driver,"//*[@title='View']",0,"More Tab");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.clearTextByIndex(logger2,driver,"//*[@id='quantity_wanted']",0,"Quantity Text");
        Reusable_Methods_Loggers.sendMethodByIndex(logger2,driver,"//*[@id='quantity_wanted']","4",0,"Quantity Text Field");
        Reusable_Methods_Loggers.SelectMethod(logger2,driver,"//*[@id='group_1']","M","Size selection");
        Reusable_Methods_Loggers.clickMethod(logger2,driver,"//*[@class='exclusive']","Add to Cart");
        //Reusable_Methods_Loggers.textSelection(logger1,driver,"//*[@class='icon-ok']","Successful Massage Displayed");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.textSelectionWithIf(logger2,driver,"//*[@id=\"layer_cart\"]/div[1]/div[1]/h2","Product successfully added to your shopping cart","verification Massage");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.clickMethod(logger1,driver,"//*[@title='Proceed to checkout']","Proceed to checkout");
        Thread.sleep(1500);
        jse.executeScript("scroll(0,350)");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.clickMethod(logger2,driver,"//*[@class='cart_quantity_delete']","Delete Button");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.clickMethod(logger2,driver,"//*[@class='cart_quantity_delete']","Delete Button");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.textSelectionWithIf(logger2,driver,"//*[@class='alert alert-warning']","Your shopping cart is empty.","verification Massage");
    }

    @AfterSuite
    public void closeBrowser(){
        report.endTest(logger1);
        report.endTest(logger2);
        report.flush();
        report.close();
        driver.quit();
    }
}
