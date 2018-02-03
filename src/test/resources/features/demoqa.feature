Feature: Test DemoQa website

  @demoQa
  Scenario: As a user, I should visit demo qa website and be able to register myself
    Given I visit "home page"
    When I click on "Registration" link on page
    Then I am on "Registration | Demoqa" Page
