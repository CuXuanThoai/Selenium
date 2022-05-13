package Automation.Page.AddcCart;

import Automation.BaseSetup.BaseSetup;
import Automation.ReUse.ReUse;
import org.checkerframework.common.util.report.qual.ReportUse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class AddCart {
    WebDriver driver;
    ReUse reUse;
    Actions act ;
    Select slt  ;

    public AddCart(WebDriver driver) {
        this.driver = driver;
        reUse = new ReUse(driver);
        act = new Actions(driver);
    }

    By Search = By.xpath(" //input[@id='search_query_top']");
    By buttonSearch = By.xpath("//button[@class='btn btn-default button-search']");
    By sortBy = By.xpath("//select[@id='selectProductSort']");
    By choiseProduct = By.xpath("//ul[@class='product_list grid row']/li[1]");
    By nameProduct = By.xpath("//ul[@class='product_list grid row']//li[1]//a[@class='product-name']");
    By addCard = By.xpath("//ul[@class='product_list grid row']/li//a[@title='Add to cart']");
    By iconX = By.xpath("//span[@class='cross']");

    By Cart = By.xpath("//span[@class='ajax_cart_product_txt unvisible']");
    By inCart = By.xpath("//td[@class='cart_description']/p/a");
    String acutual ;
    String acutual2 ;

    public void searchItem(String values) {
        driver.findElement(Search).sendKeys(values);
        reUse.WaitForPageLoad();
        driver.findElement(buttonSearch).click();
    }

    public void choiceItems(String values) {
       driver.findElement(sortBy).click();
        reUse.WaitForPageLoad();
        Select slt = new Select(driver.findElement(sortBy));
        slt.selectByVisibleText(values);
        reUse.WaitForPageLoad();

        act.moveToElement(driver.findElement(choiseProduct)).build().perform();
         acutual = driver.findElement(nameProduct).getText();

        reUse.ClickElement(addCard);
        reUse.WaitForPageLoad();
        reUse.ClickElement(iconX);
        //reUse.ClickElement(choiseProduct);
        reUse.WaitForPageLoad();

        System.out.println(" Ten Cua San Pham La : "  +acutual);

    }
    public void Verify (){
    driver.findElement(Cart).click();
    reUse.WaitForPageLoad();
    acutual2 =driver.findElement(inCart).getText();
    System.out.println(" Ten Cua San Pham Trong Gio Hang La : " +acutual2);
    if (acutual2.equals(acutual)){
        System.out.println(" Verify Thanh Cong ");
    }
    else
    {
        System.out.println(" Verify That Bai");
    }
    }
}
