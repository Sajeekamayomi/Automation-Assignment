@smoke
Feature: View user in User Management

  As a Client Admin
  I want to view a user's details in the User Management page
  So that I can verify the user details

  Scenario: View a user
    Given the user logs in as "Client Admin"
    When I search for the user and navigate to the view user page
    Then I should see the user details are displayed
    And the user logs out