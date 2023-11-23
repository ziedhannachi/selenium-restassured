@contacts
Feature: Contacts Page Testing

  Background:
    Given I am on the Home Page

  Scenario: Add Contact menu to menu bar
    And the "Contacts" menu is "not present" in menu bar
    When I "add" "Contacts" menu in menu bar
    Then the "Contacts" menu "is" displayed in menu bar

  Scenario: Remove Contact menu to menu bar
    And the "Contacts" menu is "present" in menu bar
    When I "remove" "Contacts" menu in menu bar
    Then the "Contacts" menu "is not" displayed in menu bar

  Scenario: Contacts - list of records
    And the "Contacts" menu is "present" in menu bar
    When I hover over "Contacts" from menu bar
    And I click on List Of Records submenu
    Then the list of contacts table is displayed


  Scenario: Contacts - add new record
    And the "Contacts" menu is "present" in menu bar
    When I add a new record from New submenu
    Then the new added contact is displayed in contacts table

  Scenario Outline: Contacts - filtering using one parameter
    And the table with all contacts is displayed
    When user filters contacts using "<parameter>" with "<value>"
    Then the selected contacts "<parameter>" and "<value>" are displayed
    Examples:
      | parameter  | value     |
      | First name | Viorel    |
      | Function   | Tester S6 |
      | Last Name  | VIOREL    |