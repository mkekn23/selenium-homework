package ge.tbc.testautomation;
import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Map;

public class NavigationTest {
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
        driver.get(Constants.ULTIMATE_QA_URL);
    }


    @Test(priority = 1, description = "check if services link works and also goes back")
    public void goToServicesAndBack(){
        WebElement servicesLink = driver.findElement(By.id("menu-item-218392"));
        servicesLink.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("consulting"));

        Assert.assertEquals(driver.getCurrentUrl(), Constants.CONSULTING_URL);

        driver.navigate().back();

        Assert.assertEquals(driver.getCurrentUrl(), Constants.ULTIMATE_QA_URL);
    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
