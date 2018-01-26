Feature: Test dummy website under zap

  @zap
  Scenario: As a user, i should visit dummy website on browser
    Given I visit "https://avinash-anand.github.io/coursera-angularjs/module3-solution/" page
    Then I am on "Narrow Down Your Menu Choice" Page
