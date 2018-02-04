Feature: Test DemoQa website

  @demoQa
  Scenario: As a user, I should visit demo qa website and be able to register myself
    Given I visit "home page"
    When I click on "Registration" link on page
    Then I am on "Registration | Demoqa" Page
    And I fill the form with data:
      | Name             | Value                   | Type     |
      | First Name       | avinash                 | text     |
      | Last Name        | anand                   | text     |
      | Marital Status   | Married                 | radio    |
      | Hobby            | Reading                 | checkbox |
      | Country          | India                   | select   |
      | Phone Number     | 1234567890              | text     |
      | Username         | avinash-anand           | text     |
      | Email            | abcd@ef.com             | text     |
      | About Yourself   | This is some demo text. | textarea |
      | Password         | password123             | password |
      | Confirm Password | password123             | password |
    And I wait for 2 seconds
    Then I submit the form
    And I wait for 2 seconds
