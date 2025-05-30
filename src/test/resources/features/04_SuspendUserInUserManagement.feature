@smoke
Feature: Suspend User in User Management

  As a Client Admin
  I want to suspend an existing user in the User Management section
  So that the user no longer has access to the warehouse system

  Scenario: Successfully suspend an active user
    Given the user logs in as "Client Admin"
    When search user to suspend
    And clicks the suspend button
    And check whether user is suspended
    And the user logs out
