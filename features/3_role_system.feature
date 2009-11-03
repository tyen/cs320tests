Feature: role system
  As a system administrator
  I need to manage roles and their permissions
  In order to ensure the system is used securely

  @3.1
  Scenario outline: create roles with all possible permission sets
    Given there is a user "admin" with password "letmein"
    And the user "admin" has the role "System Administrator"
    And the following permissions exist:
      | Permission |
      | p1         |
      | p2         |
      | p3         |
    When I create the role "nurse"
    And grant permissions <permission_set> to the role "nurse"
    And remove all permissions from role "nurse"
    And deactivate the role "nurse"
    Then the role "nurse" has 0 permissions
    And the role nurse is inactive

    Examples:
      | permission_set |
      | 	       |
      | p1	       |
      | p2	       |
      | p3	       |
      | p1,p2	       |
      |	p1,p3	       |
      | p2,p3	       |
      | p1,p2,p3       |

  @3.2.1
  Scenario: try all permitted actions for a user
    Given there is a 

  @3.2.1
  Scenario: try all unpermitted actions for a user


  @3.2.2
  Scenario: check that role assignment stays consistent


  @3.2.3
  Scenario: log all actions for each user


  
