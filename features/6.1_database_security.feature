Feature: Database security of patient records
	As a security specialist
	I need to ensure that patient records are encrypted and zeroed out
	In order to keep data confidential

	@6.1.1
	Scenario: Encryption of patient information in medical records
		Given I have the installed software
		And access to the database
		And knowledge of the encryption algorithm used
		And my own independent implementatio of the encrypted algorithm
		When I create a medical record
		And fill in the name field as "Bob"
		And I encrypt the name field using the installed software
		And I encrypt the name field using my new independently implementation of the encryption algorithm
		Then I should see the encrypted name field equal the independently encrypted name field

	@6.1.1
	Scenario: Decryption of patient information in medical records
	
	@6.1.2
	Scenario: Zeroing of information on the hard drive to ensure HIPAA compliance
