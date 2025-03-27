package assertions;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class Compare {

    public boolean validatePageURL(WebDriver driver, String expectedURL){
        boolean flag = Objects.requireNonNull(driver.getCurrentUrl()).equalsIgnoreCase(expectedURL);

        return flag;
    }
}
