svn_url = "http://my-svn.assembla.com/svn/cs320/trunk/Project/src/"
src_folder = "teama_src"

def run(cmd)
	if not system(cmd)
		puts "error on command: #{cmd}"
		exit
	end
end

run("svn export #{svn_url} #{src_folder}")

# Team A needs to provide a jar file without Test classes
run("rm -r #{src_folder}/Test*")
run("rm #{src_folder}/StorageDummy.java")
run("rm #{src_folder}/StorageCopy.java")
run("rm #{src_folder}/StorageTest.java")
run("javac #{src_folder}/*.java")

run("ant cucumber")
