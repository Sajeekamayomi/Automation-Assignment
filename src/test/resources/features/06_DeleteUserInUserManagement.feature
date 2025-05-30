@smoke
Feature: Delete User in User Management

  As a Client Admin
  I want to delete an existing user in the User Management section
  So that the user no longer has access to the warehouse system

  Scenario: Successfully delete an active user
    Given the user logs in as "Client Admin"
    When the user searches for an existing active user
    And the user selects the user and clicks the delete button
    Then the user account should be removed from the list
    And the user logs out
