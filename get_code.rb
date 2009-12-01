svn_url = "http://my-svn.assembla.com/svn/cs320/trunk/Project/src/"
src_folder = "teama_src"

def run(cmd)
	if not system(cmd)
		puts "error on command: #{cmd}"
		exit
	end
end

run("svn checkout #{svn_url} #{src_folder}")
run("javac #{src_folder}/*.java")
run("cucumber")
