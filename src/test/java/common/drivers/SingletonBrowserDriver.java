package common.drivers;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public abstract class SingletonBrowserDriver {

	private static WebDriver driver;

	@Before
	public static void initialize() {
		getWebDriver(BrowserType.CHROME);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	public static WebDriver getWebDriver(BrowserType type) {
		if (driver == null) {
			switch (type) {
			case FIREFOX: {
				FirefoxProfile fp = new FirefoxProfile();
				fp.setAcceptUntrustedCertificates(true);
				driver = new FirefoxDriver(fp);
			}
			case CHROME: {
				System.setProperty("webdriver.chrome.driver",
						"chrome/chromedriver.exe");
				ChromeOptions co = new ChromeOptions();
				DesiredCapabilities dc = DesiredCapabilities.chrome();
				dc.setCapability(ChromeOptions.CAPABILITY, co);
				driver = new ChromeDriver(dc);
			}
			default: {
				System.setProperty("webdriver.chrome.driver",
						"chrome/chromedriver.exe");
				ChromeOptions co = new ChromeOptions();
				DesiredCapabilities dc = DesiredCapabilities.chrome();
				dc.setCapability(ChromeOptions.CAPABILITY, co);
				driver = new ChromeDriver(dc);
			}
			}
		}
		return driver;
	}

	@After
	public static void tearDown(Scenario scenario) {
		System.out.println("After - 1");
		if (scenario.isFailed()) {
			System.out.println("After - 2");
			byte[] screenshot = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		} else {
			System.out.println("After - 3");
			if (driver == null) {
				System.out.println("After - 4");
				return;
			} else {
				System.out.println("After - 5");
				driver.quit();
			}
		}
	}

}
