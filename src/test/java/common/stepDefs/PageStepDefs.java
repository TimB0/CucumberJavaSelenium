package common.stepDefs;

import org.openqa.selenium.By;

import cucumber.api.java.en.When;

public class PageStepDefs implements RootStepDefs {

	@When("^I click on \"([^\"]*)\" link on page$")
	public void i_click_on_link_on_page(String linkText) throws Throwable {
		driver.findElement(By.linkText(linkText)).click();
	}

}
