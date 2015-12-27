package common.stepDefs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.drivers.BrowserType;
import common.drivers.SingletonBrowserDriver;

import cucumber.api.java.en.When;

public class PageStepDefs {

	public static final WebDriver driver = SingletonBrowserDriver
			.getWebDriver(BrowserType.CHROME);

	@When("^I click on \"([^\"]*)\" link on page$")
	public void i_click_on_link_on_page(String linkText) throws Throwable {
		driver.findElement(By.linkText(linkText)).click();
	}

}
