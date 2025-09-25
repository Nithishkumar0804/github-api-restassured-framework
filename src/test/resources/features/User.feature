@UserFeature
Feature: Retrieve profile info and repositories
  Background:
    Given I am an authenticated user

  @getProfile
  Scenario: Get profile for authenticated user
    When I send a "GET" request to "getUserProfile"
    Then the response "login" should be "rnithishkumar080422"


  @getProfile
  Scenario: get repositories for Authenticated user
    When I send a "GET" request to "getUserRepos"
    Then Get the repo details