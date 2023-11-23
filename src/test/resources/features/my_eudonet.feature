@myEudonet
Feature: My Eudonet Page Testing

  Background:
    Given I am on My Eudonet page

  Scenario Outline: My Preferences - choose language
    Given I am on Choose language page
    When I select a <language> from the drop down list
    Then the Home Page is loaded
    And the next "<logOutBtn>" translations are correct
    Examples:
      | language | logOutBtn                 |
      | FRENCH   | Déconnexion               |
      | ENGLISH  | Log out                   |
      | GERMAN   | Ausschaltung              |
      | DUTCH    | Verbreking van verbinding |
      | SPANISH  | Desconexión               |
      | ITALIAN  | Sconnessione              |


  Scenario Outline: My advanced options - select Office version and export mode
    Given I am on Export report page
    When I select an "<Office>" version and an "<export>" mode
    Then the "<Office>" version and "<export>" mode are set correctly
    Examples:
      | Office         | export   |
      | Office_2019_64 | Standard |
      | Office_2016_64 | Standard |
      | Office_2013_64 | Standard |
      | Office_2010_64 | Standard |
