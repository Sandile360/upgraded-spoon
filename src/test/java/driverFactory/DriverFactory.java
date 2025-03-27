package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import util.Utility;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DriverFactory {
    public WebDriver driver;

    @BeforeMethod
    public void startDriverInstance() throws IOException {
        if(Utility.fetchPropertyValue("browser").equalsIgnoreCase("gecko")) {
            driver = new FirefoxDriver();
            driver.navigate().to(Utility.fetchPropertyValue("applicationURL"));
        }

    }

    @AfterMethod
    public void closeDriverInstance(){
        driver.quit();
    }
}
