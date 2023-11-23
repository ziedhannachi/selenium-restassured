@business
Feature: Business Page Testing

  Background:
    Given I am on the Home Page

  Scenario: Add Business menu to menu bar
    And the "Business" menu is "not present" in menu bar
    When I "add" "Business" menu in menu bar
    Then the "Business" menu "is" displayed in menu bar

  Scenario: Remove Business menu to menu bar
    And the "Business" menu is "present" in menu bar
    When I "remove" "Business" menu in menu bar
    Then the "Business" menu "is not" displayed in menu bar

  Scenario: Business - see list of records
    And the "Business" menu is "present" in menu bar
    When I hover over "Business" from menu bar
    And I click on List Of Records submenu on Business menu
    Then the list of businesses table is displayed

  Scenario: Business - add new record
    And the "Business" menu is "present" in menu bar
    When I add a new record from Advanced search submenu
    Then the new added business is displayed in businesses table

  Scenario Outline: Business - filtering using one parameter
    And the table with all businesses is displayed
    When user filters businesses using "<parameter>" with "<value>"
    Then the selected businesses "<parameter>" and "<value>" are displayed
    Examples:
      | parameter | value    |
      | Business  | BUSINESS |
      | Decision  | Price    |
      | Source    | Mailing  |
      | Type      | New Biz  |