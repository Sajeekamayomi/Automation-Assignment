@smoke
Feature: Add New User in User Management by Super Admin

  As a Client Admin
  I want to add a new user in the User Management section
  So that I can manage user access within my warehouse system

  Scenario: Successfully add a new user by Super Admin
    Given the user logs in as "Super Admin"
    When the user navigates to the Add New User page as "Super Admin"
    And the user fills user details from property file for "Super Admin"
    And clicks the create button
    Then the user should be successfully created
    And the user logs out
