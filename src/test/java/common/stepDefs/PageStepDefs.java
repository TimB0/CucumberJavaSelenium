package common.stepDefs;

import common.config.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.drivers.SingletonDriver;

import cucumber.api.java.en.When;

public class PageStepDefs {

    private static final WebDriver driver = SingletonDriver.getDriver();
    private PropertyLoader propertyLoader = PropertyLoader.getPropertyLoader();

    @When("^I click on \"([^\"]*)\" link on page$")
    public void i_click_on_link_on_page(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

}
