#Checkout code from repository

system 'svn checkout "http://my-svn.assembla.com/svn/cs320/trunk/Project/src/"'

#if exit code implies error
if $? != 0
  print "error during checkout\n"
  exit
end

#Compile Java Code
system 'javac src/*.java'

#if exit code implies error
if $? != 0
  print "error during compile\n"
  exit
end

#Run cumber tests

system 'cucumber'
