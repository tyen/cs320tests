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
  Scenario Outline: try all permitted actions for a user
    Given the roles "Nurse, Doctor, Pharmacist" exist
    And the roles "Nurse, Doctor, Pharmacist" have different permission sets
    And there is a user <username> with the password <password>
    And the user <username> has the role <role_name>
    Then I execute all "create" privileges granted to <username>
    And I execute all "read" privileges granted to <username>
    And I execute all "update" privileges granted to <username>
    And I execute all "delete" privileges granted to <username>
    
    Examples:
      | role_name  | username | password  |
      | Nurse	   | karen    | letmein   |
      | Doctor	   | eric     | imadoctor |
      | Pharmacist | ed	      | drugcheck |

  @3.2.1
  Scenario Outline: try all unpermitted actions for a user
    Given the roles "Nurse, Doctor, Pharmacist" exist
    And the roles "Nurse, Doctor, Pharmacist" have different permission sets
    And there is a user <username> with the password <password>
    And the user <username> has the role <role_name>
    Then I execute all "create" privileges not granted to <username>
    And I execute all "read" privileges not granted to <username>
    And I execute all "update" privileges not granted to <username>
    And I execute all "delete" privileges not granted to <username>
    
    Examples:
      | role_name  | username | password  |
      | Nurse	   | karen    | letmein   |
      | Doctor	   | eric     | imadoctor |
      | Pharmacist | ed	      | drugcheck |

  @3.2.2
  Scenario: check that role assignment stays consistent
    Given the roles "Nurse, Doctor, Pharmacist" exist
    And "7500" users with username "user<i>" and password "secretpw<i>", where <i> ranges from 1 to 7500
    And "user<i>" has a randomly selected role <role_name>
    When I login all users "user<i>" with password "secretpw<i>"
    Then the computer's user environment corresponds with the correctly assigned role
    When I remove all useres "user<i>"
    Then I expect to not be able to login as "user<i>" with password "secretpw<i>"


  @3.2.3
  Scenario: log all actions for each user
