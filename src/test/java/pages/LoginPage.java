package pages;

import jdk.jshell.execution.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.Utility;

import java.io.IOException;
import java.io.ObjectInputFilter;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUserName(String userName) throws IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.id(Utility.fetchElementValue("userEmail"))).sendKeys(userName);
    }

    public void enterPassword(String password) throws IOException {
        driver.findElement(By.id(Utility.fetchElementValue("userPassword"))).sendKeys(password);

    }

    public void confirmLogin() throws IOException {
        driver.findElement(By.name(Utility.fetchElementValue("btn_login"))).click();

    }
}
