@reports
Feature: Sending Reports Testing

  Background:
    Given I am on the Home Page

  Scenario Outline: Send Excel and Word reports for contacts list
    And the table with all contacts is displayed
    When user filters contacts using "First name" with "Viorel"
    And user creates a new report which contains parameters using "<format>"
      | E-Mail     |
      | First name |
      | Last Name  |
    Then the report file with is created
    Examples:
      | format          |
      | Microsoft Excel |
      | Microsoft Word  |

  Scenario Outline: Add a new Chart report
    And the table with all contacts is displayed
    When user filters contacts using "First name" with "Viorel"
    And user creates a new chart which contains parameters using "<series>" and "<format>"
      | label1_X   | label2_X   | label3_X   | field1_X   | field2_X   | value1_Y   | value2_Y   |
      | <label1_X> | <label2_X> | <label3_X> | <field1_X> | <field2_X> | <value1_Y> | <value2_Y> |
    Then the chart file with is created
    Examples:
      | series              | format       | label1_X | label2_X   | label3_X | field1_X | field2_X | value1_Y | value2_Y          |
      | Single Series Chart | Histogram    | Contacts | Create the | Month    |          |          | Contacts | Number of records |
      | Single Series Chart | Histogram 3D | Contacts | Create the | Month    |          |          | Contacts | Number of records |
      | Multi-Series Chart  | Histogram    | Contacts | Create the | Month    | Contacts | Function | Contacts | Number of records |