package common.stepDefs;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import common.drivers.SingletonDriver;

public class NavigationStepDefs {

    private WebDriver driver = SingletonDriver.getDriver();

    @Given("^I visit \"([^\"]*)\" page$")
    public void i_visit_page(String URL) {
        driver.navigate().to(URL);
    }

    @Then("^I am on \"([^\"]*)\" Page$")
    public void i_am_on_Page(String title) {
//		System.out.println("#title = " + driver.getTitle());
        Assert.assertEquals("title was not equal to driver.getTitle() - title = " + title + " driver.getTitle = " + driver.getTitle(), driver.getTitle(), title);
    }

}
