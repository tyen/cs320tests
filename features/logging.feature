Feature: logging everyone's actions
  As a security specialist
  I need timestamped logs of how everyone uses the system
  In order to detect suspicious activity

  Scenario: nurse creates new patient
    Given I have permission to see the usage logs
    And users with the role "nurse" can create new patients
    And there is a "nurse" with user ID 55
    And the user with ID 55 just created a patient with ID 7283
    When I open the usage log
    Then I should see that the patient ID 7283 was just created by user ID 55
