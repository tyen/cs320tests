Feature: History Taking
  As a user
  I need to login
  In order to have access to the program

  @5.1.1
  Scenario: create a medical record
    Given I have access to client database
    And I have a medical record
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
  Scenario: recovering a partial record on a local machine
