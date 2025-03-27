package testcases;

import dataGenerators.DataGenerator;
import driverFactory.DriverFactory;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.IOException;

public class Login extends DriverFactory {

    @Test(dataProvider = "Excel",dataProviderClass = DataGenerator.class)
    public void dataProviderLogin(String email,String password) throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(email);
        loginPage.enterPassword(password);
        loginPage.confirmLogin();
    }

}

