@address
Feature: Address Page Testing

  Background:
    Given I am on the Home Page

  Scenario: Add Address menu to menu bar
    And the "Address" menu is "not present" in menu bar
    When I "add" "Address" menu in menu bar
    Then the "Address" menu "is" displayed in menu bar

  Scenario: Remove Address menu to menu bar
    And the "Address" menu is "present" in menu bar
    When I "remove" "Address" menu in menu bar
    Then the "Address" menu "is not" displayed in menu bar

  Scenario: Address - list of records
    And the "Address" menu is "present" in menu bar
    When I hover over "Address" from menu bar
    And I click on List Of Records submenu on Address menu
    Then the list of addresses table is displayed

  Scenario Outline: Address - filtering using one parameter
    And the table with all addresses is displayed
    When user filters addresses using "<parameter>" with "<value>"
    Then the selected addresses "<parameter>" and "<value>" are displayed
    Examples:
      | parameter | value              |
      | Company   | TESTING FACTORY S4 |
      | Last Name | VIOREL             |