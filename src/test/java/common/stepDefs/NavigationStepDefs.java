package common.stepDefs;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import common.drivers.BrowserType;
import common.drivers.SingletonBrowserDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class NavigationStepDefs {

    public static final WebDriver driver = SingletonBrowserDriver
            .getWebDriver(BrowserType.CHROME);

    @Given("^I visit \"([^\"]*)\" page$")
    public void i_visit_page(String URL) throws Throwable {
        driver.navigate().to(URL);
    }

    @Then("^I am on \"([^\"]*)\" Page$")
    public void i_am_on_Page(String title) throws Throwable {
//		System.out.println("#title = " + driver.getTitle());
        Assert.assertEquals("title was not equal to driver.getTitle() - title = " + title + " driver.getTitle = " + driver.getTitle(), driver.getTitle(), title);
    }

}
