package common.stepDefs;

import common.config.PropertyLoader;
import common.drivers.SingletonDriver;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import static org.assertj.core.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class FormStepDefs {

    private static final String FIELD_KEY_NAME = "Name";
    private static final String FORM_FIELD_VALUE = "Value";
    private static final String FORM_FIELD_TYPE = "Type";

    private static final String ELEMENT_ATTR_TYPE = "type";
    private static final String ELEMENT_ATTR_VALUE = "value";

    private WebDriver driver = SingletonDriver.getDriver();
    private PropertyLoader propertyLoader = PropertyLoader.getPropertyLoader();

    @Then("^I fill the form with data:$")
    public void i_fill_the_form_with_data(DataTable dataTable) throws Throwable {
        //this gets 1st row column as keys, and rest values
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for(Map<String, String> column: data) {
            String fieldKey = column.get(FIELD_KEY_NAME);
            String fieldLocatorKey = propertyLoader.getParsedProperty(fieldKey);
            String fieldLocatorValue = propertyLoader.getProperty(fieldLocatorKey);
            String locatorType = getLocatorType(fieldLocatorKey);
            String fieldValue = column.get(FORM_FIELD_VALUE);
            String fieldType = column.get(FORM_FIELD_TYPE);

            switch (fieldType.toLowerCase()) {
                case "text" : {
                    WebElement field = getElement(locatorType, fieldLocatorValue);
                    assertThat(field.getAttribute(ELEMENT_ATTR_TYPE)).isEqualToIgnoringCase(fieldType);
                    field.sendKeys(fieldValue);
                    break;
                }
                case "radio" : {
                    //radio always comes in group, hence find by name is safest option
                    List<WebElement> radioGroup = getElementsByName(fieldLocatorValue);
                    assertThat(radioGroup.stream()
                            .allMatch(e -> e.getAttribute(ELEMENT_ATTR_TYPE).equalsIgnoreCase(fieldType))
                    ).isTrue();
                    selectElementByValue(fieldValue, radioGroup);
                    break;
                }
                case "checkbox" : {
                    //checkbox always comes in group, hence find by name is safest option
                    List<WebElement> checkboxGroup = getElementsByName(fieldLocatorValue);
                    assertThat(checkboxGroup.stream()
                            .allMatch(e -> e.getAttribute(ELEMENT_ATTR_TYPE).equalsIgnoreCase(fieldType))
                    ).isTrue();
                    selectElementByValue(fieldValue, checkboxGroup);
                    break;
                }
                case "textarea" : {
                    WebElement field = getElement(locatorType, fieldLocatorValue);
                    assertThat(field.getTagName()).isEqualToIgnoringCase("textarea");
                    //clear first. This brings the field into focus.
                    field.clear();
                    field.sendKeys(fieldValue);
                    break;
                }
            }
        }
    }

    private void selectElementByValue(String fieldValue, List<WebElement> elements) {
        boolean notClicked = true;
        for(WebElement radio: elements) {
            String value = radio.getAttribute(ELEMENT_ATTR_VALUE);
            if(value.equalsIgnoreCase(fieldValue)) {
                radio.click();
                notClicked = false;
                break;
            }
        }
        if(notClicked) {
            System.err.println("Element with Field Value : " + fieldValue + " NOT FOUND in group - " + elements);
            fail("Element not found with given value : %s in the group of elements provided - %s.", fieldValue, elements);
        }
    }

    /**
     * It returns locator type like - id, name, xpath etc.
     * default value returned is 'id'.
     * @param locatorKey - key from property file
     * @return - locator type
     */
    private String getLocatorType(String locatorKey) {
        if(locatorKey.endsWith("_id")) {
            return "id";
        } else if(locatorKey.endsWith("_name")) {
            return "name";
        } else if (locatorKey.endsWith("_xpath")) {
            return "xpath";
        } else {
            return "id";
        }
    }

    /**
     * @param locatorType
     * @param locatorValue
     * @return
     */
    private WebElement getElement(String locatorType, String locatorValue) {
        WebElement element = null;
        switch (locatorType) {
            case "id" : {
                element = driver.findElement(By.id(locatorValue));
                break;
            }
            case "name" : {
                element = driver.findElement(By.name(locatorValue));
                break;
            }
            case "xpath" : {
                element = driver.findElement(By.xpath(locatorValue));
                break;
            }
        }
        return element;
    }


    /** returns all elements by name - used for radio/check=box kind of fields
     * @return List<WebElement> that met criteria
     */
    private List<WebElement> getElementsByName(String name) {
        return driver.findElements(By.name(name));
    }

}
