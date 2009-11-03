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
    Then the medical record is stored in the client database
  
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
    Given I have a role with persmissions to create a medical record
    And I am logged into the system
    And I have started the medical interview process
    When I add a new drug with an unreasonably low dosage
    Then I am alerted of an unreasonable dosage

  @5.1.3
  Scenario: input unreasonably high drug dosage
    Given I have a role with persmissions to create a medical record
    And I am logged into the system
    And I have started the medical interview process
    When I add a new drug with an unreasonably high dosage
    Then I am alerted of an unreasonable dosage
    
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
