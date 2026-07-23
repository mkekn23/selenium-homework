package ge.tbc.testautomation;

import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class WebElementTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false
        ));
        options.addArguments("--disable-features=PasswordLeakDetection,AutofillServerCommunication");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(Constants.DRAG_AND_DROP_URL);
    }


    @Test(priority = 1, description = "column's dimenssions test")
    public void dimensionTest(){
        WebElement columnA = driver.findElement(By.id("column-a"));
        WebElement columnB = driver.findElement(By.id("column-b"));

        int yLocationA = columnA.getLocation().getY();
        int yLocationB = columnB.getLocation().getY();
        Assert.assertEquals(yLocationA, yLocationB);

        Assert.assertEquals(columnA.getAttribute("draggable"), "true");
        Assert.assertEquals(columnB.getAttribute("draggable"), "true");
    }

    @Test(priority = 2, description = "text with link test")
    public void linkTest() {
        WebElement link = driver.findElement(By.linkText("Elemental Selenium"));
        Assert.assertEquals(link.getAttribute("href"), Constants.ELEMENTAL_SELENIUM_URL);
    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
