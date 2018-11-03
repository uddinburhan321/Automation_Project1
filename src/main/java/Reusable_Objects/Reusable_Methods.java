package Reusable_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyInput;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import java.security.KeyPair;

public class Reusable_Methods {
    public static void clickMethod(WebDriver driver, String locator, String elementName){
        Actions mouseMovement = new Actions(driver);
        try{
            System.out.println("Clicking on element " + elementName);
            WebElement clickBtn = driver.findElement(By.xpath(locator));
            mouseMovement.moveToElement(clickBtn).click().perform();
        }catch (Exception err){
            System.out.println("Unable to click on element " + elementName);
        }
    }

    public  static  void clickMethodByIndex( WebDriver driver, String locator, int indexNumber, String elementName){
        Actions mouseMovement = new Actions(driver);
        try{
            System.out.println("Clicking on element " + elementName);
            WebElement clickBtn = driver.findElements(By.xpath(locator)).get(indexNumber);
            mouseMovement.moveToElement(clickBtn).click().perform();
        }catch (Exception err){
            System.out.println("Unable to send to element " + elementName);
        }
    }

    public  static  void sendMethod( WebDriver driver, String locator, String userInput, String elementName){
        Actions mouseMovement = new Actions(driver);
        try{
            System.out.println("Sending to " + userInput + " an element " + elementName);
            WebElement sendKey = driver.findElement(By.xpath(locator));
            mouseMovement.moveToElement(sendKey).click().sendKeys(userInput).perform();
        }catch (Exception err){
            System.out.println("Unable to send to element " + elementName);
        }
    }

    public  static  void sendMethodByIndex( WebDriver driver, String locator, String userInput, int indexNumber, String elementName){
        Actions mouseMovement = new Actions(driver);
        try{
            System.out.println("Sending to " + userInput + " an element " + elementName);
            WebElement sendKey = driver.findElements(By.xpath(locator)).get(indexNumber);
            mouseMovement.moveToElement(sendKey).click().sendKeys(userInput).perform();
        }catch (Exception err){
            System.out.println("Unable to send to element " + elementName);
        }
    }

    public  static  void clearText( WebDriver driver, String locator, String elementName){
        Actions mouseMovement = new Actions(driver);
        try{
            System.out.println("Clearing text field of " + elementName);
            WebElement clearTxt = driver
                    .findElement(By.xpath(locator));
            mouseMovement.moveToElement(clearTxt)
            .doubleClick()
            .sendKeys(Keys.BACK_SPACE)
            .perform();
        }catch (Exception err){
            System.out.println("Unable to process request for element " + elementName);
        }
    }

    public static void matchTitle(WebDriver driver, String expectedTitle, String elementName) {
        Actions mouseMovement = new Actions(driver);
        try{
            String title = driver.getTitle();
            if(title.equals(expectedTitle)){
                System.out.println("Title matcheed");
            }else{
                System.out.println("Title didn't match");
            }
        }catch (Exception err){
            System.out.println("Couldn't proceed");
        }
    }
    public static void SelectMethod(WebDriver driver,String locator, String userInput, String elementName){
        Actions mouseMovement = new Actions(driver);
        try{
            System.out.println("Selecting item from the drop down of " + elementName);
            WebElement listItem = driver.findElement(By.xpath(locator));
            Select itemDropdown = new Select(listItem);
            itemDropdown.selectByVisibleText(userInput);
        }catch (Exception err){
            System.out.println("Unable to process request for " + elementName);
        }
    }

    public static String textSelectionIndx(WebDriver driver, String locator, int indexNum, String elementName){
        Actions mouseMovement = new Actions(driver);
        String text = null;
        try{
            System.out.println("Captered text is: " + elementName);
            WebElement txtElement = driver.findElements(By.xpath(locator)).get(indexNum);
            text = txtElement.getText();
            //mouseMovement.moveToElement(txtElement).doubleClick().sendKeys(Keys.CONTROL, "c").perform();
        }catch (Exception err){
            System.out.println("Unable to find the specific text of: " + elementName);
        }
        return text;
    }
}
