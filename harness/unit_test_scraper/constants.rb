UNIT_TEST_PATHS=[
#  ['./enter/path/name', :python],
#  ['./enter/path/name', :java],
#  ['./enter/path/name', :flex]
   ['./', :python],
   ['./sample_tests/', :python]
]

PROGRAMMING_LANGUAGE={
  :python => {
    :unit_file_extension => /\.py/,
    :def_statement => /\s*def/,
    :param_list => /\(\.*\)\s*$/,
    :unit_name_regex => /\s*def (\w+)\([\w\s,=\[\]'"]*\):/
  },
  :java => { :unit_file_extension => '.java' },
  :flex => { :unit_file_extension => '.as' }
}

