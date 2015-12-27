Feature: Test Google.com website

  @wip
  Scenario: As a user, i should visit google.com website on browser
    Given I visit "http://www.google.com" page
    Then I am on "Google" Page
    When I click on "Gmail" link on page
    Then I am on "Gmail" Page

#  @wip
  Scenario: As a user, i should visit google.com website on browser
    Given I visit "http://www.google.com" page
    Then I am on "Google" Page
    