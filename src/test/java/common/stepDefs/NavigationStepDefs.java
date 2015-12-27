package common.stepDefs;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import common.drivers.BrowserType;
import common.drivers.SingletonBrowserDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class NavigationStepDefs {
	
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
				System.out.println("After shutdown hook - 1");
				return;
			} else {
				System.out.println("After shutdown hook - 2");
				driver.quit();
			}
		}
	}

	@Given("^I visit \"([^\"]*)\" page$")
	public void i_visit_page(String URL) throws Throwable {
		driver.navigate().to(URL);
	}

	@Then("^I am on \"([^\"]*)\" Page$")
	public void i_am_on_Page(String title) throws Throwable {
		Assert.assertEquals("", driver.getTitle(), title);
	}

}
