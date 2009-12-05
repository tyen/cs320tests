Feature: User interface security of sensitive data

  @6.2.1
  Scenario: Users view sensitive data with their proper permissions
    Given I have access to the client database
    And I load the client computer with patient data
    When I view each user group's patient information
    Then I should not see sensitive data that I'm not suppose to see
