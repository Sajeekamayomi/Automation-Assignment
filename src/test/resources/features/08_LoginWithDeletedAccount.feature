@smoke
Feature: Login with Deleted Account

  As a deleted user
  I want to log in to the system
  So that I can verify the account restriction behavior

  Scenario: Attempt to login with a deleted account
    When the user attempts to login with deleted credentials
    Then the system should show an account deleted message
