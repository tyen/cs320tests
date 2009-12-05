Given /^the roles "([\w\s,]+)" have different permission sets$/ do |role_list|
  pending # express the regexp above with the code you wish you had
end

Given /^there is a user (\w+) with the password (\w+)$/ do |user,passwd|
  pending # express the regexp above with the code you wish you had
end

Given /^the user (\w+) has the role (\w+)$/ do |user,role|
  pending # express the regexp above with the code you wish you had
end

Then /^I execute all "(\w+)" privileges granted to (\w+)$/ do |privilege_name,user|
  pending # express the regexp above with the code you wish you had
end

Given /^the roles "([\w\s,]+)" exist$/ do |roles_list|
  pending # express the regexp above with the code you wish you had
end

Given /^"(\d+)" users with username "(\w+)" and password "(\w+)", where <i> ranges from (\d+) to (\d+)$/ do |num_users,__u,__p,__id1,__id2|
  # ignore parameters starting with __.  Just create 
  # 'num_users' many users, each with a unique username
  # and with a variety of password.
  pending # express the regexp above with the code you wish you had
end

Given /^"(\w+)" has a randomly selected role <role_name>$/ do |user|
  pending # express the regexp above with the code you wish you had
end

When /^I login all users "([\w\s,]+)" with password "(\w+)"$/ do |user|
  pending # express the regexp above with the code you wish you had
end

Then /^the computer's user environment corresponds with the correctly assigned role$/ do
  pending # express the regexp above with the code you wish you had
end

When /^I remove all useres "(\w+)"$/ do |__u|
  # ignore the __u parameter.  just delete all users from the database
  pending # express the regexp above with the code you wish you had
end

Then /^I expect to not be able to login as "(\w+)" with password "(\w+)"$/ do |user,passwd|
  pending # express the regexp above with the code you wish you had
end

Given /^a user account with role "(\w+)"$/ do |role|
  pending # express the regexp above with the code you wish you had
end

When /^I access all respective functions for each user account$/ do
  # for each user in the database, run all allowed actions as that user
  pending # express the regexp above with the code you wish you had
end

Then /^I expect each action to be logged correctly$/ do
  # all logged actions should not contradict access permissions
  pending # express the regexp above with the code you wish you had
end
