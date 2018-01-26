package common.runner;

import common.drivers.SingletonDriver;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

@RunWith(value = Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"common.stepDefs", "common.hooks"},
        tags = {"@zap"},
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"})
public class ZapRunner {

    private static WebDriver driver;

    @BeforeClass
    public static void beforeAll() {
        System.setProperty("browser", "firefox");
        System.setProperty("proxy.enabled", "true");
        System.setProperty("proxy.path", "localhost:11111");
        driver = SingletonDriver.getDriver();
    }

    @AfterClass
    public static void afterAll() {
        if(Objects.nonNull(driver)) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("SHUTDOWN HOOK STARTED!!!");
                driver.quit();
                System.out.println("SHUTDOWN HOOK COMPLETED!!");
            }));
        }
    }

}
