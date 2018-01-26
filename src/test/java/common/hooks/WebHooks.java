package common.hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import common.drivers.SingletonDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class WebHooks {

    private static WebDriver driver;

    @Before(value = {"@wip"})
    public static void initialize() {
        System.out.println("Before-1");
        driver = SingletonDriver.getDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @After(value = {"@wip"})
    public static void tearDown(Scenario scenario) {
        System.out.println("After - 1");
        if (scenario.isFailed()) {
            System.out.println("After - 2");
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
    }

}
