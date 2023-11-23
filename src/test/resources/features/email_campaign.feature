@campaigns
Feature: Sending Email Campaigns

  Background:
    Given I am on the Home Page

  Scenario: Send email campaigns for contacts list
    And the table with all contacts is displayed
    When user filters contacts using "First name" with "Viorel"
    And user creates a new email campaign
    Then the email campaign with is created
    And the email campaign is found in email campaigns list of records