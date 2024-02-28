Feature: User Management

  Scenario: Count all users in the repository
    Given the user repository is initialized
    When I count all users
    Then the count should be greater than or equal to 0
