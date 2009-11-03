# 6.1.1

Given /^I have access to the centralized database$/ do
  true
end

Given /^my own independent implementation of the encrypted algorithm$/ do
  true
end

When /^I create a medical record$/ do
  true
end

When /^fill in the name field as "([^\"]*)"$/ do |arg1|
  true
end

When /^I encrypt the name field using the installed software$/ do
  true
end

When /^I encrypt the name field using my new independently implementation of the encryption algorithm$/ do
  true
end

Then /^I should see the encrypted name field equal the independently encrypted name field$/ do
  true
end

When /^I decrypt the name field in the installed software$/ do
  true
end

When /^I decrypt the name field in the independent implementation$/ do
  true
end

Then /^I should see both name fields equal$/ do
  true
end

# 6.1.2

Given /^I have medical records with the following information:$/ do |table|
  # table is a Cucumber::Ast::Table
  true
end

When /^I create the medical records on the client machine$/ do
  true
end

When /^record the location of the records on the hard drive$/ do
  true
end

When /^connect to the centralized database$/ do
  true
end

When /^upload the data on the client machine to the centralized database$/ do
  true
end

Then /^I expect all bytes from regions were record data was stored on the client machine to be zeroes$/ do
  true
end
