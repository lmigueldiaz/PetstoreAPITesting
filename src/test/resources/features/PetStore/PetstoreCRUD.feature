@PetStore
Feature: Petstore API CRUD

  Scenario: CRUD complete for a pet
    Given The user creates a new pet
    When  The user retrieves the pet by id
    And   The pet information should be correct
    And   The user updates the pet information
    And   The updated pet data should be correct
    And   The user deletes the pet
    Then  The pet should no longer exist