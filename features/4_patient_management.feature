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
    When I input a patient's <name> and <DOB>
    Then that patient is displayed by the system
    And the patient's record is downloaded 
    And the record has a time stamp 

  @4.2
  Scenario: creating new patient that doesn't exist in centralized database


  @4.3
  Scenario: search for patients in the centralized database


  
