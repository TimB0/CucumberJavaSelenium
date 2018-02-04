package common.stepDefs;

import common.config.PropertyLoader;
import common.drivers.SingletonDriver;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageStepDefs {

    private static final WebDriver driver = SingletonDriver.getDriver();
    private PropertyLoader propertyLoader = PropertyLoader.getPropertyLoader();

    @When("^I click on \"([^\"]*)\" link on page$")
    public void i_click_on_link_on_page(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    @Then("^I wait for (\\d+) seconds$")
    public void i_wait_for_seconds(int time) throws Throwable {
        Thread.sleep(time * 1000);
    }

}
