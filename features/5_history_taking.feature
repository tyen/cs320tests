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

  @5.3.2
  Scenario: restoring a partial medical record
    Given I have access to the client database
    And a role with permissions to create and edit medical records
    And the following partial medical record:
      |  name  |  DOB        |
      |  Alex  |  10/21/1928 |
    When I input the partial medical record data
    And save the incomplete record locally
    Then I should be able to restore the locally saved medical record
