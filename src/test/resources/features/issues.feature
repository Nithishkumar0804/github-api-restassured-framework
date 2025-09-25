@issuesFeature
Feature: GitHub Issues Management
  This feature file is used to test the GitHub Issues API
  for creating, fetching, and updating issues in a repository.

  Background:
    Given I am an authenticated user

  @CreateIssue
  Scenario: Create a new issue
    When I "createIssue" the issue with "Post" request from POJO
    Then the response status code should be 201

  @GetIssue
  Scenario: Fetch an existing issue
    When I "getIssue" the issue with "Get" request from POJO
    Then the response status code should be 200
    And the issue details should match the POJO

  @LockUnlockIssue
  Scenario: changing the lock status
    When I "lockIssue" the issue with "put"
    And  I "unlockIssue" the issue with "delete"

  @UpdateIssue
  Scenario: Update an existing issue
    When I "updateIssue" the issue with "Patch" request from POJO
    Then the response status code should be 200
    And the issue should be updated successfully




