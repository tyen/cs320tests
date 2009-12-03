require 'find'
require 'constants'

class TestFinder
  
  def self.unit_test_names
    unit_names = Array.new
    UNIT_TEST_PATHS.each do |path,language|
      unit_names += self.extract_unit_names(path,language)
    end
    return unit_names
  end

private

  def self.extract_unit_names(path,language)
    file_ext = PROGRAMMING_LANGUAGE[language][:unit_file_extension]

    unit_names = Array.new
    Dir.foreach(path) do |f|
      unless file_ext.match(f).nil?
        unit_names += self.parse_file("#{path}/#{f}", language)
      end
    end
    return unit_names
  end

  def self.parse_file(filename,language)
    unit_name_regex = PROGRAMMING_LANGUAGE[language][:unit_name_regex]

    unit_names = Array.new
    File.open(filename, 'r') do |f|
      while (line = f.gets)
        unit_name = unit_name_regex.match(line)
        unit_names << unit_name[1] if defined?(unit_name[1])
      end
    end
    return unit_names
  end

end

puts TestFinder.unit_test_names()
