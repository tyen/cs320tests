Feature: History Taking
  As a user
  I need to login
  In order to have access to the program

  @5.1.1
  Scenario: create a medical record
    Given I have access to client database
    And I have the following medical record:
      | name   | DOB        |
      | Joe Bo | 1954-03-08 |
    And I have a role with permissions to create a medical record
    When I create a new medical record
    Then the medical record exists in the client database
  
  @5.1.2
  Scenario: adding drugs with serious interactions
    Given I have a list containing critical drug interactions
    And I have the following medical record:
      | name    | DOB        |
      | Max Tee | 1954-03-08 |
    And I have a role with permissions to create a medical record
    When I add 2 drugs with serious interactions
    Then I am alerted of a serious drug interaction

  @5.1.2
  Scenario: adding drug with minor interactions
    Given I have a list containing critical drug interactions
    And I have the following medical record:
      | name    | DOB        |
      | Max Tee | 1954-03-08 |
    And I have a role with permissions to create a medical record
    When I add 2 drugs with minor interactions
    Then I am not alerted of a serious drug interaction

  @5.1.2
  Scenario: adding drugs with no interactions
    Given I have a list containing critical drug interactions
    And I have the following medical record:
      | name    | DOB        |
      | Max Tee | 1954-03-08 |
    And I have a role with permissions to create a medical record
    When I add 2 drugs with no interactions
    Then I am not alerted of a serious drug interaction

  @5.1.3
  Scenario: input negative drug dosage
    Given I have a role with persmissions to create a medical record
    And I am logged into the system
    And I have started the medical interview process
    When I add a new drug with a negative dosage
    Then I am alerted of an unreasonable dosage

  @5.1.3
  Scenario: input unreasonably low drug dosage
    Given I have a role with permissions to create a medical record
    And I am logged into the system
    And I have started the medical interview process
    When I add a new drug with an unreasonably low dosage
    Then I am alerted of an unreasonable dosage

  @5.1.3
  Scenario: input unreasonably high drug dosage
    Given I have a role with permissions to create a medical record
    And I am logged into the system
    And I have started the medical interview process
    When I add a new drug with an unreasonably high dosage
    Then I am alerted of an unreasonable dosage
    
  @5.1.4
  Scenario: save patient with blank name
    Given I have a role with permissions to create a medical record
    And I have the following medical record:
      | name    | DOB        | weight  | height |
      | Max Tee | 1954-03-08 | 170 lbs | 5'10"  |
    And I have deleted the name
    When I save the medical record
    Then I receive a validation error

  @5.1.4
  Scenario: save patient with invalid birthday
    Given I have a role with permissions to create a medical record
    And I have the following medical record:
      | name    | DOB        | weight  | height |
      | Max Tee | 1954-03-08 | 170 lbs | 5'10"  |
    And I entered the following invalid birthdate:
      | birthdate         |
      | -1940-09-13       |
      | 98-12-15          |
      | october 20th 1918 |
    When I save the medical record
    Then I receive a validation error

  @5.1.4
  Scenario: save patient with invalid weight
    Given I have a role with permissions to create a medical record
    And I have the following medical record:
      | name    | DOB        | weight  | height |
      | Max Tee | 1954-03-08 | 170 lbs | 5'10"  |
    And I entered the following invalid weight:
      | weight |
      | -1     |
      | 0      |
      | ninety |
      | 2o0    |
      | 1000   |
    When I save the medical record
    Then I receive a validation error

  @5.1.4
  Scenario: save patient with invalid height
    Given I have a role with permissions to create a medical record
    And I have the following medical record:
      | name    | DOB        | weight  | height |
      | Max Tee | 1954-03-08 | 170 lbs | 5'10"  |
    And I entered the following invalid height:
      | height |
      | -1     |
      | 0      |
      | five   |
      | 6 feet |
      | 1000   |
    When I save the medical record
    Then I receive a validation error

  @5.1.5
  Scenario: explicitly download patient record
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
    And I have a role with permissions to create a medical record
    And I create a partial medical record with name <name> and DOB <DOB>
    When I attempt to submit the partial medical record
    Then the partial medical record is not submitted
    And the record is saved locally with the incomplete area flagged

    Examples:
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

  @5.3.2
  Scenario: restoring a partial medical record
    Given I have access to the client database
    And I have a role with permissions to create and edit medical records
    And I create the following partial medical record:
      |  name  |  DOB        |
      |  Alex  |  1928-10-21 |
    When I save the incomplete record locally
    And I exit out of the medical record
    Then I should be able to restore the locally saved medical record
