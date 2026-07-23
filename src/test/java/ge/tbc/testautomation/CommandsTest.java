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

public class CommandsTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false
        ));
        options.addArguments("--disable-features=PasswordLeakDetection,AutofillServerCommunication");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(Constants.DYNAMIC_CONTROLS_URL);
    }

    @Test(priority = 1, description = "check if heading and description is correct")
    public void labelTest() {
        WebElement heading = driver.findElement(By.xpath("//div[@class='example']/h4"));
        Assert.assertEquals(heading.getText(), Constants.HEADING);

        WebElement description = driver.findElement(By.xpath("//div[@class='example']/p"));
        Assert.assertEquals(description.getText(), Constants.DESCRIPTION_TEXT);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}