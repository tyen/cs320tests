Given /^I am on client machine with access to the network$/ do
  true
end

Given /^my username is "([^\"]*)" and my password is "([^\"]*)"$/ do |arg1, arg2|
  true
end

Given /^the username "([^\"]*)" belongs to a "([^\"]*)"$/ do |arg1, arg2|
  true
end

Given /^I am not logged in already$/ do
  true
end

When /^I enter username "([^\"]*)" and password "([^\"]*)" on the login view$/ do |arg1, arg2|
  true
end

Then /^I should see the main internal view$/ do
  raise "Cannot load main internal view!"
end
