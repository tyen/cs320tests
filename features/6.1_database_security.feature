Feature: Database security of patient records
  As a security specialist
  I need to ensure that patient records are encrypted and zeroed out
  In order to keep data confidential

  @6.1.1
  Scenario: Encryption and decryption of patient information in medical records
    Given I have access to the centralized database
    And my own independent implementation of the encrypted algorithm
    When I create a medical record
    And fill in the name field as "Bob"
    And I encrypt the name field using the installed software
    And I encrypt the name field using my new independently implementation of the encryption algorithm
    Then I should see the encrypted name field equal the independently encrypted name field
    When I decrypt the name field in the installed software
    And I decrypt the name field in the independent implementation
    Then I should see both name fields equal

  @6.1.2
  Scenario: Zeroing of local information after uploading data from the client machine to the centralized database
    Given I have access to the centralized database
    And I have medical records with the following information:
      |  name      |   DOB    |
      |  Eachan    |  9/8/49  |
      |  Eamnonn   |  9/8/49  |
      |  Eamon     |  9/8/49  |
      |  Earl      |  9/8/49  |
      |  Earnest   |  9/8/49  |
      |  Earvin    |  9/8/49  |
      |  Eaton     |  9/8/49  |
      |  Eban      |  9/8/49  |
      |  Ebenezer  |  9/8/49  |
    When I create the medical records on the client machine
    And record the location of the records on the hard drive
    And connect to the centralized database
    And upload the data on the client machine to the centralized database
    Then I expect all bytes from regions where record data was stored on the client machine to be zeroes
