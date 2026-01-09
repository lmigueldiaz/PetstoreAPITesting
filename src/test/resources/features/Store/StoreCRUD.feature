@Store
Feature: Store API CRUD

  Scenario: Complete CRUD for a store order
    Given I create a new order
    When  I get the order by id
    Then  The order information should be correct
    When  I delete the order
    Then  The order should no longer exist