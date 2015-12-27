package common.stepDefs;

import org.openqa.selenium.WebDriver;

import common.drivers.BrowserType;
import common.drivers.SingletonBrowserDriver;

public interface RootStepDefs {
	
	public static final WebDriver driver = SingletonBrowserDriver.getWebDriver(BrowserType.CHROME);
	
}
