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

  @5.1.6
  Scenario: check support for 200 concurrent database connections

  @5.3.1
  Scenario: saving an partial record on a local machine
    Given I have access to the client database
    And a role with permissions to create a medical record
    And I create the following partial medical record:
      |  name   |  DOB        |
      |  Alex   |  10/21/1928 |
      |  Bob    |  10/22/1928 |
      |  Josh   |  10/23/1928 |
      |  Jake   |  10/24/1928 |
      |  Tim    |  10/25/1928 |
      |  Steve  |  10/26/1928 |
      |  Alex   |  10/27/1928 |
      |  Joe    |  10/31/1928 |
      |  Kim    |  10/11/1928 |
      |  Jim    |  10/21/1928 |
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
