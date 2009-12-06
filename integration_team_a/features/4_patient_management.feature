Feature: patient management
  As a user who interacts with patients
  I need the ability to work with patient records on my machine and the centralized database
  In order to improve efficiency of medical history tracking

  @4.1.1
  Scenario Outline: download patient and associated records from centralized database
    Given I have access to the centralized database
    And a patient named <name> who was born on <DOB> is in the centralized database with ID <unique_id>
    When I search for a patient by name <name> and birth date <DOB>
    Then patient <unique_id> is in the client database
    And the patient <unique_id>'s associated medical records are in the client database
    And the patient <unique_id>'s local medical records have time stamps

    Examples:
      |     name     |     DOB    | unique_id |
      | James Smith  | 1978-06-11 | 1         |
      | Ian Blane    | 1999-11-05 | 2	       |
      | Luffy Darell | 2004-02-03 | 3	       |
      | Jimmy Leo    | 1964-01-18 | 4	       |
      | Mike Ike     | 1980-08-29 | 5	       |
      | Vanilla Ice  | 1975-04-12 | 6	       |
      | Simon Lee    | 2009-05-05 | 7	       |
      | Bob Builder  | 2004-09-01 | 8	       |
      | Fox McCloud  | 1989-06-08 | 9	       |
      | Hello Kitty  | 1994-10-26 | 10	       |


  @4.2
  Scenario Outline: creating new patient that doesn't exist in centralized database
    Given I have access to the client database
    And I have access to the centralized database
    And I am logged in as a "Nurse"
    And there is no patient named "Andrew Zimmerman" in the centralized database
    When I create a new patient named "Andrew Zimmerman" who was born on "1972-04-15"
    And data is uploaded from the client database to centralized database
    Then the patient "Andrew Zimmerman" is in the centralized database


  @4.3
  Scenario Outline: search for patients in the client database when offline
    Given I have access to the client database
    And I am logged in as a "Nurse"
    And 2000 patients are in the client database
    And there are no patients named <name> in the client database
    When a patient named <name> who was born on <DOB> is added to the client database
    Then searching for patients named <name> return one result

    Examples:
      | name       | DOB        |
      | John Smith | 1978-04-18 |

  @4.3
  Scenario Outline: search for patients in the centralized database when online
    Given I have access to the centralized database
    And I am logged in as a "Nurse"
    And 2000 patients are in the centralized database
    And there are no patients named <name> in the centralized database
    When a patient named <name> who was born on <DOB> is added to the client database
    And data is uploaded from the client database to centralized database
    Then searching for patients named <name> returns one result

    Examples:
      | name       | DOB        |
      | John Smith | 1978-04-18 |
