@smoke
Feature: Login with Suspended Account

  As a suspended user
  I want to log in to the system
  So that I can verify the account restriction behavior

  Scenario: Attempt to login with a suspended account
    When the user attempts to login with suspended credentials
    Then the system should show a account disabled message
