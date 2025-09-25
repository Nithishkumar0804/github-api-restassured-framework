@miscFeature
Feature: This is a miscellaneous operation
  Scenario: To Check the rate limit for Authenticated user
    When Providing the Authentication for "getRateLimiting"
    And the response status code should be 200


  Scenario: Starring and unstarring a repo
    Given "starring" the repo with "Put"
    Then the response status code should be 204
    Given "unStarring" the repo with "delete"
    Then the response status code should be 204