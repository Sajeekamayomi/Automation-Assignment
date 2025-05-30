@smoke
Feature: Update User in User Management by Client Admin

  Scenario: Successfully update the user by Client Admin
    Given the user logs in as "Super Admin"
    And the user searches for the user
    When the user clicks the edit button for the user and navigates to the edit page
    And the user updates the first name and status
    And the user clicks the update button on edit page
    Then the user's details should be updated successfully
    And the user logs out
