package common.stepDefs;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class NavigationStepDefs implements RootStepDefs {

	@Given("^I visit \"([^\"]*)\" page$")
	public void i_visit_page(String URL) throws Throwable {
		driver.navigate().to(URL);
	}

	@Then("^I am on \"([^\"]*)\" Page$")
	public void i_am_on_Page(String title) throws Throwable {
		Assert.assertEquals("", driver.getTitle(), title);
	}

}
