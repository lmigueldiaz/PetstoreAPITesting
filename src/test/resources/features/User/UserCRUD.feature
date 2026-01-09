@User
Feature: User API CRUD

  Scenario: Complete CRUD for a user
    Given I create a new user
    When  I get the user by username
    And   The user information should be correct
    And   I update the user
    And   The updated user information should be correct
    And   I delete the user
    Then  The user should no longer exist