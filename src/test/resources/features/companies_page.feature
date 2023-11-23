
Feature: Companies Page Testing

  Background:
    Given I am on the Home Page

  Scenario: Add Address menu to menu bar
    And the "Companies" menu is "not present" in menu bar
    When I "add" "Companies" menu in menu bar
    Then the "Companies" menu "is" displayed in menu bar

  Scenario: Remove Address menu to menu bar
    And the "Companies" menu is "present" in menu bar
    When I "remove" "Companies" menu in menu bar
    Then the "Companies" menu "is not" displayed in menu bar

  Scenario: Companies - list of records
    And the "Companies" menu is "present" in menu bar
    When I hover over "Companies" from menu bar
    And I click on List Of Records submenu on Companies menu
    Then the list of companies table is displayed

  Scenario: Companies - add new record
    And the "Companies" menu is "present" in menu bar
    When I add a new company from New submenu
    Then the new added company is displayed in companies table

  @companies
  Scenario Outline: Companies - filtering using one parameter
    And the table with all companies is displayed
    When user filters companies using "<parameter>" with "<value>"
    Then the selected companies "<parameter>" and "<value>" are displayed
    Examples:
      | parameter | value     |
      | City      | Timisoara |
      | Country   | Romania   |