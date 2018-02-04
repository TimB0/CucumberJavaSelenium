package common.hooks;

import common.drivers.SingletonDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class WebHooks {

    private static WebDriver driver;

    @Before(value = {"@wip", "@demoQa", "@suite"})
    public static void initialize() {
        System.out.println("Before scenario");
        driver = SingletonDriver.getDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @After(value = {"@wip", "@demoQa", "@suite"})
    public static void tearDown(Scenario scenario) {
        System.out.println("After scenario");
        if (scenario.isFailed()) {
            System.out.println("After scenario - failed");
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
    }

}
