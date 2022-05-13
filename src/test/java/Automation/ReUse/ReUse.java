package Automation.ReUse;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ReUse {
    private WebDriverWait wait ;
    private static  JavascriptExecutor js ;
    public static    WebDriver driver ;
    private Select select ;
    public ReUse ( WebDriver driver ) {
        this.driver = driver ;
        //this.wait = new WebDriverWait(driver  , Duration.ofSeconds(5));
        this.js = (JavascriptExecutor) driver ;

    }
    public boolean VerifyUrl(String url ) {
        System.out.println(" Url Current : " + driver.getCurrentUrl());
        System.out.println(url);
        return driver.getCurrentUrl().contains(url);
    }

    public  boolean VerifyTextElement  (By element , String textexpect ) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
        return driver.findElement(element).getText().equals(textexpect);
    }



    public  void ClickElement(By element ) {
        WaitForPageLoad();
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        WebElement webElement = driver.findElement(element);
        js.executeScript("arguments[0].style.border='5px dotted yellow'",webElement);
        driver.findElement(element).click();
    }
    public  void SetText(By element, String keys ) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        WebElement webElement = driver.findElement(element);
        driver.findElement(element).clear();
        driver.findElement(element).click();
        driver.findElement(element).sendKeys(keys);
        Thread.sleep(500);
    }

    public static void HighLightElement ( WebElement button ) {
        //WebElement webElement = driver.findElement(button);
        js.executeScript("arguments[0].style.border='5px dotted yellow'",button);
    }
    public void SwitchFram (String frame ) {
        WebElement  frame1 = driver.findElement(By.id(frame));
        driver.switchTo().frame(frame);
    }
    public  void ScrollElement(By element ) {
        WaitForPageLoad();
        WebElement webElement = driver.findElement(element);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        js.executeScript("arguments[0].scrollIntoView();", webElement);
    }
    public void  Select( String value,  By element){
        driver.findElement(element);
        select.selectByVisibleText(value);
    }
    public  void GetAtribute(By element ) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        WebElement webElement = driver.findElement(element);
        js.executeScript("arguments[0].style.border='5px dotted yellow'",webElement);
        String atribute = webElement.getAttribute("value");
        int  len = atribute.length();
        System.out.println(atribute);
        System.out.println(len);
    }

    public void WaitForPageLoad(){
        // wait for jQuery to loaded
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        try {
            wait = new WebDriverWait( driver, Duration.ofSeconds(10));
            wait.until(jQueryLoad);
            wait.until(jsLoad);
        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang.");
        }

    }
}
