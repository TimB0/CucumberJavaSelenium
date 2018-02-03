package common.stepDefs;

import common.config.PropertyLoader;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import common.drivers.SingletonDriver;

public class NavigationStepDefs {

    private WebDriver driver = SingletonDriver.getDriver();
    private PropertyLoader propertyLoader = PropertyLoader.getPropertyLoader();

    @Given("^I visit \"([^\"]*)\" page$")
    public void i_visit_page(String URL) {
        driver.navigate().to(URL);
    }

    @Then("^I am on \"([^\"]*)\" Page$")
    public void i_am_on_Page(String title) {
        Assert.assertEquals("title was not equal to driver.getTitle() - title = " + title + " driver.getTitle = " + driver.getTitle(), driver.getTitle(), title);
    }

    @Given("^I visit \"([^\"]*)\"$")
    public void i_visit(String pageName) throws Throwable {
        String pageUrl = propertyLoader.getParsedProperty(pageName);
        driver.get(pageUrl);
    }

}
