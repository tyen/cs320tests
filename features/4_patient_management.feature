Feature: patient management
  As a user who interacts with patients
  I need the ability to work with patient records on my machine and the centralized database
  In order to improve efficiency of medical history tracking

  @4.1.1
  Scenario: download patient and associated records from centralized database
    Given I have access to the centralzed database
    And there is a list of patients in the centralized database:
      |     name     |     DOB    |
      | James Smith  | 1978-06-11 |
      | Ian Blane    | 1999-11-05 |
      | Luffy Darell | 2004-02-03 |
      | Jimmy Leo    | 1964-01-18 |
      | Mike Ike     | 1980-08-29 |
      | Vanilla Ice  | 1975-04-12 |
      | Simon Lee    | 2009-05-05 |
      | Bob Builder  | 2004-09-01 |
      | Fox McCloud  | 1989-06-08 |
      | Hello Kitty  | 1994-10-26 |
    And I am searching for a patint in the centralized database
    When I input a patient's <name>, <DOB>
    Then that patient's record is downloaded 
    And the patient's associated medical records are downloaded
    And the record has a time stamp 

  @4.2
  Scenario: creating new patient that doesn't exist in centralized database
    Given I have access to the client database
    And I have access to the centralized database
    And I am logged in as a "Nurse"
    When I create a new patient:
      |        name      |    DOB     |
      | Andrew Zimmerman | 1972-04-15 |
    And upload the patient to the client database
    And upload client database to centralized database
    Then patient is written to centralized database


  @4.3 (offline)
  Scenario: search for patients in the client database when offline
    Given I have access to the client database
    And I am logged in as a "Nurse"
    And there are 2000 patients in the client database
    When I search for patient by <name>, <DOB>
    And I search for 1000 different patients in the client database
    Then each search produces the correct <name>, <DOB> for the corresponding patient

  @4.3 (online)
  Scenario: search for patients in the centralized database when online
    Given I have access to the client database
    And I have access to the centralized database
    And I am logged in as a "Nurse"
    And there are 2000 patients in the centralized database
    When I search for patient by <name>, <DOB>
    And I search for 1000 different patients in the centralized database
    Then each search produces the correct <name>, <DOB> for the corresponding patient


  
