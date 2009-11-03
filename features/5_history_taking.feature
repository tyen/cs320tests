Feature: History Taking
  As a user
  I need to login
  In order to have access to the program

  @5.1.1 @wip
  Scenario: create a medical record
    Given I have access to client database
    And I have a medical record with the following information:
      | name | dob        |
      | Joe  | 10/21/1967 |
    And I have a role with permissions to create a medical record
    When I create a new medical record
    Then the medical record is stored in the client database
  
  @5.1.2
  Scenario: report dangerous drug interactions
    Given

  @5.1.3
  Scenario: catch dosage outside of acceptable dosage range

  @5.1.4
  Scenario: catch invalid patient information

  @5.1.5
  Scenario: explicitly download patient record

  @5.1.5
  Scenario: implicitly upload patient records when connected to centralized database
    Given access to the centralized database
    And an internet connection
    And a role with permissions to create medical records
    When I transfer a medical record from the centralized database
    And disconnect the internet
    And make five different edits to the medical records
    And save the edits locally
    And connect the internet
    And upload the edited medical records to the centralized database
    And download the medical records from the centralized database
    Then the five different versions of the medical record can be accessed from the centralized database
    And there is a difference between the versions of the medical records

  @5.1.6
  Scenario: check support for 200 concurrent database connections
    Given access to the centralized database
    And I have an administrator login
    And 200 running processes of the program
    And the centralized database contains the maximum number of medical records
    When I login as the administrator
    And get a new medical record from the database
    And read a random drug from the database
    And write a drug name to the medical record and upload to the database
    And simultaneously open a trial survey
    And fill out the survey
    And write the survey to the database
    Then all the data is successfully read and written
    And all the data was read and written within the maximum time allowed

  @5.3.1
  Scenario: saving an partial record on a local machine
    Given I have access to the client database
    And a role with permissions to create a medical record
    And I create the following partial medical record:
      |  name   |  DOB        |
      |  Alex   |  1928-10-21 |
      |  Bob    |  1928-10-22 |
      |  Josh   |  1928-10-23 |
      |  Jake   |  1928-10-24 |
      |  Tim    |  1928-10-25 |
      |  Steve  |  1928-10-26 |
      |  Alex   |  1928-10-27 |
      |  Joe    |  1928-10-31 |
      |  Kim    |  1928-10-11 |
      |  Jim    |  1928-10-21 |
    When I attempt to submit each medical record
    Then each medical record is not submitted
    And each record is saved locally with incomplete area flagged
    

  @5.3.2
  Scenario: restoring a partial medical record
    Given I have access to the client database
    And a role with permissions to create and edit medical records
    And I create the following partial medical record:
      |  name  |  DOB        |
      |  Alex  |  10/21/1928 |
    When I save the incomplete record locally and exit out of the medical record
    Then I should be able to restore the locally saved medical record
