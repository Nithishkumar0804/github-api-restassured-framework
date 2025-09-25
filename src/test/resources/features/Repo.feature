@repoFeature
Feature: GitHub Repository API Tests
  Background:
    Given I am an authenticated user

  @repo @createRepo
  Scenario: Create a new repository
    When I send a "POST" request to "createRepo" with repository payload
    Then the response status code should be 201
    And the response should contain the created repository details

  @repo @update
  Scenario: Update repository details
    When I send a "PATCH" request to "updateRepo" with stored repository name
    Then the response status code should be 200
    And the response should reflect the updated repository details

   @repo @delete
   Scenario: Delete repository
     When I send a "DELETE" request to "deleteRepo" with stored repository name
     Then the response status code should be 204
     And fetching the repository should return 404