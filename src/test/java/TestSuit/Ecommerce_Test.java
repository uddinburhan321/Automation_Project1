package TestSuit;

import Reusable_Objects.Reusable_Methods_Loggers;
import Utilities.Abstract_Class;
import Utilities.Abstract_Class_Parallel;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.io.IOException;

public class Ecommerce_Test extends Abstract_Class_Parallel {
    @Test
    public void Test1() throws IOException, InterruptedException {
        Reusable_Methods_Loggers.naviagteMethod(logger,driver,"http://automationpractice.com/index.php");
        Reusable_Methods_Loggers.matchTitle(logger,driver,"My-Store","Title");
        Reusable_Methods_Loggers.mouseHoverByIndex(logger,driver,"//*[@class='sf-with-ul']",0, "Women Tab");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.clickMethodByIndex(logger,driver,"//*[@title='T-shirts']",0,"T-shirt Link");
        //JavascriptExecutor jse = (JavascriptExecutor)driver;
        logger.log(LogStatus.INFO,"Scrolling to the bottom of the page");
        Thread.sleep(1500);
        jse.executeScript("scroll(0,600)");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.mouseHover(logger,driver,"//*[@title='Faded Short Sleeve T-shirts']","Product Picture");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.clickMethod(logger,driver,"//*[@title='Add to cart']","Add To Cart");
        Thread.sleep(1500);
        //Reusable_Methods_Loggers.textSelection(logger1,driver,"//*[@class='icon-ok']","Successful Massage Displayed");
        Reusable_Methods_Loggers.textSelectionWithIf(logger,driver,"//*[@id=\"layer_cart\"]/div[1]/div[1]/h2","Product successfully added to your shopping cart","verification Massage");
        Thread.sleep(2500);
        Reusable_Methods_Loggers.clickMethodByIndex(logger,driver,"//*[@class='btn btn-default button button-medium']",0,"Proceed to checkout");
        Thread.sleep(1500);
        //Reusable_Methods_Loggers.clickMethod(logger1,driver,"//*[@id='cart_quantity_up_1_1_0_0']","Add More Button");
        //Reusable_Methods_Loggers.clickMethod(logger1,driver,"//*[@id='cart_quantity_up_1_1_0_0']","Add More Button");
        Reusable_Methods_Loggers.sendMethod(logger,driver,"//*[@class='cart_quantity_input form-control grey']","3","Quantity Change");
        Thread.sleep(1500);
        jse.executeScript("scroll(0,300)");
        Thread.sleep(1500);
        //Reusable_Methods_Loggers.clickMethod(logger1,driver,"//*[@class='button btn btn-default standard-checkout button-medium']","Proceed to checkout");
        Reusable_Methods_Loggers.clickMethodByIndex(logger,driver,"//*[@title='Proceed to checkout']",1,"Proceed to checkout");
        Thread.sleep(1500);
    }
}
