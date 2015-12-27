package common.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SingletonBrowserDriver {

	private static WebDriver driver;

	public static WebDriver getWebDriver(BrowserType type) {
		if (driver == null) {
			switch (type) {
			case FIREFOX: {
				FirefoxProfile fp = new FirefoxProfile();
				fp.setAcceptUntrustedCertificates(true);
				driver = new FirefoxDriver(fp);
				break;
			}
			case CHROME: {
				System.setProperty("webdriver.chrome.driver",
						"chrome/chromedriver.exe");
				ChromeOptions co = new ChromeOptions();
				DesiredCapabilities dc = DesiredCapabilities.chrome();
				dc.setCapability(ChromeOptions.CAPABILITY, co);
				driver = new ChromeDriver(dc);
				break;
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

}
