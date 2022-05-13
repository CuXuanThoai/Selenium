package Automation.TestCases;

import Automation.BaseSetup.BaseSetup;
import Automation.Page.AddcCart.AddCart;
import Automation.ReUse.ReUse;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TcAddCart extends BaseSetup {
    WebDriver driver;
    AddCart addCart ;
    @BeforeClass
    public void setUp() {
        driver = getDriver();
    }

    @Test(priority = 1)
    public void AddCart () throws InterruptedException {
        addCart = new AddCart(driver);
        driver.get("http://automationpractice.com/index.php");
        addCart.searchItem("Dress");
    }
    @Test(priority = 2)
    public void choiseItems(){
        addCart.choiceItems("Reference: Highest first");
    }
    @Test(priority = 3)
    public void verify(){
        addCart.Verify();
    }
}
