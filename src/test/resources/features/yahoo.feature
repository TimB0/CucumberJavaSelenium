Feature: Test Yahoo.com website

  @wip
  Scenario: As a user, i should visit yahoo.com website on browser
    Given I visit "https://uk.yahoo.com/" page
    Then I am on "Yahoo" Page
    When I click on "News" link on page
    Then I am on "Yahoo News UK" Page

  @wip
  Scenario: As a user, i should visit google.com website on browser
    Given I visit "http://www.google.com" page
    Then I am on "Google" Page
