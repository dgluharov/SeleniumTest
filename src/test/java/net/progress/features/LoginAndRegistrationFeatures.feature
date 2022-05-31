Feature: Login and registration
  In order to improve my experience
  As an user
  I want login and registration form

  Scenario: User is successfully registered in with valid data
    Given there is a new user
    When the user fills the registration form with appropriate data
    Then the user is successfully registered
    