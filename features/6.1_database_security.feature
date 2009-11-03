Feature: Database security of patient records
  As a security specialist
  I need to ensure that patient records are encrypted and zeroed out
  In order to keep data confidential

  @6.1.1
  Scenario: Encryption and decryption of patient information in medical records
    Given I have access to the database
    And my own independent implementation of the encrypted algorithm
    When I create a medical record
    And fill in the name field as "Bob"
    And I encrypt the name field using the installed software
    And I encrypt the name field using my new independently implementation of the encryption algorithm
    Then I should see the encrypted name field equal the independently encrypted name field
    When I decrypt the name field in the installed software and the independent implementation
    Then I should see both name fields equal

  @6.1.2
  Scenario: Zeroing of information on the hard drive to ensure HIPAA compliance