package common.stepDefs;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import common.drivers.BrowserType;
import common.drivers.SingletonBrowserDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.When;

public class PageStepDefs {
	
public static final WebDriver driver = SingletonBrowserDriver.getWebDriver(BrowserType.CHROME);
	
	@Before(value = {"@wip"})
	public static void initialize() {
		System.out.println("Before-1");
//		getWebDriver(BrowserType.CHROME);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
//		Runtime.getRuntime().addShutdownHook(new Thread(){
//			public void run() {
//				if (driver == null) {
//					System.out.println("shutdown hook - 1");
//					return;
//				} else {
//					System.out.println("shutdown hook - 2");
//					driver.quit();
//				}
//			}
//		});
	}
	
	@After(value = {"@wip"})
	public static void tearDown(Scenario scenario) {
		System.out.println("After - 1");
		if (scenario.isFailed()) {
			System.out.println("After - 2");
			byte[] screenshot = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		} else {
			if (driver == null) {
				System.out.println("shutdown hook - 1");
				return;
			} else {
				System.out.println("shutdown hook - 2");
				driver.quit();
			}
		}
	}

	@When("^I click on \"([^\"]*)\" link on page$")
	public void i_click_on_link_on_page(String linkText) throws Throwable {
		driver.findElement(By.linkText(linkText)).click();
	}

}
