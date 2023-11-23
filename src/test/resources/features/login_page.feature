@login
Feature: Login Page Testing

  Background:
    Given the Login Page is loaded


  Scenario Outline: Check translation for available languages
    When I click on any <language> tab
    Then the following data are translated correct
      | logo   | client_area   | client_password   | your_database   | username   | password   | forgot_password   | loginBtn   |
      | <logo> | <client_area> | <client_password> | <your_database> | <username> | <password> | <forgot_password> | <loginBtn> |
    Examples:
      | language | logo               | client_area     | client_password | your_database         | username                | password     | forgot_password              | loginBtn    |
      | FRENCH   | l'Espace Abonné    | Espace client   | Mot de passe    | Votre base de données | Votre nom d'utilisateur | Mot de passe | Mot de passe oublié ?        | Connexion   |
      | ENGLISH  | Eudonet Login Page | Client area     | Password        | Your database         | Your Username           | Password     | Forgot your password?        | Log In      |
      | GERMAN   | dem Nutzerportal   | Clientbereich   | Passwort        | Ihre Datenbank        | Ihr Benutzername        | Passwort     | Passwort vergessen?          | Verbindung  |
      | DUTCH    | de Abonnee Ruimte  | Klanten gebied  | Paswoord        | Uw database           | Uw gebruikersnaam       | Paswoord     | Wachtwoord vergeten?         | Verbinding  |
      | SPANISH  | Espacio Abonato    | Espacio cliente | Contraseña      | Su base de datos      | Su nombre de usuario    | Contraseña   | ¿Ha olvidado su contraseña?  | Conexión    |
      | ITALIAN  | Abbonato           | Area clienti    | Password        | Database              | Il vostro nome d'utente | Password     | Hai dimenticato la password? | Connessione |


  Scenario Outline: Login with invalid credentials
    When user enters invalid credentials login will be unsuccessful
      | client_area   | client_password   | your_database   | username   | password   |
      | <client_area> | <client_password> | <your_database> | <username> | <password> |
    Then an "<error_message>" is displayed
    Examples:
      | client_area | client_password | your_database | username       | password            | error_message            |
      | eudonet     | eudonet         | EUDO CLEAN    | Wrong_Username | EudonetPentalog2023 | Unknown user or password |
      | eudonet     | eudonet         | EUDO CLEAN    | ADMINISTRATEUR | Wrong_Password      | Unknown user or password |


  Scenario: Login then Logout
      When user enter login credentials login is successful
        | client_area | client_password | your_database | username       | password            |
        | eudonet     | eudonet         | EUDO CLEAN    | ADMINISTRATEUR | EudonetPentalog2023 |
      Then the Home Page is loaded
      When user Logout
      Then the Login Page is loaded