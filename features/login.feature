Feature: login process
  As a user
  I need to login
  In order to have access to the program

  Scenario: nurse successfully logs into machine on the network
    Given I am on client machine with access to the network
    And my username is "kate" and my password is "s81c0as"
    And the username "kate" belongs to a "nurse"
    And I am not logged in already
    When I enter username "kate" and password "s81c0as" on the login view
    Then I should see the main internal view
